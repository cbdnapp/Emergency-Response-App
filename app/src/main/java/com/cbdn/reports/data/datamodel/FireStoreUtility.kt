package com.cbdn.reports.data.datamodel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.firestore.persistentCacheSettings
import convertMillisToDateTime
import kotlinx.coroutines.tasks.await


class FireStoreUtility {
     private val db: FirebaseFirestore = Firebase.firestore
    init {
        val settings = firestoreSettings {
            // Use memory cache
            setLocalCacheSettings(memoryCacheSettings {})
            // Use persistent disk cache (default)
            setLocalCacheSettings(persistentCacheSettings {})
        }
        db.firestoreSettings = settings
        db.collection("reports")
            .addSnapshotListener(MetadataChanges.INCLUDE) { querySnapshot, e ->
                if (e != null) {
                    Log.e("DEV", "FireStoreUtility.init: Listen error", e)
                    return@addSnapshotListener
                }
                for (change in querySnapshot!!.documentChanges) {
                    if (change.type == DocumentChange.Type.ADDED) {
                        Log.d("DEV", "FireStoreUtility.init: New Report: ${change.document.data}")
                    }
                    val source = if (querySnapshot.metadata.isFromCache) {
                        "local cache"
                    } else {
                        "server"
                    }
                    Log.d("DEV", "FireStoreUtility.init: Data fetched from $source")
                }
            }
    }

    suspend fun getReports(finalized: Boolean): List<Pair<String, Report>> {
        val reports: MutableList<Pair<String, Report>> = mutableListOf()
        val documents = db.collection("reports")
            .orderBy("datetimeDispatch", Query.Direction.DESCENDING)
            .whereEqualTo("finalized", finalized)
            .get()
            .await()

        if(documents.isEmpty){
            Log.d("DEV", "FireStoreUtility.getReports: Received no documents")
        } else {
            for (document in documents) {
                val reportID: String = document.id
                val report: Report = document.toObject(Report::class.java)
                reports.add(Pair(reportID, report))

            }
//            Log.d("DEV", "$reports")
        }
//        Log.d("DEV", "FireStoreUtility Reports: $reports")
        return reports
    }

    fun updateReport(report: Report, reportID: String){
        db.collection("reports").document(reportID)
            .set(report)
            .addOnSuccessListener {
                Log.d("DEV", "FireStoreUtility.updateReport: DocumentSnapshot added with ID: $reportID")
            }
            .addOnFailureListener { error ->
                Log.e("DEV", "FireStoreUtility.updateReport: Error adding document: $error")
            }
    }

    fun submitReport(report: Report) {
        db.collection("reports")
            .add(report)
            .addOnSuccessListener { documentReference ->
                Log.d("DEV", "FireStoreUtility.submitReport: DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { error ->
                Log.e("DEV", "FireStoreUtility.submitReport: Error adding document: $error")
            }
    }

    fun amendReport(report: Report, prevID : String){
        db.collection("reports")
            .add(report)
            .addOnSuccessListener { newDocumentReference ->
                db.collection("reports").document(newDocumentReference.id)
                    .update(mapOf("prev" to prevID))
                    .addOnSuccessListener {
                        Log.d("DEV", "FireStoreUtility.amendReport: Successfully updated document: ${newDocumentReference.id} ")
                    }
                    .addOnFailureListener{ error ->
                        Log.e("DEV", "FireStoreUtility.amendReport: Error updating document: ${newDocumentReference.id}: $error")
                    }
                db.collection("reports").document(prevID)
                    .update(mapOf("next" to newDocumentReference.id))
                    .addOnSuccessListener {
                        Log.d("DEV", "FireStoreUtility.amendReport: Successfully updated document: $prevID ")
                    }
                    .addOnFailureListener{ error ->
                        Log.e("DEV", "FireStoreUtility.amendReport: Error updating document: $prevID: $error")
                    }
            }
            .addOnFailureListener{ error ->
                Log.e("DEV", "FireStoreUtility.amendReport: Error appending document: $error")
            }
    }

    private fun containsContent(report: Report, content: String?, start: Long?, end: Long?): Boolean {
        if (content.isNullOrEmpty()) {
            return report.datetimeDispatch!! in (start ?: 0)..(end ?: Long.MAX_VALUE)
        } else {
            for (victim in report.victimInfo) {
                if (victim.name == content){
                    return true
                }
            }
            return (report.commandingOfficer == content ||
                    report.reportWriter == content ||
                    report.location == content) &&
                    report.datetimeDispatch!! in (start ?: 0)..(end ?: Long.MAX_VALUE)
        }
    }

    // Query by date, by author, by commanding officer, by victim, by location, and by truck.
    suspend fun filterQuery(content: String?, start: Long?, end: Long?) : List<Pair<String, Report>>{
        Log.d("DEV", "FireStoreUtility.filterQuery: content: ${content}, start: ${convertMillisToDateTime(start)}, end: ${convertMillisToDateTime(end)}")


        val reports: MutableList<Pair<String, Report>> = mutableListOf()
        val documents: List<Pair<String, Report>> = getReports(true)

        if(documents.isEmpty()){
            Log.d("DEV", "FireStoreUtility.filterQuery: Received no documents")
        } else {
            for (document in documents) {
                val report: Report = document.second
                if (containsContent(report, content, start, end)){
                    reports.add(document)
                }
            }
        }
        return reports
    }

//    suspend fun getReport(id: String) : Report?{
//        val document = db.collection("reports").document(id)
//            .get()
//            .await()
//        return document.toObject(Report::class.java)
//    }

    fun deleteReport(reportID: String){
        db.collection("reports").document(reportID)
            .delete()
            .addOnSuccessListener { Log.d("DEV", "FireStoreUtility.deleteReport: Successfully deleted document: $reportID ") }
            .addOnFailureListener { error -> Log.e("DEV", "FireStoreUtility.deleteReport: Error deleting document: $error")}
    }
}
package com.cbdn.reports.data.datamodel
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.firestore.persistentCacheSettings
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
                    Log.e("DEV", "Listen error", e)
                    return@addSnapshotListener
                }
                for (change in querySnapshot!!.documentChanges) {
                    if (change.type == DocumentChange.Type.ADDED) {
                        Log.d("DEV", "New Report: ${change.document.data}")
                    }
                    val source = if (querySnapshot.metadata.isFromCache) {
                        "local cache"
                    } else {
                        "server"
                    }
                    Log.d("DEV", "Data fetched from $source")
                }
            }

    }


    suspend fun getReports(finalized: Boolean): List<Pair<String, Report>> {
        val reports: MutableList<Pair<String, Report>> = mutableListOf()
        val documents = db.collection("reports")
            .whereEqualTo("finalized", finalized)
            .get()
            .await()

        if(documents.isEmpty){
            Log.d("DEV", "Received no documents")
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
                Log.d("DEV", "DocumentSnapshot added with ID: $reportID")
            }
            .addOnFailureListener { error ->
                Log.e("DEV", "Error adding document: $error")
            }
    }

    fun submitReport(report: Report) {
        db.collection("reports")
            .add(report)
            .addOnSuccessListener { documentReference ->
                Log.d("DEV", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { error ->
                Log.e("DEV", "Error adding document: $error")
            }
    }

    fun amendReport(report: Report, prevID : String){
        db.collection("reports")
            .add(report)
            .addOnSuccessListener { newDocumentReference ->
                db.collection("reports").document(newDocumentReference.id)
                    .update(mapOf("prev" to prevID))
                    .addOnSuccessListener {
                        Log.d("DEV", "Successfully updated document: ${newDocumentReference.id} ")
                    }
                    .addOnFailureListener{ error ->
                        Log.e("DEV", "Error updating document: ${newDocumentReference.id}: $error")
                    }
                db.collection("reports").document(prevID)
                    .update(mapOf("next" to newDocumentReference.id))
                    .addOnSuccessListener {
                        Log.e("DEV", "Successfully updated document: $prevID ")
                    }
                    .addOnFailureListener{ error ->
                        Log.e("DEV", "Error updating document: $prevID: $error")
                    }
            }
            .addOnFailureListener{ error ->
                Log.e("DEV", "Error appending document: $error")
            }
    }

    private fun containsContent(report: Report, content: String?, start: Long?, end: Long?): Boolean {
        for (victim in report.victimInfo) {
            if (victim.name == content){
                return true
            }
        }
        return (report.commandingOfficer == content ||
                report.reportWriter == content ||
                report.location == content) &&
                (report.datetimeReturn!! >= (start ?: -1) && report.datetimeReturn!! <= (end ?: Long.MAX_VALUE))
    }
    // Query by date, by author, by commanding officer, by victim, by location, and by truck.
    suspend fun filterQuery(content: String?, start: Long?, end: Long?) : List<Pair<String, Report>>{

        val reports: MutableList<Pair<String, Report>> = mutableListOf()
        val documents = db.collection("reports")
            .whereEqualTo("finalized", true)
            .get()
            .await()

        if(documents.isEmpty){
            Log.d("DEV", "Received no documents")
        } else {
            for (document in documents) {
                val reportID: String = document.id
                val report: Report = document.toObject(Report::class.java)
                if (containsContent(report, content, start, end)){
                    reports.add(Pair(reportID, report))
                }
            }
        }
        Log.d("DEV", "FireStoreUtility from Filter Reports: $reports")
        return reports
    }

    suspend fun getReport(id: String) : Report?{
        val document = db.collection("reports").document(id)
            .get()
            .await()
        return document.toObject(Report::class.java)

    }
    fun deleteReport(reportID: String){
        db.collection("reports").document(reportID)
            .delete()
            .addOnSuccessListener { Log.e("DEV", "Successfully deleted document: $reportID ") }
            .addOnFailureListener { error -> Log.e("DEV", "Error deleting document: $error")}
    }


}


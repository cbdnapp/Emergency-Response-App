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
                    Log.w("Test", "Listen error", e)
                    return@addSnapshotListener
                }
                for (change in querySnapshot!!.documentChanges) {
                    if (change.type == DocumentChange.Type.ADDED) {
                        Log.d("Test", "New Report: ${change.document.data}")
                    }
                    val source = if (querySnapshot.metadata.isFromCache) {
                        "local cache"
                    } else {
                        "server"
                    }
                    Log.d("Test", "Data fetched from $source")
                }
            }

    }


    suspend fun getReport(finalized: Boolean): List<Pair<String, Report>> {
        val reports: MutableList<Pair<String, Report>> = mutableListOf()
        val documents = db.collection("reports")
            .whereEqualTo("finalized", finalized)
            .get()
            .await()

        if(documents.isEmpty){
            Log.d("Test", "Received no documents")
        } else {
            for (document in documents) {
                val reportID: String = document.id
                val report: Report = document.toObject(Report::class.java)
                reports.add(Pair(reportID, report))
            }
            Log.d("Test", "$reports")
        }
        return reports.toList()
    }

    fun updateReport(report: Report, reportID: String){
        db.collection("reports").document(reportID)
            .set(report)
            .addOnSuccessListener {
                println()
                Log.d("Test", "DocumentSnapshot added with ID: $reportID")
            }
            .addOnFailureListener { error ->
                Log.d("Test", "Error adding document: $error")
            }
    }

    fun submitReport(report: Report) {
        db.collection("reports")
            .add(report)
            .addOnSuccessListener { documentReference -> println()//
                Log.d("Test", "DocumentSnapshot added with ID: ${documentReference.id}")

            }
            .addOnFailureListener { error ->
                Log.d("Test", "Error adding document: $error")
            }
    }

    fun ammendReport(report: Report, prevId : String){
        db.collection("reports")
            .add(report)
            .addOnSuccessListener { newDocumentReference ->
                db.collection("reports").document(newDocumentReference.id)
                    .update(mapOf("prev" to prevId))
                    .addOnSuccessListener {
                        Log.d("Test", "Successfully updated document: ${newDocumentReference.id} ")
                    }
                    .addOnFailureListener{ error ->
                        Log.d("Test", "Error updating document: ${newDocumentReference.id}: $error")
                    }
                db.collection("reports").document(prevId)
                    .update(mapOf("next" to newDocumentReference.id))
                    .addOnSuccessListener {
                        Log.d("Test", "Successfully updated document: $prevId ")
                    }
                    .addOnFailureListener{ error ->
                        Log.d("Test", "Error updating document: $prevId: $error")
                    }
            }
            .addOnFailureListener{ error ->
                Log.d("Test", "Error appending document: $error")
            }
    }
}
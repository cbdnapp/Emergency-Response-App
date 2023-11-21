package com.cbdn.reports.data.datamodel
import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


class FireStoreUtility {
    private val db = Firebase.firestore

    @SuppressLint("SuspiciousIndentation")
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
                println("DocumentSnapshot added with ID: $reportID")
            }
            .addOnFailureListener { error ->
                println("Error adding document: $error")
            }
    }

    fun submitReport(report: Report) {
        db.collection("reports")
            .add(report)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { error ->
                println("Error adding document: $error")
            }
    }
}
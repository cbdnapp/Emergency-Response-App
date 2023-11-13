package com.example.post_poc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.lifecycle.MutableLiveData

import retrofit2.Call
import retrofit2.Response
import retrofit2.create
import java.util.ServiceLoader
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.submit_post)
        val author: EditText = findViewById(R.id.author)
        val code: EditText = findViewById(R.id.emergency_code)


        button.setOnClickListener{ view ->
            postBttnClicked(view, author.text.toString(), code.text.toString())
            author.text.clear()
            code.text.clear()

        }
    }

    fun postBttnClicked(view: View?, author: String, code: String){
        val newReport: Report = Report(
            listOf<String>(),
            listOf(author),
            listOf<String>(),
            listOf<String>(),
            listOf<String>(),
            listOf<String>(),
            listOf<String>(),
            listOf(code),
            false,
            listOf<String>(),
            listOf<String>(),
            listOf<String>(),
            listOf<String>(),
            listOf<String>(),
            listOf<Int>(),
            listOf<VictimInfo>())
        val context = this
        val retroService: RetroService = RetroInstance.buildService(RetroService::class.java)
        val requestCall = retroService.createReport(newReport)
        requestCall.enqueue(object: retrofit2.Callback<ReportResponse> {
            override fun onResponse(call: Call<ReportResponse>, response: Response<ReportResponse>) {
//                if(response.code() == 200){
//                    Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(context, "Response: Failed to add item", Toast.LENGTH_SHORT).show()
//                }
            }

            override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
//                Toast.makeText(context, "On failure: Failed to add item", Toast.LENGTH_SHORT).show()
            }
        })
        println("Button clicked!")

    }
}
package com.example.triviamaker

import android.app.Application
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class TriviaViewModel(application: Application) : AndroidViewModel(application) {
    private val triviaModel: TriviaModel = TriviaModel()

    fun makeRequest(textView: TextView) {
        val queue = Volley.newRequestQueue(getApplication())
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, triviaModel.getUrl(), null,
            { response -> val content = response.getString("text")
                textView.text = content},
            { Toast.makeText(getApplication(), "Something went wrong with trivia request", Toast.LENGTH_LONG).show()})

        queue.add(jsonObjectRequest)
    }

}
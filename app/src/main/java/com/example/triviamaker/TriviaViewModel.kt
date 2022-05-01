package com.example.triviamaker

import android.app.Application
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class TriviaViewModel(application: Application) : AndroidViewModel(application) {
    private val triviaModel: TriviaModel = TriviaModel()

    var content: String? = null

    fun makeRequest(textView: TextView, progress: ProgressBar) {
        progress.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(getApplication())
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, triviaModel.getUrl(), null,
            { response ->  content = response.getString("text")
                textView.text = content
                progress.visibility = View.GONE
            },
            { Toast.makeText(getApplication(), "Something went wrong with trivia request", Toast.LENGTH_LONG).show()})

        queue.add(jsonObjectRequest)
    }

}
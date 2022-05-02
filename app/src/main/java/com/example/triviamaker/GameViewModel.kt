package com.example.triviamaker

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlin.collections.List

class GameViewModel(application: Application): AndroidViewModel(application) {
    private val gameModel: GameModel = GameModel()

    var question: String? = null
    var correctAns: String? = null
    var incorrect: MutableList<String>? = null

    fun makeRequest(mutableParams: MutableMap<String, List<String>>) {
        val url = gameModel.genUrl(mutableParams)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response -> question = response.getString("question")
            correctAns = response.getString("correct_answer")
            incorrect = mutableListOf(response.getString("incorrect_answers")) },
            { Toast.makeText(this.getApplication(),
                "Something went wrong ${it.localizedMessage}", Toast.LENGTH_LONG).show() })

        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest)
    }
}
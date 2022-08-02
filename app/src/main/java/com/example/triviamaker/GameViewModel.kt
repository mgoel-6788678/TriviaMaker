package com.example.triviamaker

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.triviamaker.databinding.FragmentActualGameBinding
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.List

class GameViewModel(application: Application): AndroidViewModel(application) {
    private val gameModel: GameModel = GameModel()

    private lateinit var actualGameFragment: ActualGameFragment

    var question: String? = null
    var correctAns: String? = null
    var incorrect: JSONArray? = null


    fun makeRequest(mutableParams: MutableMap<String, List<String>>) {
        val url = gameModel.genUrl(mutableParams)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response -> val results = response.getJSONArray("results").getJSONObject(0)
                question = results.getString("question")
            correctAns = results.getString("correct_answer")
            incorrect = results.getJSONArray("incorrect_answers")
            Toast.makeText(this.getApplication(), "Got Everything", Toast.LENGTH_LONG).show()
            actualGameFragment.initializeStuff()},
            { Toast.makeText(this.getApplication(),
                "Something went wrong ${it.localizedMessage}", Toast.LENGTH_LONG).show() })

        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest)
    }


    fun getInfoAboutActualGameFragment(agf: ActualGameFragment) {
        this.actualGameFragment = agf
    }
}
package com.example.triviamaker

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class TriviaModel {
    private val url: String = "https://uselessfacts.jsph.pl/random.json?language=en"

    fun getUrl(): String {return url}
}
package com.example.triviamaker

class GameModel {
    private var url: String = "https://opentdb.com/api.php?amount=1"


    fun genUrl(): String {
        // for now
        return url
    }

    private fun getToken() {

    }
}
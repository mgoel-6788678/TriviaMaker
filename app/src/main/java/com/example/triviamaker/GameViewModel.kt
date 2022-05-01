package com.example.triviamaker

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class GameViewModel(application: Application): AndroidViewModel(application) {
    private val gameModel: GameModel = GameModel()

    fun makeRequest() {
        val url = gameModel.genUrl()
    }
}
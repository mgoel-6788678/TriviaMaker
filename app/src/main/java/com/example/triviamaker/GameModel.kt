package com.example.triviamaker

import android.util.Log

class GameModel {
    private var url: String = "https://opentdb.com/api.php?amount=1"


    fun genUrl(mutableParams: MutableMap<String, List<String>>): String {
        val chosenCategoryList: List<String>? = mutableParams["category"]
        val chosenDifficultyList: List<String>? = mutableParams["difficulty"]
        val chosenTypeList: List<String>? = mutableParams["type"]

        if (!chosenCategoryList?.get(0).equals("any")) {
            Log.d("chosenCategory", "Here's what we get: $chosenCategoryList")
            url += "&category="+ (chosenCategoryList?.get(1)?.toInt()?.plus(9)).toString()
        }

        if (!chosenDifficultyList?.get(0).equals("any")) {
            Log.d("chosenDifficulty", "Here's what we get: $chosenDifficultyList")
            url += "&difficulty="+chosenDifficultyList?.get(0)
        }

        if (!chosenTypeList?.get(0).equals("any")) {
            Log.d("chosenType", "here's what we get: $chosenTypeList")
            url += "&type="+ if (chosenTypeList?.get(0).equals("MCQ")) "multiple" else "boolean"
        }
        return url
    }
}
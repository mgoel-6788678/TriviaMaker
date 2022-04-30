package com.example.triviamaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.triviamaker.databinding.ActivityTriviaBinding

class Trivia : AppCompatActivity() {
    private lateinit var binding: ActivityTriviaBinding
    private lateinit var tModel: TriviaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTriviaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TriviaViewModel::class.java)

        // may be erroneous if the text view does not change
        tModel.makeRequest(binding.fact)
    }

    fun nextFact(view: View) {
        tModel.makeRequest(binding.fact)
    }
}
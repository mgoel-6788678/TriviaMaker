package com.example.triviamaker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.triviamaker.databinding.FragmentTriviaBinding

class TriviaFragment : Fragment() {

    private var _binding : FragmentTriviaBinding? = null
    private val binding get() = _binding!!

    private lateinit var tModel: TriviaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentTriviaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.requireActivity().application))[TriviaViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tModel.makeRequest(binding.funFact, binding.progressLoad)
        binding.nextButton.setOnClickListener {
            tModel.makeRequest(binding.funFact, binding.progressLoad)
        }

        binding.shareFact.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, tModel.content)
            val chooser = Intent.createChooser(intent, "Fun Fact")
            startActivity(chooser)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
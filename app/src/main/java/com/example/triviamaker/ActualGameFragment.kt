package com.example.triviamaker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triviamaker.databinding.FragmentActualGameBinding

class ActualGameFragment : Fragment() {

    private var _binding: FragmentActualGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_actual_game, container, false)
        _binding = FragmentActualGameBinding.inflate(inflater, container, false)
        // gameViewModel = GameViewModel(this.requireContext().applicationContext as Application)
        gameViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application))[GameViewModel::class.java]
        gameViewModel.getInfoAboutActualGameFragment(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.optionsList.layoutManager = LinearLayoutManager(this.requireContext())
        binding.optionsList.adapter = ListOfOptionsAdapter(this.requireActivity())
    }

    fun initializeStuff() {
        if (gameViewModel.question != null) {
            gameViewModel.question!!.also { binding.question.text = it }
        }else {
            Log.d("error", "question still null")
        }
    }
}
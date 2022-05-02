package com.example.triviamaker

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.triviamaker.databinding.FragmentGameBinding
import kotlin.collections.MutableMap as MutableMap

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding?= null
    private val binding get() = _binding!!

    private lateinit var gameViewModel: GameViewModel

    private lateinit var mutableParameters : MutableMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mutableParameters = mutableMapOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameViewModel = GameViewModel(this.requireContext().applicationContext as Application)

        binding.submitButton.setOnClickListener {
            val navController = it.findNavController()
            // send the parameters to game view model which will make the request and change it in the Actual game fragment
            // then navigate to the actual game fragment, which will have everything already setup

            gameViewModel.makeRequest(mutableParameters)

            Toast.makeText(this.requireContext(), "Submit button clicked", Toast.LENGTH_LONG).show()
            navController.navigate(R.id.action_gameFragment_to_actualGameFragment)
        }

        ArrayAdapter.createFromResource(this.requireContext(), R.array.categoriesInSpinner, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = it
        }

        binding.categorySpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val chosenCategory = parent?.getItemAtPosition(pos).toString()
                val group = listOf(chosenCategory, pos.toString())
                mutableParameters["category"] = group
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                val chosenCategory = "any"
                mutableParameters["category"] = listOf(chosenCategory, "0")
            }
        }

        ArrayAdapter.createFromResource(this.requireContext(), R.array.difficultyArray, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.difficultySpinner.adapter = it
        }

        binding.difficultySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val chosenDifficulty = p0?.getItemAtPosition(p2).toString()
                mutableParameters["difficulty"] = listOf(chosenDifficulty, p2.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                val chosenDifficulty = p0?.getItemAtPosition(0).toString()
                mutableParameters["difficulty"] = listOf(chosenDifficulty, "0")
            }
        }

        ArrayAdapter.createFromResource(this.requireContext(), R.array.typeQuestion, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.typeSpinner.adapter = it
        }

        binding.typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val chosenType = p0?.getItemAtPosition(p2).toString()
                mutableParameters["type"] = listOf(chosenType, p2.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                val chosenType=p0?.getItemAtPosition(0).toString()
                mutableParameters["type"] = listOf(chosenType, "0")
            }
        }
    }
}
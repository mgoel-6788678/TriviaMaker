package com.example.triviamaker

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

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding?= null
    private val binding get() = _binding!!

    private var chosenCategory: String? = null
    private var chosenCategoryPosition: Int? = null

    private var chosenDifficulty: String? = null
    private var chosenDifficultyPosition: Int? = null

    private var chosenType: String? = null
    private var chosenTypePosition: Int? = null

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

        binding.submitButton.setOnClickListener {
            val navController = it.findNavController()
            Toast.makeText(this.requireContext(), "Submit button clicked", Toast.LENGTH_LONG).show()
            navController.navigate(R.id.action_gameFragment_to_actualGameFragment)
        }

        ArrayAdapter.createFromResource(this.requireContext(), R.array.categoriesInSpinner, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = it
        }

        binding.categorySpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                chosenCategory = parent?.getItemAtPosition(pos).toString()
                chosenCategoryPosition = pos
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                chosenCategory = "any"
                chosenCategoryPosition = 0
            }
        }

        ArrayAdapter.createFromResource(this.requireContext(), R.array.difficultyArray, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.difficultySpinner.adapter = it
        }

        binding.difficultySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                chosenDifficulty = p0?.getItemAtPosition(p2).toString()
                chosenDifficultyPosition =p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                chosenDifficulty = p0?.getItemAtPosition(0).toString()
                chosenDifficultyPosition = 0
            }
        }

        ArrayAdapter.createFromResource(this.requireContext(), R.array.typeQuestion, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.typeSpinner.adapter = it
        }

        binding.typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                chosenType = p0?.getItemAtPosition(p2).toString()
                chosenTypePosition = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                chosenType=p0?.getItemAtPosition(0).toString()
                chosenTypePosition = 0
            }
        }
    }

    // only for the category spinner
}
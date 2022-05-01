package com.example.triviamaker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.triviamaker.databinding.ActivityMainBinding
import com.example.triviamaker.databinding.FragmentDriverBinding

class DriverFragment : Fragment() {
    private var _binding: FragmentDriverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDriverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.triviaButton.setOnClickListener {
            // navigate to trivia fragment
            // val triviaActionId: Int = resources.getIdentifier("action_driverFragment_to_triviaFragment", "id", this.requireContext().packageName)
            it.findNavController().navigate(R.id.action_driverFragment_to_triviaFragment)
        }

        binding.gameButton.setOnClickListener {
            // navigate to game fragment
            // val actionid: Int = resources.getIdentifier("action_driverFragment_to_gameFragment", "id", this.requireContext().packageName)
            it.findNavController().navigate(R.id.action_driverFragment_to_gameFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
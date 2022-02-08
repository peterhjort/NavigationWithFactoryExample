package com.example.navigationexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigationexample1.databinding.FragmentTwoBinding

class FragmentTwo : Fragment() {
    private lateinit var viewModel: FragmentTwoViewModel
    private lateinit var binding: FragmentTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FragmentTwoViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_two, container, false)

        binding.partyListView.text = viewModel.parties.sorted().joinToString(", ")

        binding.button.setOnClickListener {
            val selectedParty = binding.editTextPartyName.text.toString()
            if(selectedParty in viewModel.parties) {
                val action = FragmentTwoDirections.actionFragmentTwoToFragmentThree(selectedParty)
                findNavController().navigate(action)
            } else {
                Toast.makeText(activity, "Hmh", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}

class FragmentTwoViewModel: ViewModel() {
    val parties = ParliamentMembersData.members.map { it.party }.toSet()
}
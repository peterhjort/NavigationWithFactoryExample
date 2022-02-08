package com.example.navigationexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.navigationexample1.databinding.FragmentOneBinding

class FragmentOne : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentOneBinding>(
            inflater, R.layout.fragment_one, container, false)

        binding.goButton.setOnClickListener {
            val action = FragmentOneDirections.actionFragmentOneToFragmentTwo()
            findNavController().navigate(action)
        }

        return binding.root
    }
}
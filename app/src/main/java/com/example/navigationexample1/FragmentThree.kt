package com.example.navigationexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navigationexample1.databinding.FragmentThreeBinding

class FragmentThree : Fragment() {
    private lateinit var binding: FragmentThreeBinding
    private lateinit var viewModel: FragmentThreeViewModel
    private lateinit var viewModelFactory: FragmentThreeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = FragmentThreeArgs.fromBundle(requireArguments())

        viewModelFactory = FragmentThreeViewModelFactory(args.partyName)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FragmentThreeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_three, container, false)

        binding.nextButton.setOnClickListener {
            viewModel.updateMP()
            updateUI()
        }

        updateUI()

        return binding.root
    }

    fun updateUI() {
        binding.nextButton.text = "${viewModel.index+1} / ${viewModel.mps.size}"
        binding.nameView.text = "${viewModel.current.first} ${viewModel.current.last}"
        binding.constituencyView.text = viewModel.current.constituency
        binding.ministerView.text = if (viewModel.current.minister) "ministeri" else "kansanedustaja"
    }
}

class FragmentThreeViewModel(party: String): ViewModel() {
    var index = 0
        private set
    val mps = ParliamentMembersData.members.filter { it.party == party }
    var current = mps[ 0 ]
        private set

    fun updateMP() {
        index = (index + 1) % mps.size
        current = mps[ index ]
    }
}

class FragmentThreeViewModelFactory(private val party: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentThreeViewModel::class.java)) {
            return FragmentThreeViewModel(party) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
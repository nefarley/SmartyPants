package com.example.smartypants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartypants.database.DefinitionsRepository
import com.example.smartypants.databinding.FragmentLetterListBinding
import com.example.smartypants.viewmodel.DefinitionViewModelFactory
import com.example.smartypants.viewmodel.DefinitionsViewModel


class LetterListFragment : Fragment() {
    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!
    private val definitionViewModel: DefinitionsViewModel by activityViewModels {
        DefinitionViewModelFactory(
            (activity?.application as DefinitionsApplication).repository.definitionsDao()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLetterListBinding.inflate(inflater,container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //recyclerview
        val adapter = LetterAdapter{
            val action = LetterListFragmentDirections.actionLetterListFragmentToDefinitionFragment(it.letter)
            this.findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        //viewmodel
        definitionViewModel.allDefinitions.observe(this.viewLifecycleOwner){
                definition -> definition.let {
                    adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this.context,2)
    }


}
package com.example.recipesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentGideRecipeBinding

class GideRecipeFragment : Fragment() {

    private lateinit var binding: FragmentGideRecipeBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGideRecipeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.recipe.observe(activity as LifecycleOwner){
            binding.apply {
                instructions.text = it?.instructions.toString()
                ingredients.text = it?.ingredients.toString()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GideRecipeFragment()
    }
}
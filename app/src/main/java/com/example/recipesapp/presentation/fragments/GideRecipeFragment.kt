package com.example.recipesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentGideRecipeBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class GideRecipeFragment : Fragment() {

    private lateinit var binding: FragmentGideRecipeBinding
    private val dataModel by activityViewModel<DataModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGideRecipeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.recipe.observe(viewLifecycleOwner) {
            binding.apply {
                var text = "${getString(R.string.instructions)} ${it?.instructions.toString()}"
                instructions.text = text
                text = "${getString(R.string.ingredients)} ${it?.ingredients.toString()}"
                ingredients.text = text
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GideRecipeFragment()
    }
}
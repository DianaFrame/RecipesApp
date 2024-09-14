package com.example.recipesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentInfoDishBinding

class InfoDishFragment : Fragment() {

    private lateinit var binding: FragmentInfoDishBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoDishBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.recipe.observe(activity as LifecycleOwner){
            binding.apply {
                name.text = it?.name
                mealType.text = it?.mealType.toString()
                prepTimeMinutes.text = it?.prepTimeMinutes.toString()
                cookTimeMinutes.text = it?.cookTimeMinutes.toString()
                servings.text = it?.servings.toString()
                difficulty.text = it?.difficulty
                cuisine.text = it?.cuisine
                tags.text = it?.tags.toString()
                rating.text = it?.rating.toString()
                caloriesPerServing.text = it?.caloriesPerServing.toString()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoDishFragment()
    }
}
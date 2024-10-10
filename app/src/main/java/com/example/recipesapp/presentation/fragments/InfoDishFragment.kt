package com.example.recipesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentInfoDishBinding

class InfoDishFragment : Fragment() {

    private lateinit var binding: FragmentInfoDishBinding
    private val dataModel: DataModel by activityViewModels { DataModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoDishBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.recipe.observe(viewLifecycleOwner) {
            binding.apply {
                var text = "${getString(R.string.dish_name)} ${it?.name}"
                name.text = text
                text = "${getString(R.string.meal_type)} ${it?.mealType.toString()}"
                mealType.text = text
                text = "${getString(R.string.prep_time_minutes)} ${it?.prepTimeMinutes.toString()}"
                prepTimeMinutes.text = text
                text = "${getString(R.string.cook_time_minutes)} ${it?.cookTimeMinutes.toString()}"
                cookTimeMinutes.text = text
                text = "${getString(R.string.servings)} ${it?.servings.toString()}"
                servings.text = text
                text = "${getString(R.string.difficulty)} ${it?.difficulty}"
                difficulty.text = text
                text = "${getString(R.string.cuisine)} ${it?.cuisine}"
                cuisine.text = text
                text = "${getString(R.string.tags)} ${it?.tags.toString()}"
                tags.text = text
                text = "${getString(R.string.rating)} ${it?.rating.toString()}"
                rating.text = text
                text =
                    "${getString(R.string.calories_per_serving)} ${it?.caloriesPerServing.toString()}"
                caloriesPerServing.text = text
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoDishFragment()
    }
}
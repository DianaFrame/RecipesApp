package com.example.recipesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentDetailsRecipeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class DetailsRecipeFragment : Fragment() {
    private lateinit var binding: FragmentDetailsRecipeBinding
    private val dataModel: DataModel by activityViewModels { DataModel.Factory }
    private val fragmentList = listOf(
        InfoDishFragment.newInstance(),
        GideRecipeFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsRecipeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = VpAdapter(this.requireActivity(), fragmentList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab, pos ->
            tab.text = resources.getStringArray(R.array.tab_layout_titles)[pos]
        }.attach()
        dataModel.loadStatus.observe(viewLifecycleOwner) { status ->
            if (status) {
                binding.detailsRecipe.visibility = View.GONE
                binding.lottieProgressBar.apply {
                    visibility = View.VISIBLE
                    setMinAndMaxProgress(0f, 1f)
                    repeatCount = LottieDrawable.INFINITE
                    repeatMode = LottieDrawable.RESTART
                    playAnimation()
                }
            } else {
                binding.apply {
                    lottieProgressBar.pauseAnimation()
                    lottieProgressBar.visibility = View.GONE
                    detailsRecipe.visibility = View.VISIBLE
                }
            }

        }
        dataModel.recipeId.observe(viewLifecycleOwner) { id ->
            dataModel.getDetails(id = id)

        }
        dataModel.recipe.observe(viewLifecycleOwner) { recipe ->
            Picasso.get().load(recipe?.image).into(binding.image)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsRecipeFragment()
    }
}
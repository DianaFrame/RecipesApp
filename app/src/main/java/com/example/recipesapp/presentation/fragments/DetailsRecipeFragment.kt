package com.example.recipesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.airbnb.lottie.LottieDrawable
import com.example.data.RecipesRepositoryImpl
import com.example.domain.usecases.GetDetailsRecipe
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentDetailsRecipeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsRecipeFragment : Fragment() {
    private lateinit var binding: FragmentDetailsRecipeBinding
    private val recipesRepositoryImpl = RecipesRepositoryImpl()
    private val getDetailsRecipe = GetDetailsRecipe(recipesRepositoryImpl)
    private val dataModel: DataModel by activityViewModels()
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
        binding.detailsRecipe.visibility = View.GONE
        binding.lottieProgressBar.apply {
            visibility = View.VISIBLE
            setMinAndMaxProgress(0f, 1f)
            repeatCount = LottieDrawable.INFINITE
            repeatMode = LottieDrawable.RESTART
            playAnimation()
        }
        dataModel.recipeId.observe(activity as LifecycleOwner){
            CoroutineScope(Dispatchers.IO).launch {
                val recipe = getDetailsRecipe.execute(it)
                activity?.runOnUiThread {
                    binding.apply {
                        lottieProgressBar.pauseAnimation()
                        lottieProgressBar.visibility = View.GONE
                        detailsRecipe.visibility = View.VISIBLE
                        Picasso.get().load(recipe?.image).into(image)
                    }
                    dataModel.recipe.value = recipe
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsRecipeFragment()
    }
}
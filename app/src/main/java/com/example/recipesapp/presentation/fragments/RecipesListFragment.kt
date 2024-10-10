package com.example.recipesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentRecipesListBinding
import com.example.recipesapp.presentation.recycler.Listener
import com.example.recipesapp.presentation.recycler.RecipeAdapter

class RecipesListFragment : Fragment(), Listener {
    private lateinit var binding: FragmentRecipesListBinding
    private val dataModel: DataModel by activityViewModels { DataModel.Factory }
    private val adapter: RecipeAdapter? by lazy { RecipeAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rcRecipes.adapter = adapter
        binding.rcRecipes.layoutManager = GridLayoutManager(view.context, 3)
        dataModel.loadStatus.observe(viewLifecycleOwner) { status ->
            if (status) {
                binding.lottieProgressBar.apply {
                    setMinAndMaxProgress(0f, 1f)
                    repeatCount = LottieDrawable.INFINITE
                    repeatMode = LottieDrawable.RESTART
                    playAnimation()
                }
            } else {
                binding.apply {
                    lottieProgressBar.pauseAnimation()
                    lottieProgressBar.visibility = View.GONE
                    searchRecipe.visibility = View.VISIBLE
                    rcRecipes.visibility = View.VISIBLE
                }
            }

        }
        dataModel.getList()
        dataModel.recipesList.observe(viewLifecycleOwner) { recipes ->
            adapter?.submitList(recipes?.recipes)
        }


        binding.searchRecipe.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dataModel.search(newText)
                dataModel.recipesList.observe(viewLifecycleOwner) { recipes ->
                    adapter?.submitList(recipes?.recipes)
                }
                return true
            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = RecipesListFragment()
    }

    override fun onClick(id: Int) {
        dataModel.selectRecipe(id = id)
        val controller = findNavController()
        controller.navigate(R.id.detailsRecipeFragment)
    }
}
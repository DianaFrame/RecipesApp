package com.example.recipesapp.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.RecipesListItem
import com.example.recipesapp.R

class RecipeAdapter(private val listener: Listener) : ListAdapter<RecipesListItem, RecipeViewHolder>(Comparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        return holder.bind(
            recipesListItem = getItem(position),
            listener = listener)
    }

}
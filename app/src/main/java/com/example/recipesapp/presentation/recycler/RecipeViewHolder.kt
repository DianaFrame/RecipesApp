package com.example.recipesapp.presentation.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.RecipesListItem
import com.example.recipesapp.databinding.RecipeListItemBinding
import com.squareup.picasso.Picasso

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = RecipeListItemBinding.bind(view)
    fun bind(recipesListItem: RecipesListItem, listener: Listener) {
        binding.name.text = recipesListItem.name
        Picasso.get().load(recipesListItem.image).into(binding.image)
        itemView.setOnClickListener {
            listener.onClick(recipesListItem.id)
        }
    }
}
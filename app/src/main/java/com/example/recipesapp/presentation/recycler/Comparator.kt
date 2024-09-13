package com.example.recipesapp.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.RecipesListItem

class Comparator : DiffUtil.ItemCallback<RecipesListItem>() {
    override fun areItemsTheSame(oldItem: RecipesListItem, newItem: RecipesListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipesListItem, newItem: RecipesListItem): Boolean {
        return oldItem == newItem
    }
}
package com.example.recipesapp.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.DetailsRecipe

class DataModel: ViewModel() {
    val recipeId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val recipe: MutableLiveData<DetailsRecipe?> by lazy {
        MutableLiveData<DetailsRecipe?>()
    }
}
package com.example.domain

import com.example.domain.models.DetailsRecipe
import com.example.domain.models.RecipesList

interface RecipesRepository {

    fun getList(): RecipesList

    fun getDetails(): DetailsRecipe

}
package com.example.domain

import com.example.domain.models.DetailsRecipe
import com.example.domain.models.RecipesList

interface RecipesRepository {

    suspend fun getList(): RecipesList?

    suspend fun getDetailsById(id: Int): DetailsRecipe?

    suspend fun getSearchListByName(name: String): RecipesList?


}
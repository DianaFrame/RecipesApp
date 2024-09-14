package com.example.domain.usecases

import com.example.domain.RecipesRepository
import com.example.domain.models.RecipesList

class GetSearchRecipesList(private val recipesRepository: RecipesRepository) {
    suspend fun execute(name: String): RecipesList?{
        return recipesRepository.getSearchListByName(name = name)
    }

}
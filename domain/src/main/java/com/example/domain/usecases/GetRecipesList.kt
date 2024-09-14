package com.example.domain.usecases

import com.example.domain.RecipesRepository
import com.example.domain.models.RecipesList

class GetRecipesList(private val recipesRepository: RecipesRepository) {
    suspend fun execute(): RecipesList? {
        return recipesRepository.getList()
    }
}
package com.example.domain.usecases

import com.example.domain.RecipesRepository
import com.example.domain.models.RecipesList

class GetRecipesList(private val recipesRepository: RecipesRepository) {
    fun execute(): RecipesList {
        return recipesRepository.getList()
    }
}
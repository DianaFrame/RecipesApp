package com.example.domain.usecases

import com.example.domain.RecipesRepository
import com.example.domain.models.DetailsRecipe

class GetDetailsRecipe(private val recipesRepository: RecipesRepository) {
    fun execute(): DetailsRecipe {
        return recipesRepository.getDetails()
    }
}
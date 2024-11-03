package com.example.domain.usecases

import com.example.domain.RecipesRepository
import com.example.domain.models.DetailsRecipe

class GetDetailsRecipeUseCase(private val recipesRepository: RecipesRepository) {
    suspend fun execute(id: Int): DetailsRecipe?{
        return recipesRepository.getDetailsById(id = id)
    }
}
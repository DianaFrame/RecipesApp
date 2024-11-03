package com.example.domain.usecases

import com.example.domain.RecipesRepository
import com.example.domain.models.DetailsRecipe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetDetailsRecipeUseCaseTest {

    private val recipesRepository = mock<RecipesRepository>()

    @Test
    fun `should return recipe's details as in repository`() = runTest {
        val testReturnData = DetailsRecipe(
            caloriesPerServing = 1,
            cookTimeMinutes = 1,
            cuisine = "test cuisine",
            difficulty = "test difficulty",
            id = 1,
            image = "test image",
            ingredients = listOf("test ingredients"),
            instructions = listOf("test instructions"),
            mealType = listOf("test mealType"),
            name = "test name",
            prepTimeMinutes = 1,
            rating = 1.0,
            servings = 1,
            tags = listOf("test tags")
        )
        val testId = 1
        Mockito.`when`(recipesRepository.getDetailsById(testId)).thenReturn(testReturnData)
        val getDetailsRecipeUseCase = GetDetailsRecipeUseCase(recipesRepository = recipesRepository)
        val actual = getDetailsRecipeUseCase.execute(testId)
        val expected = DetailsRecipe(
            caloriesPerServing = 1,
            cookTimeMinutes = 1,
            cuisine = "test cuisine",
            difficulty = "test difficulty",
            id = 1,
            image = "test image",
            ingredients = listOf("test ingredients"),
            instructions = listOf("test instructions"),
            mealType = listOf("test mealType"),
            name = "test name",
            prepTimeMinutes = 1,
            rating = 1.0,
            servings = 1,
            tags = listOf("test tags")
        )
        Assertions.assertEquals(expected, actual)

    }
}
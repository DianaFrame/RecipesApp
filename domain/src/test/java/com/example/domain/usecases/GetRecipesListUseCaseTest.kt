package com.example.domain.usecases

import com.example.domain.RecipesRepository
import com.example.domain.models.RecipesList
import com.example.domain.models.RecipesListItem
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


class GetRecipesListUseCaseTest {

    private val recipesRepository = mock<RecipesRepository>()

    @Test
    fun `should return the list of recipes as in repository`() = runTest {
        val testData = RecipesList(
            listOf(
                RecipesListItem(
                    id = 1,
                    image = "image1",
                    name = "test name1"
                ),
                RecipesListItem(
                    id = 2,
                    image = "image2",
                    name = "test name2"
                )
            )
        )
        Mockito.`when`(recipesRepository.getList()).thenReturn(testData)
        val useCase = GetRecipesListUseCase(recipesRepository = recipesRepository)
        val actual = useCase.execute()
        val expected = RecipesList(
            listOf(
                RecipesListItem(
                    id = 1,
                    image = "image1",
                    name = "test name1"
                ),
                RecipesListItem(
                    id = 2,
                    image = "image2",
                    name = "test name2"
                )
            )
        )

        Assertions.assertEquals(expected, actual)
    }
}
package com.example.data

import com.example.data.api.RecipeApi
import com.example.data.models.DetailsRecipeById
import com.example.data.models.RecipeForList
import com.example.data.models.Recipes
import com.example.domain.RecipesRepository
import com.example.domain.models.DetailsRecipe
import com.example.domain.models.RecipesList
import com.example.domain.models.RecipesListItem

class RecipesRepositoryImpl: RecipesRepository {
    private val api: RecipeApi by lazy { Retrofit.getClient().create(RecipeApi::class.java) }
    override suspend fun getList(): RecipesList? {
        return api
            .getAllRecipes()
            .body()
            ?.toRecipesList()
    }

    override suspend fun getDetailsById(id: Int): DetailsRecipe? {
        return api
            .getDetailsRecipeById(id = id)
            .body()
            ?.toDetailsRecipe()
    }

    override suspend fun getSearchListByName(name: String): RecipesList? {
        return api
            .searchRecipes(name = name)
            .body()
            ?.toRecipesList()
    }

    private fun RecipeForList.toRecipesListItem(): RecipesListItem{
        return RecipesListItem(
            name = this.name,
            id = this.id,
            image = this.image,
        )
    }
    private fun Recipes.toRecipesList(): RecipesList{
        return RecipesList(
            recipes = this.recipes.map {
                it.toRecipesListItem()
            }
        )
    }
    private fun DetailsRecipeById.toDetailsRecipe(): DetailsRecipe{
        return DetailsRecipe(
            cookTimeMinutes = this.cookTimeMinutes,
            name = this.name,
            id = this.id,
            image = this.image,
            prepTimeMinutes = this.prepTimeMinutes,
            mealType = this.mealType,
            tags = this.tags,
            cuisine = this.cuisine,
            servings = this.servings,
            difficulty = this.difficulty,
            instructions = this.instructions,
            ingredients = this.ingredients,
            rating = this.rating,
            caloriesPerServing = this.caloriesPerServing
        )
    }


}
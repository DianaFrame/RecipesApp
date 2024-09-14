package com.example.data.api

import com.example.data.models.DetailsRecipeById
import com.example.data.models.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes")
    suspend fun getAllRecipes(): Response<Recipes>

    @GET("recipes/search")
    suspend fun searchRecipes(@Query("q") name: String): Response<Recipes>

    @GET("recipes/{id}")
    suspend fun getDetailsRecipeById(@Path("id") id: Int): Response<DetailsRecipeById>
}
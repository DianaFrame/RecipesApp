package com.example.data.api

import com.example.data.models.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes")
    suspend fun getAllRecipes(): Response<Recipes>

    @GET("recipes")
    suspend fun searchRecipes(@Query("q") name: String): Response<Recipes>

}
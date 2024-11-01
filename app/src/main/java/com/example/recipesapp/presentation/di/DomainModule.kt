package com.example.recipesapp.presentation.di

import com.example.domain.usecases.GetDetailsRecipe
import com.example.domain.usecases.GetRecipesList
import com.example.domain.usecases.GetSearchRecipesList
import org.koin.dsl.module

val domainModule = module {
    factory<GetRecipesList> {
        GetRecipesList(recipesRepository = get())
    }

    factory<GetDetailsRecipe> {
        GetDetailsRecipe(recipesRepository = get())
    }

    factory<GetSearchRecipesList> {
        GetSearchRecipesList(recipesRepository = get())
    }
}
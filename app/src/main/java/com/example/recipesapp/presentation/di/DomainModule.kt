package com.example.recipesapp.presentation.di

import com.example.domain.usecases.GetDetailsRecipeUseCase
import com.example.domain.usecases.GetRecipesListUseCase
import com.example.domain.usecases.GetSearchRecipesListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetRecipesListUseCase> {
        GetRecipesListUseCase(recipesRepository = get())
    }

    factory<GetDetailsRecipeUseCase> {
        GetDetailsRecipeUseCase(recipesRepository = get())
    }

    factory<GetSearchRecipesListUseCase> {
        GetSearchRecipesListUseCase(recipesRepository = get())
    }
}
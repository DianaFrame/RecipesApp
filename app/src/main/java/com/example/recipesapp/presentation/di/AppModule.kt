package com.example.recipesapp.presentation.di

import com.example.recipesapp.presentation.fragments.DataModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<DataModel> {
        DataModel(
            getDetailsRecipe = get(),
            getRecipesList = get(),
            getSearchRecipesList = get()
        )
    }
}
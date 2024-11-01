package com.example.recipesapp.presentation.di

import com.example.data.RecipesRepositoryImpl
import com.example.domain.RecipesRepository
import org.koin.dsl.module

val dataModule = module {
    single<RecipesRepository> {
        RecipesRepositoryImpl()
    }
}
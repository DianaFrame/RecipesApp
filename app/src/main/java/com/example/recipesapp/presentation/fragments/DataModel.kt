package com.example.recipesapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.DetailsRecipe
import com.example.domain.models.RecipesList
import com.example.domain.usecases.GetDetailsRecipeUseCase
import com.example.domain.usecases.GetRecipesListUseCase
import com.example.domain.usecases.GetSearchRecipesListUseCase
import kotlinx.coroutines.launch

class DataModel(

    private val getSearchRecipesListUseCase: GetSearchRecipesListUseCase,
    private val getRecipesListUseCase: GetRecipesListUseCase,
    private val getDetailsRecipeUseCase: GetDetailsRecipeUseCase,

    ) : ViewModel() {
    private val recipeIdMutable: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private val recipeMutable: MutableLiveData<DetailsRecipe?> by lazy {
        MutableLiveData<DetailsRecipe?>()
    }
    private val loadStatusMutable: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val recipesListMutable: MutableLiveData<RecipesList?> by lazy {
        MutableLiveData<RecipesList?>()
    }
    val recipesList: LiveData<RecipesList?> = recipesListMutable
    val loadStatus: LiveData<Boolean> = loadStatusMutable
    val recipeId: LiveData<Int> = recipeIdMutable
    val recipe: LiveData<DetailsRecipe?> = recipeMutable

    fun search(newText: String?) {
        viewModelScope.launch {
            loadStatusMutable.value = true
            recipesListMutable.value = newText?.let { getSearchRecipesListUseCase.execute(it) }
            loadStatusMutable.value = false

        }
    }

    fun getList() {
        viewModelScope.launch {
            loadStatusMutable.value = true
            recipesListMutable.value = getRecipesListUseCase.execute()
            loadStatusMutable.value = false
        }
    }

    fun getDetails(id: Int) {
        viewModelScope.launch {
            loadStatusMutable.value = true
            recipeMutable.value = getDetailsRecipeUseCase.execute(id = id)
            loadStatusMutable.value = false
        }
    }

    fun selectRecipe(id: Int) {
        recipeIdMutable.value = id
    }
}
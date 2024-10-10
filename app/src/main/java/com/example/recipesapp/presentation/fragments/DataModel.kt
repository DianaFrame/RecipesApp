package com.example.recipesapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.RecipesRepositoryImpl
import com.example.domain.models.DetailsRecipe
import com.example.domain.models.RecipesList
import com.example.domain.usecases.GetDetailsRecipe
import com.example.domain.usecases.GetRecipesList
import com.example.domain.usecases.GetSearchRecipesList
import kotlinx.coroutines.launch

class DataModel(

    private val getSearchRecipesList: GetSearchRecipesList,
    private val getRecipesList: GetRecipesList,
    private val getDetailsRecipe: GetDetailsRecipe,

    ) : ViewModel() {

    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            private val recipesRepositoryImpl = RecipesRepositoryImpl()
            private val getSearchRecipesList = GetSearchRecipesList(recipesRepositoryImpl)
            private val getRecipesList = GetRecipesList(recipesRepositoryImpl)
            private val getDetailsRecipe = GetDetailsRecipe(recipesRepositoryImpl)
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DataModel(
                    getSearchRecipesList = getSearchRecipesList,
                    getRecipesList = getRecipesList,
                    getDetailsRecipe = getDetailsRecipe
                ) as T
            }
        }
    }


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
            recipesListMutable.value = newText?.let { getSearchRecipesList.execute(it) }
            loadStatusMutable.value = false

        }
    }

    fun getList() {
        viewModelScope.launch {
            loadStatusMutable.value = true
            recipesListMutable.value = getRecipesList.execute()
            loadStatusMutable.value = false
        }
    }

    fun getDetails(id: Int) {
        viewModelScope.launch {
            loadStatusMutable.value = true
            recipeMutable.value = getDetailsRecipe.execute(id = id)
            loadStatusMutable.value = false
        }
    }

    fun selectRecipe(id: Int) {
        recipeIdMutable.value = id
    }
}
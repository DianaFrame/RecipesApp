package com.example.recipesapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RecipesRepositoryImpl
import com.example.domain.models.DetailsRecipe
import com.example.domain.models.RecipesList
import com.example.domain.usecases.GetRecipesList
import com.example.domain.usecases.GetSearchRecipesList
import kotlinx.coroutines.launch

class DataModel: ViewModel() {

    private val recipesRepositoryImpl = RecipesRepositoryImpl()
    private val getSearchRecipesList = GetSearchRecipesList(recipesRepositoryImpl)
    private val getRecipesList = GetRecipesList(recipesRepositoryImpl)

    val recipeId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val recipe: MutableLiveData<DetailsRecipe?> by lazy {
        MutableLiveData<DetailsRecipe?>()
    }
    private val loadStatusMutable : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val recipesListMutable: MutableLiveData<RecipesList?> by lazy {
        MutableLiveData<RecipesList?>()
    }
    val recipesList: LiveData<RecipesList?> = recipesListMutable
    val loadStatus: LiveData<Boolean> = loadStatusMutable

    fun search(newText: String?){
        viewModelScope.launch {
            loadStatusMutable.value = true
            recipesListMutable.value = newText?.let { getSearchRecipesList.execute(it) }
            loadStatusMutable.value = false

        }
    }

    fun getList(){
        viewModelScope.launch {
            loadStatusMutable.value = true
            recipesListMutable.value = getRecipesList.execute()
            loadStatusMutable.value = false
        }
    }
}
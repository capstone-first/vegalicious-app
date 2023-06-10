package com.bangkit.vegalicious.ui.screen.searchresults

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchResultsViewModel(
	private val recipeRepository: RecipeRepository,
) : ViewModel() {
	
	private val _uiStateRecipe: MutableStateFlow<UiState<List<Recipe>>> = MutableStateFlow(UiState.Loading)
	
	val uiStateRecipe: StateFlow<UiState<List<Recipe>>>
		get() = _uiStateRecipe
	
	fun getAllRecipes() {
		viewModelScope.launch {
			recipeRepository.getAllRecipes()
				.catch {
					_uiStateRecipe.value = UiState.Error(it.message.toString())
				}
				.collect {
					_uiStateRecipe.value = UiState.Success(it)
				}
		}
	}
	
	fun searchRecipes(query: String, tags: List<String>) {
		viewModelScope.launch {
			recipeRepository.searchRecipes(query, tags)
				.catch {
					_uiStateRecipe.value = UiState.Error(it.message.toString())
				}
				.collect {
					_uiStateRecipe.value = UiState.Success(it)
				}
		}
	}
}
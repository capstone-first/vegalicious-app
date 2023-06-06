package com.bangkit.vegalicious.ui.screen.searchresults

import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchResultsViewModel(
	private val recipeRepository: RecipeRepository,
) : ViewModel() {
	
	private val _uiStateRecipe: MutableStateFlow<UiState<List<Recipe>>> = MutableStateFlow(UiState.Loading)
	
	val uiStateRecipe: StateFlow<UiState<List<Recipe>>>
		get() = _uiStateRecipe
}
package com.bangkit.vegalicious.ui.screen.recipedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
	private val _uiState: MutableStateFlow<UiState<Recipe>> = MutableStateFlow(UiState.Loading)
	
	val uiState: StateFlow<UiState<Recipe>>
		get() = _uiState
	
	fun getRecipeById(recipeId: String) {
		viewModelScope.launch {
			_uiState.value = UiState.Loading
			_uiState.value = UiState.Success(recipeRepository.getRecipeById(recipeId)) // Hasil bisa saja null kalau ada error. Jangan lupa cari logikanya.
		}
	}
}
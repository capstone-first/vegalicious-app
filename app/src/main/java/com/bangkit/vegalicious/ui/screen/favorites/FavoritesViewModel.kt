package com.bangkit.vegalicious.ui.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoritesViewModel(
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
	
	fun getFavorites() {
		viewModelScope.launch {
			recipeRepository.getFavorites()
				.catch {
					_uiStateRecipe.value = UiState.Error(it.message.toString())
				}
				.collect {
					_uiStateRecipe.value = UiState.Success(it)
				}
		}
	}
}
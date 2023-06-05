package com.bangkit.vegalicious.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.models.Category
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
	private val recipeRepository: RecipeRepository,
	private val categoryRepository: CategoryRepository
) : ViewModel() {
	
	private val _uiStateRecipe: MutableStateFlow<UiState<List<Recipe>>> = MutableStateFlow(UiState.Loading)
	private val _uiStateCategory: MutableStateFlow<UiState<List<Category>>> = MutableStateFlow(UiState.Loading)
	
	val uiStateRecipe: StateFlow<UiState<List<Recipe>>>
		get() = _uiStateRecipe
	
	val uiStateCategory: StateFlow<UiState<List<Category>>>
		get() = _uiStateCategory
	
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
	
	fun getTopCategories() {
		viewModelScope.launch {
			categoryRepository.getTopCategories()
				.catch {
					_uiStateCategory.value = UiState.Error(it.message.toString())
				}
				.collect {
					_uiStateCategory.value = UiState.Success(it)
				}
		}
	}
}
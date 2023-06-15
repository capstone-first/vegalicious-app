package com.bangkit.vegalicious.ui.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.FavoriteRepository
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.data.local.entity.RecipeAndTag
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoritesViewModel(
	private val favoriteRepository: FavoriteRepository
) : ViewModel() {
	
//	private val _uiStateRecipe: MutableStateFlow<UiState<List<Recipe>>> = MutableStateFlow(UiState.Loading)
	private val _uiStateFavorite: MutableStateFlow<UiState<List<RecipeAndTag>>> = MutableStateFlow(UiState.Loading)
	
	val uiStateFavorite: StateFlow<UiState<List<RecipeAndTag>>>
		get() = _uiStateFavorite
//
//	fun getAllRecipes() {
//		viewModelScope.launch {
//			recipeRepository.getAllRecipes()
//				.catch {
//					_uiStateRecipe.value = UiState.Error(it.message.toString())
//				}
//				.collect {
//					_uiStateRecipe.value = UiState.Success(it)
//				}
//		}
//	}
	
	fun getFavorites() {
		viewModelScope.launch {
			favoriteRepository.getAllFavorites()
				.catch {
					_uiStateFavorite.value = UiState.Error(it.message.toString())
				}
				.collect {
					_uiStateFavorite.value = UiState.Success(it)
				}
		}
	}
	
//	fun getFavorites() {
//		viewModelScope.launch {
//			recipeRepository.getFavorites()
//				.catch {
//					_uiStateFavorite.value = UiState.Error(it.message.toString())
//				}
//				.collect {
//					_uiStateFavorite.value = UiState.Success(it)
//				}
//		}
//	}
}
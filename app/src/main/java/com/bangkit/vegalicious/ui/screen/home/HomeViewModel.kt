package com.bangkit.vegalicious.ui.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.data.remote.response.RecipeResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.models.Category
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
	private val recipeRepository: RecipeRepository,
	private val categoryRepository: CategoryRepository
) : ViewModel() {
	
	private val _uiStateRecipe: MutableStateFlow<UiState<RecipeResponse>> = MutableStateFlow(UiState.Loading)
	private val _uiStateCategory: MutableStateFlow<UiState<List<Category>>> = MutableStateFlow(UiState.Loading)
	
	
	var isStartedGetAllRecipes by mutableStateOf(false)
		private set
	
	val uiStateRecipe: StateFlow<UiState<RecipeResponse>>
		get() = _uiStateRecipe
	
	val uiStateCategory: StateFlow<UiState<List<Category>>>
		get() = _uiStateCategory
	
	fun getAllRecipes() {
		
		isStartedGetAllRecipes = true
		
		val client = ApiConfig.getApiService().getRecipesWithPage(1)
		client.enqueue(object : Callback<RecipeResponse> {
			override fun onResponse(
				call: Call<RecipeResponse>,
				response: Response<RecipeResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					
					_uiStateRecipe.value = UiState.Success(responseBody)
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					_uiStateRecipe.value = UiState.Error(responseBody.status)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateRecipe.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateRecipe.value = UiState.Error(ON_FAILURE)			}
			
		})
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
	
	companion object {
		const val TAG = "HomeViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
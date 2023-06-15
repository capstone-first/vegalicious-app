package com.bangkit.vegalicious.ui.screen.recipedetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.data.remote.response.RecipeDetailsResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.screen.profile.ProfileViewModel
import com.bangkit.vegalicious.utils.calculateBMI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetailsViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
	private val _uiState: MutableStateFlow<UiState<Recipe>> = MutableStateFlow(UiState.Loading)
	
	val uiState: StateFlow<UiState<Recipe>>
		get() = _uiState
	
	private val _uiStateRecipeDetails: MutableStateFlow<UiState<RecipeDetailsResponse>> = MutableStateFlow(UiState.Loading)
	
	val uiStateRecipeDetails: StateFlow<UiState<RecipeDetailsResponse>>
		get() = _uiStateRecipeDetails
	
	var isStartedRecipeDetails by mutableStateOf(false)
		private set
	
	fun getRecipeById(recipeId: String) {
		isStartedRecipeDetails = true
		val client = ApiConfig.getApiService().getRecipeDetails(recipeId)
		client.enqueue(object : Callback<RecipeDetailsResponse> {
			override fun onResponse(
				call: Call<RecipeDetailsResponse>,
				response: Response<RecipeDetailsResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					
					_uiStateRecipeDetails.value = UiState.Success(responseBody)
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					_uiStateRecipeDetails.value = UiState.Error(responseBody.status)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateRecipeDetails.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<RecipeDetailsResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateRecipeDetails.value = UiState.Error(ON_FAILURE)
			}
			
		})
//		TODO: Hapus kalau sudah aman
//		viewModelScope.launch {
//			_uiState.value = UiState.Loading
//			_uiState.value = UiState.Success(recipeRepository.getRecipeById(recipeId)) // Hasil bisa saja null kalau ada error. Jangan lupa cari logikanya.
//		}
	}
	
	fun saveRecipe() {
	
	}
	
	companion object {
		const val TAG = "RecipeDetailsViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
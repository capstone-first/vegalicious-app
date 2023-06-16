package com.bangkit.vegalicious.ui.screen.recipedetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.remote.response.CheckBookmarkResponse
import com.bangkit.vegalicious.data.remote.response.CreateBookmarkResponse
import com.bangkit.vegalicious.data.remote.response.DeleteBookmarkResponse
import com.bangkit.vegalicious.data.remote.response.RecipeDetailsResponse
import com.bangkit.vegalicious.data.remote.response.RecommendationResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.screen.favorites.FavoritesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetailsViewModel() : ViewModel() {
	
	private val _uiStateRecipeDetails: MutableStateFlow<UiState<RecipeDetailsResponse>> = MutableStateFlow(UiState.Loading)
	val uiStateRecipeDetails: StateFlow<UiState<RecipeDetailsResponse>>
		get() = _uiStateRecipeDetails
	
	private val _uiStateAddFavorite: MutableStateFlow<UiState<CreateBookmarkResponse>> = MutableStateFlow(UiState.Loading)
	val uiStateAddFavorite: StateFlow<UiState<CreateBookmarkResponse>>
		get() = _uiStateAddFavorite
	var isStartedRecipeDetails by mutableStateOf(false)
		private set
	
	private val _uiStateRecommendations: MutableStateFlow<UiState<RecommendationResponse>> = MutableStateFlow(UiState.Loading)
	val uiStateRecommendations: StateFlow<UiState<RecommendationResponse>>
		get() = _uiStateRecommendations
	var isStartedRecommendations by mutableStateOf(false)
		private set
	
	var _isFavorite = MutableStateFlow(false)
	val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()
	
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
					checkFavorite(responseBody.data.id)
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
	}
	
	fun getRecommendation(name: String) {
		isStartedRecommendations = true
		val client = ApiConfig.getApiService().getRecipesRecommendation(name)
		client.enqueue(object : Callback<RecommendationResponse> {
			override fun onResponse(
				call: Call<RecommendationResponse>,
				response: Response<RecommendationResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					_uiStateRecommendations.value = UiState.Error(responseBody.status)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateRecommendations.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<RecommendationResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateRecommendations.value = UiState.Error(ON_FAILURE)
			}
			
		})
	}
	
	fun saveRecipe(id: String) {
		val client = ApiConfig.getApiService().postFavorites(id)
		client.enqueue(object : Callback<CreateBookmarkResponse> {
			override fun onResponse(
				call: Call<CreateBookmarkResponse>,
				response: Response<CreateBookmarkResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.success == "success") {
					Log.d(TAG, SAVE + ON_RESPONSE + responseBody.message)
					_isFavorite.value = true
				} else if(responseBody != null) {
					Log.d(TAG, SAVE + ON_RESPONSE + responseBody.message)
				} else {
					Log.d(TAG, SAVE + ON_RESPONSE + RESPONSE_NULL)
					_uiStateRecommendations.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<CreateBookmarkResponse>, t: Throwable) {
				Log.d(TAG, SAVE + ON_FAILURE)
			}
			
		})
	}
	
	fun deleteFavorite(id: String) {
		val client = ApiConfig.getApiService().deleteFavorite(id)
		client.enqueue(object : Callback<DeleteBookmarkResponse> {
			override fun onResponse(
				call: Call<DeleteBookmarkResponse>,
				response: Response<DeleteBookmarkResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.success == "success") {
					Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_RESPONSE + "Success" + responseBody.message)
					_isFavorite.value = false
				} else if(responseBody != null) {
					Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_RESPONSE + "error" + responseBody.message)
				} else {
					Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_RESPONSE + FavoritesViewModel.RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<DeleteBookmarkResponse>, t: Throwable) {
				Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_FAILURE)
			}
		})
	}
	
	fun checkFavorite(id: String) {
		val client = ApiConfig.getApiService().checkBookmark(id)
		client.enqueue(object : Callback<CheckBookmarkResponse> {
			override fun onResponse(
				call: Call<CheckBookmarkResponse>,
				response: Response<CheckBookmarkResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_RESPONSE + "Success" + responseBody.message)
					_isFavorite.value = responseBody.data
				} else if(responseBody != null) {
					Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_RESPONSE + "error" + responseBody.message)
				} else {
					Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_RESPONSE + FavoritesViewModel.RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<CheckBookmarkResponse>, t: Throwable) {
				Log.d(FavoritesViewModel.TAG, FavoritesViewModel.ON_FAILURE)
			}
		})
	}
	
	
	

	
	companion object {
		const val TAG = "RecipeDetailsViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val SAVE = "Save,"
		const val RESPONSE_NULL = "Response body is null"
	}
}
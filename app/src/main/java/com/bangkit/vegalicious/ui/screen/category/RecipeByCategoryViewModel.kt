package com.bangkit.vegalicious.ui.screen.category

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.remote.response.RecipeResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeByCategoryViewModel : ViewModel() {
	private val _uiStateRecipe: MutableStateFlow<UiState<RecipeResponse>> = MutableStateFlow(UiState.Loading)
	
	val uiStateRecipe: StateFlow<UiState<RecipeResponse>>
		get() = _uiStateRecipe
	
	var isStartedGetRecipes by mutableStateOf(false)
		private set
	
	fun getRecipes(category: String, page: Int = 1) {
		
		isStartedGetRecipes = true
		
		val client = ApiConfig.getApiService().getRecipesByCategory(category, page)
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
	
	companion object {
		const val TAG = "RecipeByCatViMod"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
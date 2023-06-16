package com.bangkit.vegalicious.ui.screen.category.searchcategory

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.remote.response.CategoryListResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCategoryViewModel() : ViewModel() {
	
	private val _uiStateCategories: MutableStateFlow<UiState<CategoryListResponse>> = MutableStateFlow(UiState.Loading)
	
	val uiStateCategories: StateFlow<UiState<CategoryListResponse>>
		get() = _uiStateCategories
	
	var isStartedGetCategories by mutableStateOf(false)
		private set
	
	fun getCategories(query: String = "") {
		
		isStartedGetCategories = true
		
		val client = if(query == "") {
			ApiConfig.getApiService().getAllCategory()
		} else {
			ApiConfig.getApiService().getCategoriesByName(query)
		}
		client.enqueue(object : Callback<CategoryListResponse> {
			override fun onResponse(
				call: Call<CategoryListResponse>,
				response: Response<CategoryListResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					
					_uiStateCategories.value = UiState.Success(responseBody)
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					_uiStateCategories.value = UiState.Error(responseBody.status)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateCategories.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<CategoryListResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateCategories.value = UiState.Error(ON_FAILURE)
			}
		})
	}
	
	fun searchRecipes(query: String) {
		
		isStartedGetCategories = true
		
//		viewModelScope.launch {
//			recipeRepository.searchRecipes(query)
//				.catch {
//					_uiStateRecipe.value = UiState.Error(it.message.toString())
//				}
//				.collect {
//					_uiStateRecipe.value = UiState.Success(it)
//				}
//		}
	}
	
	companion object {
		const val TAG = "SearchResultsViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
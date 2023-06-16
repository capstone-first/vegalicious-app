package com.bangkit.vegalicious.ui.screen.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.remote.response.DeleteBookmarkResponse
import com.bangkit.vegalicious.data.remote.response.GetBookmarkResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesViewModel(
) : ViewModel() {
	
	private val _uiStateFavorite: MutableStateFlow<UiState<GetBookmarkResponse>> = MutableStateFlow(UiState.Loading)
	
	val uiStateFavorite: StateFlow<UiState<GetBookmarkResponse>>
		get() = _uiStateFavorite
	
	private val _uiStateDeleteFavorite: MutableStateFlow<UiState<DeleteBookmarkResponse>> = MutableStateFlow(UiState.Loading)
	
	val uiStateDeleteFavorite: StateFlow<UiState<DeleteBookmarkResponse>>
		get() = _uiStateDeleteFavorite
	
	
	fun getFavorites() {
		val client = ApiConfig.getApiService().getFavorites()
		client.enqueue(object : Callback<GetBookmarkResponse> {
			override fun onResponse(
				call: Call<GetBookmarkResponse>,
				response: Response<GetBookmarkResponse>
			) {
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + "Success" + responseBody.message)
					
					_uiStateFavorite.value = UiState.Success(responseBody)
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + "error" + responseBody.message)
					_uiStateFavorite.value = UiState.Error(responseBody.message)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateFavorite.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<GetBookmarkResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateFavorite.value = UiState.Error(ON_FAILURE)
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
					Log.d(TAG, ON_RESPONSE + "Success" + responseBody.message)
					_uiStateDeleteFavorite.value = UiState.Success(responseBody)
					_uiStateFavorite.value = UiState.Loading
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + "error" + responseBody.message)
					_uiStateDeleteFavorite.value = UiState.Error(responseBody.message)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateDeleteFavorite.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<DeleteBookmarkResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateDeleteFavorite.value = UiState.Error(ON_FAILURE)
			}
		})
	}
	
	
	companion object {
		const val TAG = "FavoritesViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
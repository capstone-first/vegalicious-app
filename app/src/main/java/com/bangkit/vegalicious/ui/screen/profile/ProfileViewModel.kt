package com.bangkit.vegalicious.ui.screen.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.remote.response.ProfileResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.utils.calculateBMI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(
) : ViewModel() {
	
	private val _uiState: MutableStateFlow<UiState<ProfileResponse>> = MutableStateFlow(UiState.Loading)
	val uiState: StateFlow<UiState<ProfileResponse>>
		get() = _uiState

	
	var bmiState by mutableStateOf(0f to "Unknown")
	
	var isAuthed by mutableStateOf(true)
		private set
	
	var isStartedLogout by mutableStateOf(false)
		private set
	
	var isStartedGetProfile by mutableStateOf(false)
		private set
	
	fun getProfile() {
		isStartedGetProfile = true
		val client = ApiConfig.getApiService().getProfile()
		client.enqueue(object : Callback<ProfileResponse> {
			override fun onResponse(
				call: Call<ProfileResponse>,
				response: Response<ProfileResponse>
			) {
				
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					val weight = responseBody.data.weight.toFloat()
					val height = responseBody.data.height.toFloat()
					
					if(weight != 0f && height != 0f)
						bmiState = calculateBMI(weight, height)
					_uiState.value = UiState.Success(responseBody)
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + responseBody.status)
					_uiState.value = UiState.Error(responseBody.status)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiState.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiState.value = UiState.Error(ON_FAILURE)
			}
		})
	}
	
	fun logout() {
		isAuthed = false
		isStartedLogout = true
		ApiConfig.clearAuth()
	}
	
	fun stop() {
		isStartedLogout = false
	}
	fun stopGetProfile() {
		isStartedGetProfile = false
	}
	
	companion object {
		const val TAG = "ProfileViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
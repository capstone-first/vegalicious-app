package com.bangkit.vegalicious.ui.screen.auth.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.remote.response.LoginResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
	
	private val _uiStateLogin: MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Loading)
	val uiStateLogin: StateFlow<UiState<LoginResponse>>
		get() = _uiStateLogin
	
	var isDone by mutableStateOf(false)
	
	fun loginUser(email: String, password: String) {
		
		val client = ApiConfig.getApiService().loginUser(email, password)
		client.enqueue(object: Callback<LoginResponse> {
			override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
				
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + responseBody.message)
					isDone = true
					ApiConfig.setAuth(responseBody.accessToken)
					_uiStateLogin.value = UiState.Success(responseBody) //LoginResponse
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + responseBody.message)
					_uiStateLogin.value = UiState.Error(responseBody.message)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateLogin.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateLogin.value = UiState.Error(ON_FAILURE)
			}
			
		})
	}
	
	companion object {
		const val TAG = "LoginViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
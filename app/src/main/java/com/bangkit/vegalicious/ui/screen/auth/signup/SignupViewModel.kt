package com.bangkit.vegalicious.ui.screen.auth.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bangkit.vegalicious.data.remote.response.RegisterResponse
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
	
	private val _uiStateRegister: MutableStateFlow<UiState<RegisterResponse>> = MutableStateFlow(UiState.Loading)
	val uiStateRegister: StateFlow<UiState<RegisterResponse>>
		get() = _uiStateRegister
	
	var isDone by mutableStateOf(false)
	
	fun signupUser(email: String, password: String, name: String) {
		
		val client = ApiConfig.getApiService().registerUser(email, password, name)
		client.enqueue(object: Callback<RegisterResponse> {
			override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
				
				val responseBody = response.body()
				if(responseBody?.status == "success") {
					Log.d(TAG, ON_RESPONSE + responseBody.message)
					isDone = true
					ApiConfig.setAuth(responseBody.accessToken)
					_uiStateRegister.value = UiState.Success(responseBody) //RegisterResponse
				} else if(responseBody != null) {
					Log.d(TAG, ON_RESPONSE + responseBody.message)
					_uiStateRegister.value = UiState.Error(responseBody.message)
				} else {
					Log.d(TAG, ON_RESPONSE + RESPONSE_NULL)
					_uiStateRegister.value = UiState.Error(RESPONSE_NULL)
				}
			}
			
			override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
				Log.d(TAG, ON_FAILURE)
				_uiStateRegister.value = UiState.Error(ON_FAILURE)
			}
			
		})
	}
	
	companion object {
		const val TAG = "SignupViewModel"
		const val ON_FAILURE = "onFailure"
		const val ON_RESPONSE = "onResponse: "
		const val RESPONSE_NULL = "Response body is null"
	}
}
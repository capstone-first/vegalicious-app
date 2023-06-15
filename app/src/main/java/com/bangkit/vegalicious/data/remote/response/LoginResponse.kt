package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	
	@field:SerializedName("data")
	val loginData: AuthData,
	
	@field:SerializedName("message")
	val message: String,
	
	@field:SerializedName("accessToken")
	val accessToken: String,
	
	@field:SerializedName("status")
	val status: String,
	
	@field:SerializedName("refreshToken")
	val refreshToken: String
)
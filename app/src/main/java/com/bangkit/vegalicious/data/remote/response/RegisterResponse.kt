package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: AuthData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("accessToken")
	val accessToken: String,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("refreshToken")
	val refreshToken: String
)



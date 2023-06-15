package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
	
	@field:SerializedName("data")
	val data: UserData,
	
	@field:SerializedName("status")
	val status: String
)



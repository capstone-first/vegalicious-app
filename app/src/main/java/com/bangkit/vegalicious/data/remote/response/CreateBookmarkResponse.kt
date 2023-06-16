package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreateBookmarkResponse(

//	@field:SerializedName("data")
//	val data: CreateBookmarkData,

	@field:SerializedName("success")
	val success: String,

	@field:SerializedName("message")
	val message: String
)

data class CreateBookmarkData(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("recipeId")
	val recipeId: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

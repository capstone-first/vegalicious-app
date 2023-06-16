package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteBookmarkResponse(

	@field:SerializedName("data")
	val data: DeleteBookmarkData,

	@field:SerializedName("success")
	val success: String,

	@field:SerializedName("message")
	val message: String
)

data class DeleteBookmarkData(

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

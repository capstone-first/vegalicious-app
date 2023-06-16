package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetBookmarkResponse(
	
	@field:SerializedName("data")
	val data: List<FavoriteDataItem>,
	
	@field:SerializedName("status")
	val status: String,
	
	@field:SerializedName("message")
	val message: String
)

data class FavoriteRecipeItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)

data class FavoriteDataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("Recipe")
	val recipe: FavoriteRecipeItem,

	@field:SerializedName("id")
	val id: String
)

package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

	@field:SerializedName("message")
	val message: String,
	
	@field:SerializedName("data")
	val data: List<RecipeData>,

	@field:SerializedName("status")
	val status: String
)

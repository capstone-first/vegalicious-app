package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

	@field:SerializedName("data")
	val data: List<RecommendationItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class RecommendationItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("directions")
	val directions: List<String?>? = null,

	@field:SerializedName("ingredients")
	val ingredients: List<String?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: List<String?>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

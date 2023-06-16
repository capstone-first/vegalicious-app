package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(

	@field:SerializedName("totalData")
	val totalData: Int,

	@field:SerializedName("data")
	val data: List<RecipeData>,

	@field:SerializedName("totalPages")
	val totalPages: Int,

	@field:SerializedName("currentPage")
	val currentPage: Int,

	@field:SerializedName("status")
	val status: String
)

data class RecipeData(

	@field:SerializedName("sodium")
	val sodium: Int,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("fat")
	val fat: Int,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("description")
	val description: String?,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("calories")
	val calories: Int,

	@field:SerializedName("title")
	val title: String,
	
	@field:SerializedName("RecipeCategory")
	val recipeCategory: List<RecipeCategoryItem>,
)

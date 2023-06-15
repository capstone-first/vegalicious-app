package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeDetailsResponse(

	@field:SerializedName("data")
	val data: RecipeDetailsData,

	@field:SerializedName("status")
	val status: String
)

data class RecipeDetailsData(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("sodium")
	val sodium: Int,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("RecipeCategory")
	val recipeCategory: List<RecipeCategoryItem>,

	@field:SerializedName("fat")
	val fat: Int,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("calories")
	val calories: Int,

	@field:SerializedName("RecipeDirection")
	val recipeDirection: List<RecipeDirectionItem>,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("RecipeIngredient")
	val recipeIngredient: List<RecipeIngredientItem>,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class Ingredient(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)

data class RecipeDirectionItem(

	@field:SerializedName("Direction")
	val direction: Direction
)

data class RecipeIngredientItem(

	@field:SerializedName("Ingredient")
	val ingredient: Ingredient
)

data class Direction(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)

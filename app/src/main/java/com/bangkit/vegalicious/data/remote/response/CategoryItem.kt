package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeCategoryItem(
	
	@field:SerializedName("Category")
	val category: Category
)

data class Category(
	
	@field:SerializedName("name")
	val name: String,
	
	@field:SerializedName("id")
	val id: String
)


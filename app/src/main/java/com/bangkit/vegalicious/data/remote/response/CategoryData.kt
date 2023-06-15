package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryData(
	
	@field:SerializedName("createdAt")
	val createdAt: String,
	
	@field:SerializedName("name")
	val name: String,
	
	@field:SerializedName("id")
	val id: String,
	
	@field:SerializedName("updatedAt")
	val updatedAt: String
)

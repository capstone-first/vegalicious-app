package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
	
	@field:SerializedName("data")
	val data: List<CategoryData>,
	
	@field:SerializedName("status")
	val status: String
)

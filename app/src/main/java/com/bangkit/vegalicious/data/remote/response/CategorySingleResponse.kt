package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategorySingleResponse(

	@field:SerializedName("data")
	val data: CategoryData,

	@field:SerializedName("status")
	val status: String
)

package com.bangkit.vegalicious.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserData(
	
	@field:SerializedName("createdAt")
	val createdAt: String,
	
	@field:SerializedName("photoUrl")
	val photoUrl: String,
	
	@field:SerializedName("name")
	val name: String,
	
	@field:SerializedName("weight")
	val weight: String,
	
	@field:SerializedName("id")
	val id: String,
	
	@field:SerializedName("email")
	val email: String,
	
	@field:SerializedName("updatedAt")
	val updatedAt: String,
	
	@field:SerializedName("height")
	val height: String
)
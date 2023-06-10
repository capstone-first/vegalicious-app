package com.bangkit.vegalicious.models

data class Profile(
	val username: String,
	val email: String,
	var weight: Float = 0f,
	var height: Float = 0f,
	var BMI: Float = 0f
)

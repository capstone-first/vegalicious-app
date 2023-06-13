package com.bangkit.vegalicious.data.remote.retrofit

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
	
	@FormUrlEncoded
	@POST("/api/login")
	fun loginUser(
		@Field("email") email: String,
		@Field("password") password: String,
	)
	
	@FormUrlEncoded
	@POST("/api/register")
	fun registerUser(
		@Field("email") email: String,
		@Field("password") password: String,
		@Field("name") name: String,
	)
	
	@GET("/api/profile")
	fun getProfile()
	
	@GET("/api/category")
	fun getAllCategory()
	
	@GET("/api/category")
	fun getTopCategory() //Belum ada, req dulu
	
	
}
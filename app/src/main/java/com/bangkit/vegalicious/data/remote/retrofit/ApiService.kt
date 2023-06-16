package com.bangkit.vegalicious.data.remote.retrofit

import com.bangkit.vegalicious.data.remote.response.CategoryListResponse
import com.bangkit.vegalicious.data.remote.response.LoginResponse
import com.bangkit.vegalicious.data.remote.response.ProfileResponse
import com.bangkit.vegalicious.data.remote.response.RecipeDetailsResponse
import com.bangkit.vegalicious.data.remote.response.RecipeResponse
import com.bangkit.vegalicious.data.remote.response.RecommendationResponse
import com.bangkit.vegalicious.data.remote.response.RegisterResponse
import retrofit2.http.*
import retrofit2.Call

interface ApiService {
	
	@FormUrlEncoded
	@POST("/api/login")
	fun loginUser(
		@Field("email") email: String,
		@Field("password") password: String,
	): Call<LoginResponse>
	
	@FormUrlEncoded
	@POST("/api/register")
	fun registerUser(
		@Field("email") email: String,
		@Field("password") password: String,
		@Field("name") name: String,
	): Call<RegisterResponse>
	
	@GET("/api/profile")
	fun getProfile(): Call<ProfileResponse>
	
	@GET("/api/recipe")
	fun getAllRecipes(
		@Query("page") page: Int
	): Call<RecipeResponse>
	
	@GET("/api/recipe/{title}/find")
	fun getRecipesByTitle(
		@Path("title") title: String,
		@Query("page") page: Int
	): Call<RecipeResponse>
	
	@GET("api/recipe/{id}")
	fun getRecipeDetails(
		@Path("id") id: String
	): Call<RecipeDetailsResponse>
	
	@FormUrlEncoded
	@POST("/api/recipe/category")
	fun getRecipesByCategory(
		@Field("category") category: String,
		@Query("page") page: Int
	): Call<RecipeResponse>
	
	@FormUrlEncoded
	@POST("/api/recipe/recomendation")
	fun getRecipesRecommendation(
		@Field("sentence") sentence: String,
	): Call<RecommendationResponse>
	
	@GET("/api/category")
	fun getAllCategory(): Call<CategoryListResponse>
	
	@GET("/api/category/name/{name}")
	fun getCategoriesByName(
		@Path("name") name: String,
	): Call<CategoryListResponse>
}
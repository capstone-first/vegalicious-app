package com.bangkit.vegalicious.utils

import android.content.Context
import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.data.FavoriteRepository
import com.bangkit.vegalicious.data.ProfileRepository
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.data.local.room.FavoriteRecipesDatabase
import com.bangkit.vegalicious.data.remote.retrofit.ApiConfig

object Injection {
	
	fun provideRecipeRepository(): RecipeRepository {
		return RecipeRepository.getInstance()
	}
	fun provideCategoryRepository(): CategoryRepository {
		return CategoryRepository.getInstance()
	}
	fun provideProfileRepository(): ProfileRepository {
		return ProfileRepository.getInstance()
	}
	fun provideFavoriteRepository(context: Context): FavoriteRepository {
		val apiService = ApiConfig.getApiService()
		val favoriteDatabase = FavoriteRecipesDatabase.getInstance(context)
		return FavoriteRepository.getInstance(apiService, favoriteDatabase)
	}
}
package com.bangkit.vegalicious.utils

import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.data.RecipeRepository

object Injection {
	
	fun provideRecipeRepository(): RecipeRepository {
		return RecipeRepository.getInstance()
	}
	fun provideCategoryRepository(): CategoryRepository {
		return CategoryRepository.getInstance()
	}
}
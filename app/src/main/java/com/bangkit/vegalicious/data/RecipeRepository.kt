package com.bangkit.vegalicious.data

import com.bangkit.vegalicious.models.Category
import com.bangkit.vegalicious.models.FakeRecipes
import com.bangkit.vegalicious.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RecipeRepository {
	
	private val recipes = mutableListOf<Recipe>()
	init {
		if(recipes.isEmpty()) {
			FakeRecipes.dummyRecipes.forEach {
				recipes.add(it)
			}
		}
	}
	
	fun getAllRecipes(): Flow<List<Recipe>> {
		return flowOf(recipes)
	}
	
	fun searchRecipes(query: String): Flow<List<Recipe>> {
		return flowOf(recipes)
	}
	
	fun getRecipeById(id: String): Recipe {
		return recipes.find { it.id == id } as Recipe // This could be null. Jangan lupa cari logika lain.
	}
	
	fun getFavorites(): Flow<List<Recipe>> {
		return flowOf(recipes)
	}
	
	companion object {
		@Volatile
		private var instance: RecipeRepository? = null
		
		fun getInstance(): RecipeRepository =
			instance ?: synchronized(this) {
				RecipeRepository().apply {
					instance = this
				}
			}
	}
}
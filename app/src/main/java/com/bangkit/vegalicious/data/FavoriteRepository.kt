package com.bangkit.vegalicious.data

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.bangkit.vegalicious.data.local.entity.FavoriteRecipeEntity
import com.bangkit.vegalicious.data.local.entity.TagEntity
import com.bangkit.vegalicious.data.local.room.*
import com.bangkit.vegalicious.data.remote.retrofit.ApiService
import com.bangkit.vegalicious.models.Recipe

class FavoriteRepository(
	val apiService: ApiService,
	private val favoriteRecipesDatabase: FavoriteRecipesDatabase
) {
	
	fun getAllFavorites() = favoriteRecipesDatabase.favoriteDao().getAllFavoriteAndTag()
	
	fun insertFavorites(recipe: Recipe) {
		val fav = FavoriteRecipeEntity(
			id = recipe.id,
			title = recipe.title,
			image = recipe.image,
			description = recipe.description
		)
		var i = 1
		val tags: MutableList<TagEntity> = mutableListOf()
		recipe.tags.forEach {
			val tag = TagEntity(
				tagId = "${recipe.tags}_${i}",
				tagText = it,
				recipeId = recipe.id
			)
			tags.add(tag)
		}
		// TODO
//		favoriteRecipesDatabase.favoriteDao().insertFavorite(fav)
//		favoriteRecipesDatabase.favoriteDao().insertTag(tags)
	}
	
	companion object {
		@Volatile
		private var instance: FavoriteRepository? = null
	fun getInstance(apiService: ApiService, favoriteRecipesDatabase: FavoriteRecipesDatabase): FavoriteRepository =
		instance ?: synchronized(this) {
			FavoriteRepository(apiService, favoriteRecipesDatabase).apply {
				instance = this
			}
		}
	}
}
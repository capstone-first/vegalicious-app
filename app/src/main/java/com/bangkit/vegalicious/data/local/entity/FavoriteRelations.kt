package com.bangkit.vegalicious.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeAndTag(
	@Embedded
	val favoriteRecipeEntity: FavoriteRecipeEntity,
	
	@Relation(
		parentColumn = "id",
		entityColumn = "recipeId"
	)
	val tags: List<TagEntity>
)

data class RecipeAndIngredient(
	@Embedded
	val favoriteRecipeEntity: FavoriteRecipeEntity,
	
	@Relation(
		parentColumn = "id",
		entityColumn = "recipeId"
	)
	val tags: List<IngredientEntity>
)

data class RecipeAndDirection(
	@Embedded
	val favoriteRecipeEntity: FavoriteRecipeEntity,
	
	@Relation(
		parentColumn = "id",
		entityColumn = "recipeId"
	)
	val tags: List<DirectionEntity>
)

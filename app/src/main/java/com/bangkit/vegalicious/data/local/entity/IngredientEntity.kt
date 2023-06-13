package com.bangkit.vegalicious.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
	@PrimaryKey
	@ColumnInfo(name = "ingredientId")
	val ingredientId: Int,
	@ColumnInfo(name = "ingredientText")
	val ingredientText: String,
	@ColumnInfo(name = "recipeId")
	val recipeId: String
)
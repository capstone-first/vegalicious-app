package com.bangkit.vegalicious.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "directions")
data class DirectionEntity(
	@PrimaryKey
	@ColumnInfo(name = "directionId")
	val directionId: Int,
	@ColumnInfo(name = "directionText")
	val directionText: String,
	@ColumnInfo(name = "recipeId")
	val recipeId: String
)
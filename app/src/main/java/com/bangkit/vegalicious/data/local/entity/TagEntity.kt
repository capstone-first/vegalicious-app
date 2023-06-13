package com.bangkit.vegalicious.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
	@PrimaryKey
	@ColumnInfo(name = "tagId")
	val tagId: Int,
	@ColumnInfo(name = "tagText")
	val tagText: String,
	@ColumnInfo(name = "recipeId")
	val recipeId: String
)
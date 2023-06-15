package com.bangkit.vegalicious.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites")
@Parcelize
data class FavoriteRecipeEntity(
	@PrimaryKey
	@ColumnInfo(name = "id")
	val id: String,
	
	@ColumnInfo(name = "title")
	val title: String,
	
//	@ColumnInfo(name = "category")
//	val category: List<String> = listOf(),
	
	@ColumnInfo(name = "image")
	val image: String? = null,
	
//	@ColumnInfo(name = "directions")
//	val directions: List<String> = listOf(),
	
	@ColumnInfo(name = "description")
	val description: String = "There is no description for this recipe."
) : Parcelable

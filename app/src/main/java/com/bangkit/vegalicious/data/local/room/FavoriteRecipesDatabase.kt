package com.bangkit.vegalicious.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.vegalicious.data.local.entity.DirectionEntity
import com.bangkit.vegalicious.data.local.entity.FavoriteRecipeEntity
import com.bangkit.vegalicious.data.local.entity.IngredientEntity
import com.bangkit.vegalicious.data.local.entity.TagEntity

@Database(
	entities = [
		FavoriteRecipeEntity::class,
		TagEntity::class,
		IngredientEntity::class,
		DirectionEntity::class,
	   ],
	version = 2, exportSchema = false)
abstract class FavoriteRecipesDatabase : RoomDatabase() {
	
	abstract fun favoriteDao(): FavoriteDao
	
	companion object {
		@Volatile
		private var INSTANCE: FavoriteRecipesDatabase? = null
		
		@JvmStatic
		fun getInstance(context: Context): FavoriteRecipesDatabase {
			if(INSTANCE == null) {
				synchronized(FavoriteRecipesDatabase::class.java) {
					INSTANCE = Room.databaseBuilder(
						context.applicationContext,
						FavoriteRecipesDatabase::class.java, "favorite_recipe_database"
					)
						.build()
				}
			}
			return INSTANCE as FavoriteRecipesDatabase
		}
	}
}
package com.bangkit.vegalicious.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.bangkit.vegalicious.data.local.entity.DirectionEntity
import com.bangkit.vegalicious.data.local.entity.FavoriteRecipeEntity
import com.bangkit.vegalicious.data.local.entity.IngredientEntity
import com.bangkit.vegalicious.data.local.entity.RecipeAndTag
import com.bangkit.vegalicious.data.local.entity.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insertFavorite(favorite: FavoriteRecipeEntity)
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insertTag(tagEntity: List<TagEntity>)
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insertIngredient(ingredientEntity: List<IngredientEntity>)
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insertDirection(directionEntity: List<DirectionEntity>)
	
	@Update
	fun updateFavorite(favorite: FavoriteRecipeEntity)
	
	@Delete
	fun deleteFavorite(favorite: FavoriteRecipeEntity)
	
	@Transaction
	@Query("SELECT * FROM favorites")
	fun getAllFavoriteAndTag(): Flow<List<RecipeAndTag>>
	
//	@Query("SELECT * FROM favorite_recipe_database ORDER BY title ASC")
//	fun getAllFavorites(): LiveData<List<FavoriteRecipyEntity>>
//
//	@Query("SELECT COUNT(*) FROM favorite_recipe_database WHERE username = :username")
//	fun countFavorite(username: String): Int
//
//	@Query("SELECT * FROM favorite_recipe_database WHERE username = :username")
//	fun getFavoriteByUsername(username: String): LiveData<FavoriteRecipyEntity?>
}
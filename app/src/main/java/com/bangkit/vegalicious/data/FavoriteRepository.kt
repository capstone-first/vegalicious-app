package com.bangkit.vegalicious.data

import com.bangkit.vegalicious.data.local.room.*

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
	
	fun getAllFavorites() = favoriteDao.getAllFavoriteAndTag()
}
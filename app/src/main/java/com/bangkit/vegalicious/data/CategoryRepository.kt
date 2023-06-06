package com.bangkit.vegalicious.data

import com.bangkit.vegalicious.models.Category
import com.bangkit.vegalicious.models.dummyCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CategoryRepository {
	
	private val categories = mutableListOf<Category>()
	init {
		if(categories.isEmpty()) {
			dummyCategories.forEach {
				categories.add(it)
			}
		}
	}
	
	fun getTopCategories(): Flow<List<Category>> {
		return flowOf(categories)
	}
	
	fun getAllCategories(): Flow<List<Category>> {
		return flowOf(categories)
	}
	
	companion object {
		@Volatile
		private var instance: CategoryRepository? = null
		
		fun getInstance(): CategoryRepository =
			instance ?: synchronized(this) {
				CategoryRepository().apply {
					instance = this
				}
			}
	}
}
package com.bangkit.vegalicious.utils

import android.content.Context
import com.bangkit.vegalicious.data.CategoryRepository

object Injection {
	
	fun provideCategoryRepository(): CategoryRepository {
		return CategoryRepository.getInstance()
	}

}
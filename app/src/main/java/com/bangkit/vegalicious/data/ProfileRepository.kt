package com.bangkit.vegalicious.data

import com.bangkit.vegalicious.models.Category
import com.bangkit.vegalicious.models.FakeRecipes
import com.bangkit.vegalicious.models.Profile
import com.bangkit.vegalicious.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProfileRepository {
	
	fun getProfile(username: String): Flow<Profile> {
		return flowOf(Profile(
			username = "johndoe123",
			email = "johndoe@email.com",
			height = 168f,
			weight = 72f
		))
	}
	
	companion object {
		@Volatile
		private var instance: ProfileRepository? = null
		
		fun getInstance(): ProfileRepository =
			instance ?: synchronized(this) {
				ProfileRepository().apply {
					instance = this
				}
			}
	}
}
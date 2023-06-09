package com.bangkit.vegalicious.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.ui.screen.auth.login.LoginViewModel
import com.bangkit.vegalicious.ui.screen.auth.signup.SignupViewModel
import com.bangkit.vegalicious.ui.screen.category.RecipeByCategoryViewModel
import com.bangkit.vegalicious.ui.screen.category.searchcategory.SearchCategoryViewModel
import com.bangkit.vegalicious.ui.screen.favorites.FavoritesViewModel
import com.bangkit.vegalicious.ui.screen.home.HomeViewModel
import com.bangkit.vegalicious.ui.screen.profile.ProfileViewModel
import com.bangkit.vegalicious.ui.screen.recipedetails.RecipeDetailsViewModel
import com.bangkit.vegalicious.ui.screen.searchresults.SearchResultsViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
	private val categoryRepository: CategoryRepository? = null,
) :
	ViewModelProvider.NewInstanceFactory() {
	
	@Suppress("UNCHECKED_CAST")
	override fun <T: ViewModel> create(modelClass: Class<T>): T {
		if(modelClass.isAssignableFrom(HomeViewModel::class.java))
			return HomeViewModel(categoryRepository!!) as T
		else if(modelClass.isAssignableFrom(SearchResultsViewModel::class.java))
			return SearchResultsViewModel() as T
		else if(modelClass.isAssignableFrom(RecipeDetailsViewModel::class.java))
			return RecipeDetailsViewModel() as T
		else if(modelClass.isAssignableFrom(FavoritesViewModel::class.java))
			return FavoritesViewModel() as T
		else if(modelClass.isAssignableFrom(ProfileViewModel::class.java))
			return ProfileViewModel() as T
		else if(modelClass.isAssignableFrom(LoginViewModel::class.java))
			return LoginViewModel() as T
		else if(modelClass.isAssignableFrom(SignupViewModel::class.java))
			return SignupViewModel() as T
		else if(modelClass.isAssignableFrom(RecipeByCategoryViewModel::class.java))
			return RecipeByCategoryViewModel() as T
		else if(modelClass.isAssignableFrom(SearchCategoryViewModel::class.java))
			return SearchCategoryViewModel() as T
		throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
	}
}
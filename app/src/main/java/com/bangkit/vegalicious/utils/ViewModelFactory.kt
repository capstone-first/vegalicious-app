package com.bangkit.vegalicious.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.vegalicious.data.CategoryRepository
import com.bangkit.vegalicious.data.RecipeRepository
import com.bangkit.vegalicious.ui.screen.home.HomeViewModel
import com.bangkit.vegalicious.ui.screen.recipedetails.RecipeDetailsViewModel
import com.bangkit.vegalicious.ui.screen.searchresults.SearchResultsViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory() :
	ViewModelProvider.NewInstanceFactory() {

	private lateinit var recipeRepository: RecipeRepository
	private lateinit var categoryRepository: CategoryRepository
	
	constructor(recipeRepository: RecipeRepository) : this() {
		this.recipeRepository = recipeRepository
	}
	
	constructor(recipeRepository: RecipeRepository, categoryRepository: CategoryRepository) : this(recipeRepository) {
		this.categoryRepository = categoryRepository
	}
	
	@Suppress("UNCHECKED_CAST")
	override fun <T: ViewModel> create(modelClass: Class<T>): T {
		if(modelClass.isAssignableFrom(HomeViewModel::class.java))
			return HomeViewModel(recipeRepository, categoryRepository) as T
		else if(modelClass.isAssignableFrom(SearchResultsViewModel::class.java))
			return SearchResultsViewModel(recipeRepository) as T
		else if(modelClass.isAssignableFrom(RecipeDetailsViewModel::class.java))
			return RecipeDetailsViewModel(recipeRepository) as T
		throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
	}
}
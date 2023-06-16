package com.bangkit.vegalicious.models

import com.bangkit.vegalicious.data.remote.response.Category
import com.bangkit.vegalicious.data.remote.response.RecipeCategoryItem
import com.bangkit.vegalicious.data.remote.response.RecipeData

object FakeRecipes {
	val dummyRecipeData = List(30) { index ->
		RecipeData(
			id = index.toString(),
			title = "Resep $index",
			image = "https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
			description = "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.",
			calories = 180,
			fat = 8,
			sodium = 49,
			rating = 3,
			recipeCategory = listOf(
				RecipeCategoryItem(Category("Jackfruit", "1")),
				RecipeCategoryItem(Category("Curry", "2")),
				RecipeCategoryItem(Category("Indian", "3")),
				RecipeCategoryItem(Category("Lunch", "4")),
			),
		)
	}
}
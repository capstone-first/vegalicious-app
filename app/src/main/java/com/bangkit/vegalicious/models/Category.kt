package com.bangkit.vegalicious.models

data class Category(
	val title: String,
	val photoUrl: String = "https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
)

val dummyCategories = listOf(
	Category("Pescatarian"),
	Category("Peanut Free"),
	Category("Dairy Free"),
	Category("Soy Free"),
	Category("Gluten Free"),
	Category("Sides"),
	Category("Breakfast"),
	Category("Lunch"),
	Category("Dinner"),
)
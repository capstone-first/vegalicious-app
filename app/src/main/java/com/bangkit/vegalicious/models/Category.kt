package com.bangkit.vegalicious.models

data class Category(
	val id: String,
	val title: String,
	val photoUrl: String = "https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
)

val dummyCategories = listOf(
	Category("1","Pescatarian"),
	Category("2","Peanut Free"),
	Category("3","Dairy Free"),
	Category("4","Soy Free"),
	Category("5","Gluten Free"),
	Category("6","Sides"),
	Category("7","Breakfast"),
	Category("8","Lunch"),
	Category("9","Dinner"),
)
package com.bangkit.vegalicious.models

data class Recipe(
	val id: String,
	val title: String,
	val photoUrl: String,
	val tags: List<String>,
	val nutrition: Map<String, Float>,
	val description: String,
)

val dummyRecipeCards = List(30) { index ->
	Recipe(
		id = index.toString(),
		title = "Resep $index",
		photoUrl = "https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
		tags = listOf(
			"Indian",
			"Indonesian",
			"Gluten-Free",
			"Vegetarian",
			"Soy-Free",
			"Curry",
			"Western",
			"Eastern"
		),
		nutrition = mapOf(
			"fat" to 1234.2f,
			"calories" to 23.2f,
			"protein" to 334.2f,
			"sodium" to 1110f,
		),
		description = "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet."
	)
}

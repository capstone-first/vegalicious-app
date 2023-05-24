package com.bangkit.vegalicious.models

data class RecipeCard(
	val id: String,
	val title: String,
	val photoUrl: String,
	val tags: List<String>,
	val description: String,
)

val dummyRecipeCards = List(10) { index ->
	RecipeCard(
		id = index.toString(),
		title = "Resep $index",
		photoUrl = "https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
		tags = listOf(
			"Tags 1",
			"Tags 2",
			"Tags 3",
		),
		description = "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet."
	)
}

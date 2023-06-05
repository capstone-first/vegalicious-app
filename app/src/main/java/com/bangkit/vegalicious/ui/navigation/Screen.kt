package com.bangkit.vegalicious.ui.navigation

sealed class Screen(val route: String) {
	
	object Home : Screen("home")
	object Search : Screen("search?q={query}&tag1={tag1}&tag2={tag2}&tag3={tag3}") {
		fun createSearchRoute(query: String? = null, tags: List<String> = listOf()): String {
			val route = if(query != null) "search?q=$query" else "search?"
			
			val sb = StringBuilder().append(route)
			
			val i = 1
			for(tag in tags) {
				sb.append("&tag$i=tag")
			}
			return sb.toString()
		}
	}
	object Favorites : Screen("favorites")
	object Profile : Screen("profile")
	object DetailRecipe : Screen("home/{recipeId}") {
		fun createRoute(recipeId: String) = "home/$recipeId"
	}
	object Category : Screen("category") {
		fun createRoute(tag: String) = "search/$tag"
	}
}

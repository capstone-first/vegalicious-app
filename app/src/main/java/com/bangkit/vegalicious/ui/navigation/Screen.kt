package com.bangkit.vegalicious.ui.navigation

import android.util.Log

sealed class Screen(val route: String) {
	
	object Home : Screen("home")
	object Search : Screen("search?q={q}&tag1={tag1}&tag2={tag2}&tag3={tag3}") {
		fun createSearchRoute(query: String, tags: List<String> = listOf()): String {
			Log.d("TEST", "TEST START")
			val route = "search?q=$query"
			
			val sb = StringBuilder().append(route)
			
			var i = 1
			for(tag in tags) {
				sb.append("&tag$i=$tag")
				i++
			}
			Log.e("CreateSearchRoute", sb.toString())
			return sb.toString()
		}
	}
	object Favorites : Screen("favorites")
	object Profile : Screen("profile")
	object DetailRecipe : Screen("home/{recipeId}") {
		fun createRoute(recipeId: String) = "home/$recipeId"
	}
	object Category : Screen("category/{tag}") {
		fun createRoute(tag: String) = "category/$tag"
	}
}
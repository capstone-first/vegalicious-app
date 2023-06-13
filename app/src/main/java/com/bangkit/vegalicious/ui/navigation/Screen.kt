package com.bangkit.vegalicious.ui.navigation

import android.util.Log

sealed class Screen(val route: String) {
	
	object Splash : Screen("splash")
	object Login : Screen("login")
	object Signup : Screen("signup")
	object Home : Screen("home")
	object Search : Screen("search?q={q}&tag1={tag1}&tag2={tag2}&tag3={tag3}") {
		fun createSearchRoute(query: String, tags: List<String> = listOf()): String {
			val route = "search?q=$query"
			
			val sb = StringBuilder().append(route)
			
			var i = 1
			for(tag in tags) {
				sb.append("&tag$i=$tag")
				i++
			}
			
			Log.d("CreateSearchRoute", sb.toString())
			return sb.toString()
		}
	}
	object Favorites : Screen("favorites")
	object Profile : Screen("profile/{username}") {
		fun createRoute(username: String) = "profile/$username"
	}
	object DetailRecipe : Screen("home/{recipeId}") {
		fun createRoute(recipeId: String) = "home/$recipeId"
	}
	object Category : Screen("category/{tag}") {
		fun createRoute(tag: String) = "category/$tag"
	}
	
	companion object {
		val menu = listOf(
			Home.route, Search.route, Favorites.route, Profile.route
		)
	}
}

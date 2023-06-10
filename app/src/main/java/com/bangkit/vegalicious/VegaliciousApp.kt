package com.bangkit.vegalicious

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkit.vegalicious.ui.navigation.NavigationItem
import com.bangkit.vegalicious.ui.navigation.Screen
import com.bangkit.vegalicious.ui.screen.auth.login.LoginScreen
import com.bangkit.vegalicious.ui.screen.auth.signup.SignupScreen
import com.bangkit.vegalicious.ui.screen.category.CategoryScreen
import com.bangkit.vegalicious.ui.screen.favorites.FavoritesScreen
import com.bangkit.vegalicious.ui.screen.home.HomeScreen
import com.bangkit.vegalicious.ui.screen.profile.ProfileScreen
import com.bangkit.vegalicious.ui.screen.recipedetails.RecipeDetailsScreen
import com.bangkit.vegalicious.ui.screen.searchresults.SearchResultsScreen
import com.bangkit.vegalicious.ui.screen.splash.SplashScreen
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VegaliciousApp(
	modifier: Modifier = Modifier,
	navController: NavHostController = rememberNavController(),
) {
	Scaffold(
		bottomBar = { BottomBar(navController = navController) },
	) { it ->
		NavHost(
			navController = navController,
			startDestination = Screen.Splash.route,
			modifier = Modifier.padding(it),
		) {
			composable(Screen.Splash.route) {
				SplashScreen(
					navigateToHome = {
						navController.navigate(Screen.Home.route)
					},
					 navigateToLogin =  {
						navController.navigate(Screen.Login.route)
					}
				)
			}
			composable(Screen.Login.route) {
				LoginScreen(
					navigateToHome = {
						navController.navigate(Screen.Home.route)
					},
					navigateToSignup = {
						navController.navigate(Screen.Signup.route)
					}
				)
			}
			composable(Screen.Signup.route) {
				SignupScreen(
					navigateToLogin = {
						navController.navigate(Screen.Login.route)
					},
				)
			}
			composable(Screen.Home.route) {
				HomeScreen(
					navigateToDetail = {
						navController.navigate(Screen.DetailRecipe.createRoute(it))
					},
					navigateToSearch = { query, tags ->
						navController.navigate(Screen.Search.createSearchRoute(query, tags).also{Log.e("CreateSearchRoute", it) })
					},
					navigateToCategory = { tag ->
						navController.navigate(Screen.Category.createRoute(tag))
					}
				)
			}
			composable(
				route = Screen.Search.route,
				arguments = listOf(
					navArgument("q") {
						type = NavType.StringType
						defaultValue = ""
					},
					navArgument("tag1") {
						type = NavType.StringType
						nullable = true
					},
					navArgument("tag2") {
						type = NavType.StringType
						nullable = true
					},
					navArgument("tag3") {
						type = NavType.StringType
						nullable = true
					}
				)
			) {
				val query = it.arguments?.getString("q").toString()
				val tags: List<String> = listOfNotNull(
					it.arguments?.getString("tag1"),
					it.arguments?.getString("tag2"),
					it.arguments?.getString("tag3"),
				)
				Log.d("CreateSearchRoute", "Query: $query")
				SearchResultsScreen(
					query = query,
					_tags = tags,
					onSearch = { _q, _t ->
						navController.navigate(Screen.Search.createSearchRoute(_q, _t).also{Log.d("SearchRoute", "Route: $it")})
					},
					navigateToDetail = {
						navController.navigate(Screen.DetailRecipe.createRoute(it))
					},
				)
			}
			composable(
				route = Screen.Favorites.route,
				arguments = listOf(
				
				)
			) {
				FavoritesScreen(
					navigateToDetail = {
						navController.navigate(Screen.DetailRecipe.createRoute(it))
					},
				)
			}
			composable(
				route = Screen.Profile.route,
				arguments = listOf(navArgument("username") {
					type = NavType.StringType
				}),
			) {
				val username = it.arguments?.getString("username").toString()
				ProfileScreen(
					username = username,
					onClickLogout = {}
				)
			}
			composable(
				route = Screen.DetailRecipe.route,
				arguments = listOf(navArgument("recipeId") {
					type = NavType.StringType
				}),
			) {
				val id = it.arguments?.getString("recipeId") ?: "null"
				RecipeDetailsScreen(
					recipeId = id,
					navigateBack = {
						navController.navigateUp()
					},
				)
			}
			composable(
				route = Screen.Category.route,
				arguments = listOf(navArgument("tag"){
					type = NavType.StringType
				})
			) {
				val tag = it.arguments?.getString("tag") as String
				CategoryScreen(
					tag = tag,
					navigateToDetail = {
						navController.navigate(Screen.DetailRecipe.createRoute(it))
					},
				)
			}
		}
	}
}


@Composable
fun BottomBar(
	modifier: Modifier = Modifier,
	navController: NavHostController,
) {
	NavigationBar(
		modifier = modifier
	) {
		val navigationItems = listOf(
			NavigationItem(
				title = stringResource(R.string.menu_home),
				icon = Icons.Outlined.Home,
				screen = Screen.Home
			),
			NavigationItem(
				title = stringResource(R.string.menu_search),
				icon = Icons.Outlined.Search,
				screen = Screen.Search
			),
			NavigationItem(
				title = stringResource(R.string.menu_favorites),
				icon = Icons.Outlined.BookmarkBorder,
				screen = Screen.Favorites
			),
		)
		
		navigationItems.map { item ->
			NavigationBarItem(
				selected = false,
				onClick = {
						  navController.navigate(item.screen.route) {
							  popUpTo(navController.graph.findStartDestination().id) {
								  saveState = true
							  }
							  restoreState = true
							  launchSingleTop = true
						  }
					},
				
				label = { Text(item.title) },
				icon = {
					Icon(
						imageVector = item.icon,
						contentDescription = item.title
					)
				}
			)
		}
		
		NavigationBarItem(
			selected = false,
			onClick = {
				navController.navigate(Screen.Profile.createRoute("")) {
					popUpTo(navController.graph.findStartDestination().id) {
						saveState = true
					}
					restoreState = true
					launchSingleTop = true
				}
			},
			
			label = { Text(stringResource(R.string.menu_profile)) },
			icon = {
				Icon(
					imageVector = Icons.Outlined.AccountCircle,
					contentDescription = stringResource(R.string.menu_profile)
				)
			}
		)
	
	}
}



@Preview
@Composable
fun VegaliciousAppPreview() {
	VegaliciousTheme {
		Surface(
			color = MaterialTheme.colorScheme.background
		) {
			VegaliciousApp()
		}
	}
}
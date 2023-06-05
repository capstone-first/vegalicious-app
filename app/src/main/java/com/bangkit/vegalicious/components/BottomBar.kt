package com.bangkit.vegalicious.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.ui.navigation.NavigationItem
import com.bangkit.vegalicious.ui.navigation.Screen

@Composable
private fun BottomBar(
	modifier: Modifier = Modifier
) {
	NavigationBar(
		modifier = modifier
	) {
		val navigationItems = listOf(
			NavigationItem(
				title = stringResource(R.string.menu_home),
				icon = Icons.Default.Home,
				screen = Screen.Home
			),
			NavigationItem(
				title = stringResource(R.string.menu_search),
				icon = Icons.Default.Search,
				screen = Screen.Home
			),
			NavigationItem(
				title = stringResource(R.string.menu_favorites),
				icon = Icons.Default.Favorite,
				screen = Screen.Home
			),
		)
		
		
	}
}

@Preview
@Composable
fun BottomBarPreview() {

}
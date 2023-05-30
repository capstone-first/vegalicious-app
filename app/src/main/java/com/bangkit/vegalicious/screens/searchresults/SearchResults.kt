package com.bangkit.vegalicious.screens.searchresults

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.components.OutlinedSearchBar
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.models.dummyRecipes
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

class SearchResults : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VegaliciousTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					SearchResultsApp()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsApp() {
	Scaffold(
		topBar = { AppBar() }
	) {
		val data = dummyRecipes
		val query = "resep"
		LazyVerticalGrid(
			columns = GridCells.Fixed(2),
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			contentPadding = PaddingValues(horizontal = 16.dp),
			modifier = Modifier
				.padding(it)
		) {
			item(span = { GridItemSpan(maxCurrentLineSpan) }) {
				Text(
					text = "Search results for \"$query\"",
					style = MaterialTheme.typography.titleMedium.copy(
						fontWeight = FontWeight.Bold
					),
					color = MaterialTheme.colorScheme.primary,
					modifier = Modifier.padding(top = 8.dp),
				)
			}
			
			items(data) {
				RecipeItem(
					modifier = Modifier
						.width(200.dp),
					title = it.title,
					photoUrl = it.photoUrl,
					tags = it.tags,
					description = it.description,
					enableTags = false
				)
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
) {
	TopAppBar(
		title = {
			Text("Search Results")
		},
		actions = {
			Row (
				modifier = Modifier.padding(start = 8.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
				verticalAlignment = Alignment.CenterVertically
					) {
				IconButton(
					onClick = {},
					Modifier.padding(0.dp)
				) {
					Icon(Icons.Default.ArrowBack, contentDescription = null,
						Modifier.size(32.dp))
				}
				OutlinedSearchBar()
			}
		},
		modifier = Modifier.height(64.dp)
	)
	
//	Surface(
//		modifier = Modifier
//			.fillMaxWidth()
//			.height(56.dp),
//		color = MaterialTheme.colorScheme.background,
//		shadowElevation = 10.dp
//	) {
//		SearchBar()
//	}
}

@Preview
@Composable
fun AppBarPreview() {
	VegaliciousTheme {
		AppBar()
	}
}

@Preview(showBackground = true)
@Composable
fun SearchResultsPreview() {
	VegaliciousTheme {
		// A surface container using the 'background' color from the theme
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			SearchResultsApp()
		}
	}
}
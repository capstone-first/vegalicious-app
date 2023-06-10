package com.bangkit.vegalicious.ui.screen.searchresults

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.vegalicious.components.OutlinedSearchBar
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.models.FakeRecipes.dummyRecipes
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.Injection
import com.bangkit.vegalicious.utils.ViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(
	query: String = "",
	_tags: List<String> = listOf(),
	onSearch: (String, List<String>) -> Unit,
	viewModel: SearchResultsViewModel = viewModel(
		factory = ViewModelFactory(
			Injection.provideRecipeRepository(),
		)
	),
	navigateToDetail: (String) -> Unit,
) {
	Log.e("SearchResultsScreen", "After search: $query")
	var input by remember { mutableStateOf(query) }
	var tags by remember { mutableStateOf(_tags) }
	Scaffold(
		topBar = { AppBar(
			input = input,
			onValueChange = { newValue ->
				input = newValue
			},
			onSearch = {
				Log.d("Before", "Before search: $input")
				onSearch(input, tags)
			}
		) }
	) { paddingValues ->
		val uiStateRecipe by remember { viewModel.uiStateRecipe }.collectAsState(initial = UiState.Loading)
		
		LazyVerticalGrid(
			columns = GridCells.Fixed(2),
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			contentPadding = PaddingValues(horizontal = 16.dp),
			modifier = Modifier
				.padding(paddingValues)
		) {
			item(span = { GridItemSpan(maxCurrentLineSpan) }) {
				Text(
					text = if(query != "") { "Search results for \"$query\":" } else {"All recipes:"},
					style = MaterialTheme.typography.titleMedium.copy(
						fontWeight = FontWeight.Bold
					),
					color = MaterialTheme.colorScheme.primary,
					modifier = Modifier.padding(top = 8.dp),
				)
				Bundle()
			}
			
			
			when(uiStateRecipe) {
				is UiState.Loading -> {
					viewModel.searchRecipes(input, tags)
				}
				is UiState.Success -> {
					items((uiStateRecipe as UiState.Success<List<Recipe>>).data) {
						RecipeItem(
							modifier = Modifier
								.width(200.dp),
							title = it.title,
							photoUrl = it.photoUrl,
							tags = it.tags,
							description = it.description,
							enableTags = true,
							onClick = { navigateToDetail(it.id) }
						)
					}
				}
				is UiState.Error -> {
				
				}
			}
			
			
			
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
	input: String = "",
	onValueChange: (String) -> Unit = {},
	onSearch: () -> Unit = {}
) {
	TopAppBar(
		title = {
			Text("Search Results")
		},
		actions = {
			Row (
				modifier = Modifier.padding(start = 8.dp, end = 16.dp),
				verticalAlignment = Alignment.CenterVertically
					) {
				IconButton(
					onClick = {},
					Modifier.padding(0.dp)
				) {
					Icon(Icons.Default.ArrowBack, contentDescription = null,
						Modifier.size(28.dp))
				}
				OutlinedSearchBar(
					value = input,
					onValueChange = onValueChange,
					onSearch = onSearch
				)
			}
		},
		modifier = Modifier.padding(vertical = 8.dp)
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
		AppBar("Resep")
	}
}

@Preview(showBackground = true)
@Composable
fun SearchResultsScreenPreview() {
	VegaliciousTheme {
		// A surface container using the 'background' color from the theme
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			SearchResultsScreen("Resep", listOf(), {_, _ ->}, navigateToDetail = {})
		}
	}
}
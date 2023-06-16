package com.bangkit.vegalicious.ui.screen.category.searchcategory

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.bangkit.vegalicious.components.SearchCategoryItem
import com.bangkit.vegalicious.data.remote.response.CategoryListResponse
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.ViewModelFactory


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchCategoryResultsScreen(
	query: String = "",
	onSearch: (String) -> Unit,
	viewModel: SearchCategoryViewModel = viewModel(
		factory = ViewModelFactory(
		)
	),
	navigateToCategory: (String) -> Unit,
) {
	Log.d("SearchResultsScreen", "After search: $query")
	var input by remember { mutableStateOf(query) }
	Scaffold(
		topBar = { AppBar(
			input = input,
			onValueChange = { newValue ->
				input = newValue
			},
			onSearch = {
				Log.d("Before", "Before search: $input")
				onSearch(input)
			}
		) }
	) { paddingValues ->
		val uiStateCategories by remember { viewModel.uiStateCategories }.collectAsState(initial = UiState.Loading)
		LazyVerticalStaggeredGrid(
			columns = StaggeredGridCells.Fixed(3),
			verticalItemSpacing = 4.dp,
			horizontalArrangement = Arrangement.spacedBy(4.dp),
			modifier = Modifier
				.padding(paddingValues)
				.padding(horizontal = 8.dp)
			) {
			
			item(span = StaggeredGridItemSpan.Companion.FullLine) {
				Text(
					text = if(query != "") { "Search results for \"$query\":" } else {"All categories:"},
					style = MaterialTheme.typography.titleMedium.copy(
							fontWeight = FontWeight.Bold
						),
					color = MaterialTheme.colorScheme.primary,
					modifier = Modifier.padding(top = 8.dp),
				)
			}
			
			when(uiStateCategories) {
				is UiState.Loading -> {
					viewModel.getCategories(query)
				}
				
				is UiState.Success -> {
					items(items = (uiStateCategories as UiState.Success<CategoryListResponse>).data.data) {
						SearchCategoryItem(
							modifier = Modifier
								.width(200.dp),
							title = it.name,
							id = it.id,
							onClick = { navigateToCategory(it.name) }
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
		AppBar("Kategori")
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
			SearchCategoryResultsScreen("Kategori", {_ -> }, navigateToCategory = {})
		}
	}
}
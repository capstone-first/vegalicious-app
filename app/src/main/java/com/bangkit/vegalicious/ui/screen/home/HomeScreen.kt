package com.bangkit.vegalicious.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.components.CategoryItem
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.components.SearchBar
import com.bangkit.vegalicious.components.SectionText
import com.bangkit.vegalicious.data.remote.response.RecipeData
import com.bangkit.vegalicious.models.Category
import com.bangkit.vegalicious.models.FakeRecipes
import com.bangkit.vegalicious.models.topCategories
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.Injection
import com.bangkit.vegalicious.utils.ViewModelFactory

@Composable
fun HomeScreen(
	viewModel: HomeViewModel = viewModel(
		factory = ViewModelFactory(
			Injection.provideCategoryRepository()
		)
	),
	navigateToDetail: (String) -> Unit,
	navigateToCategory: (String) -> Unit,
	navigateToSearchCategory: (String) -> Unit,
	navigateToSearch: (String) -> Unit,
) {
	var searchInput by remember { mutableStateOf("") }
	
	Column(
		modifier = Modifier
			.verticalScroll(rememberScrollState())
			.padding(bottom = 16.dp)
	) {
		Banner(value = searchInput, onValueChange = { newValue -> searchInput = newValue}, onIconClicked = {
			navigateToSearch(searchInput)
		})
		SectionText(title = stringResource(id = R.string.main_category_section))
		
		viewModel.uiStateCategory.collectAsState(initial = UiState.Loading).value.let { uiState ->
			when(uiState) {
				is UiState.Loading -> {
					Box(contentAlignment = Alignment.Center, modifier = Modifier
						.fillMaxSize()
						.padding(top = 32.dp)) {
						CircularProgressIndicator(modifier = Modifier.padding(8.dp))
					}
					viewModel.getTopCategories()
				}
				is UiState.Success -> {
					CategoryRow(listCategory = uiState.data, navigateToCategory = navigateToCategory, navigateToSearchCategory = navigateToSearchCategory)
				}
				is UiState.Error -> {
				
				}
			}
		}
		
		SectionText(title = stringResource(id = R.string.main_recommended_section))
		viewModel.uiStateRecipe.collectAsState(initial = UiState.Loading).value.let { uiState ->
			when(uiState) {
				is UiState.Loading -> {
					Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
						.fillMaxWidth()
						.padding(top = 32.dp)) {
						CircularProgressIndicator(modifier = Modifier.padding(8.dp))
					}
					viewModel.getAllRecipes()
				}
				is UiState.Success -> {
					RecommendedRow(listRecipes = uiState.data.data, navigateToDetail = navigateToDetail,)
				}
				is UiState.Error -> {
				
				}
			}
		}
	}
}

@Preview
@Composable
fun HomeScreenPreview() {
	VegaliciousTheme {
		Surface(
			color = MaterialTheme.colorScheme.background
		) {
			HomeScreen(
				navigateToDetail = {
				
				},
				navigateToCategory = {},
				navigateToSearchCategory = {},
				navigateToSearch = {}
			)
		}
	}
}

@Composable
fun Banner(
	modifier: Modifier = Modifier,
	value: String,
	onValueChange: (String) -> Unit,
	onIconClicked: () -> Unit
) {
	Box(modifier = modifier) {
		Image(
			painter = painterResource(id = R.drawable.banner),
			contentDescription = "Banner Image",
			contentScale = ContentScale.Crop,
			modifier = Modifier.height(220.dp)
		)
		SearchBar(value = value, onValueChange = onValueChange, onClick = onIconClicked)
	}
}

@Composable
fun CategoryRow(
	listCategory: List<Category>,
	navigateToCategory: (String) -> Any,
	navigateToSearchCategory: (String) -> Any,
	modifier: Modifier = Modifier
) {
	LazyRow(
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = PaddingValues(horizontal = 16.dp),
		modifier = modifier,
	) {
		items(listCategory) { category ->
			CategoryItem(
				title = category.title,
				photoUrl = category.photoUrl,
				onClick = {
					val tag = category.title
					navigateToCategory(tag)
				}
			)
		}
		item() {
			CategoryItem(
				title = "More Categories",
				photoUrl = "",
				onClick = {
					navigateToSearchCategory("")
				}
			)
		}
	}
}

@Preview
@Composable
fun CategoryRowPreview() {
	VegaliciousTheme {
		CategoryRow(listCategory = topCategories, {}, {})
	}
}

@Composable
fun RecommendedRow(
	listRecipes: List<RecipeData>,
	modifier: Modifier = Modifier,
	navigateToDetail: (String) -> Any,
) {
	LazyHorizontalGrid(
		modifier = modifier.height(420.dp),
		rows = GridCells.Fixed(2),
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		contentPadding = PaddingValues(horizontal = 16.dp),
	) {
		items(listRecipes) {
			RecipeItem(
				modifier = Modifier
					.width(180.dp),
				title = it.title,
				photoUrl = it.image,
				tags = it.recipeCategory,
				enableTags = false,
				onClick = { navigateToDetail(it.id) }
			)
		}
	}
}

@Preview
@Composable
fun RecommendedRowPreview() {
	VegaliciousTheme(dynamicColor = false) {
		Surface(
			color = MaterialTheme.colorScheme.background
		) {
			RecommendedRow(
				listRecipes = FakeRecipes.dummyRecipeData,
				navigateToDetail = {
				
				},
			)
		}
	}
}
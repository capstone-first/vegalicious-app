package com.bangkit.vegalicious.ui.screen.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.components.SectionText
import com.bangkit.vegalicious.data.remote.response.RecipeResponse
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.ViewModelFactory

@Composable
fun CategoryScreen(
	tag: String,
	onBackButton: () -> Unit = {},
	navigateToDetail: (String) -> Unit,
	viewModel: RecipeByCategoryViewModel = viewModel(
		factory = ViewModelFactory()
	),
) {
	val uiStateRecipe by remember { viewModel.uiStateRecipe }.collectAsState(initial = UiState.Loading)
	
	LazyVerticalGrid(
		columns = GridCells.Fixed(2),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier
			.padding(horizontal = 16.dp)
	) {
		
		item(span = { GridItemSpan(maxCurrentLineSpan) }) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.padding(top = 16.dp),
			) {
				SectionText(title = "Category: $tag", modifier = Modifier.padding(horizontal = 0.dp))
			}
		}
		when(uiStateRecipe) {
			is UiState.Loading -> {
				item(span = { GridItemSpan(maxCurrentLineSpan) }) { CircularProgressIndicator(modifier = Modifier.padding(8.dp)) }
				viewModel.getRecipes(category = tag)
			}
			
			is UiState.Success -> {
				items((uiStateRecipe as UiState.Success<RecipeResponse>).data.data) {
					RecipeItem(
						modifier = Modifier
							.width(200.dp),
						title = it.title,
						photoUrl = it.image,
						tags = it.recipeCategory,
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

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
	VegaliciousTheme {
		// A surface container using the 'background' color from the theme
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			CategoryScreen("Indian", navigateToDetail = {})
		}
	}
}
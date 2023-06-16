package com.bangkit.vegalicious.ui.screen.favorites

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.components.SectionText
import com.bangkit.vegalicious.data.remote.response.GetBookmarkResponse
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.ViewModelFactory

@Composable
fun FavoritesScreen(
	viewModel: FavoritesViewModel = viewModel(
		factory = ViewModelFactory()
	),
	navigateToDetail: (String) -> Unit = {},
) {
	val uiStateFavorite by viewModel.uiStateFavorite .collectAsState(initial = UiState.Loading)
	
	LazyVerticalGrid(
		columns = GridCells.Fixed(2),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier
			.padding(horizontal = 16.dp)
	) {
		item(span = { GridItemSpan(maxCurrentLineSpan) }) {
			SectionText(title = stringResource(id = R.string.favorites), modifier = Modifier.padding(top = 16.dp))
		}
		
		when(uiStateFavorite) {
			is UiState.Loading -> {
				viewModel.getFavorites()
				Log.d("FavoritesScreen Data", "Loading")
			}
			is UiState.Success -> {
				if((uiStateFavorite as UiState.Success<GetBookmarkResponse>).data.data.isEmpty()) {
					item(span = { GridItemSpan(maxCurrentLineSpan) }) {
						Image(
							painter = painterResource(id = R.drawable.notfound),
							contentDescription = "NotFound",
							contentScale = ContentScale.FillHeight,
							modifier = Modifier
								.height(206.dp)
								.padding(vertical = 32.dp)
						)
					}
					item(span = { GridItemSpan(maxCurrentLineSpan) }) {
						Text(
							text = "You have no favorite recipe!",
							style = MaterialTheme.typography.titleSmall,
							textAlign = TextAlign.Center
						)
					}
				} else {
					items((uiStateFavorite as UiState.Success<GetBookmarkResponse>).data.data) {
						RecipeItem(
							title = it.recipe.title,
							photoUrl = it.recipe.image,
							tags = listOf(),
							enableTags = false,
							onClick = { navigateToDetail(it.recipe.id) },
							enableFavorite = true,
							isFavorite = true,
							onFavoriteClick = {
								viewModel.deleteFavorite(it.id)
							}
						)
					}
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
			FavoritesScreen()
		}
	}
}
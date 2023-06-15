package com.bangkit.vegalicious.ui.screen.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.components.SectionText
import com.bangkit.vegalicious.data.local.entity.RecipeAndTag
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.Injection
import com.bangkit.vegalicious.utils.ViewModelFactory

@Composable
fun FavoritesScreen(
	viewModel: FavoritesViewModel = viewModel(
		factory = ViewModelFactory(
			favoriteRepository = Injection.provideFavoriteRepository(LocalContext.current),
		)
	),
	navigateToDetail: (String) -> Unit = {},
) {
	val uiStateFavorite by remember { viewModel.uiStateFavorite }.collectAsState(initial = UiState.Loading)
	
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
			}
			is UiState.Success -> {
				val data = (uiStateFavorite as UiState.Success<List<RecipeAndTag>>).data
				if(data.isEmpty()) {
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
							text = "Data Not Found",
							style = MaterialTheme.typography.titleSmall,
							textAlign = TextAlign.Center
						)
					}
				} else {
					item(span = { GridItemSpan(maxCurrentLineSpan) }) {
						Text(
							text = "Data is not empty",
							style = MaterialTheme.typography.titleSmall,
							textAlign = TextAlign.Center
						)
					}
					items(data) {
						RecipeItem(
							modifier = Modifier
								.width(200.dp),
							title = it.favoriteRecipeEntity.title,
							photoUrl = it.favoriteRecipeEntity.image
								?: "https://st.depositphotos.com/2934765/53192/v/600/depositphotos_531920820-stock-illustration-photo-available-vector-icon-default.jpg",
							tags = listOf(),
//							tags = it.tags.let { tags -> //Move to a function *edit
//								val tagsString: MutableList<String> = mutableListOf()
//								tags.forEach { tag ->
//									tagsString.add(tag.tagText)
//								}
//								tagsString
//							},
							enableTags = true,
							onClick = { navigateToDetail(it.favoriteRecipeEntity.id) },
							enableFavorite = true
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
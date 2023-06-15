package com.bangkit.vegalicious.ui.screen.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.components.SectionText
import com.bangkit.vegalicious.models.FakeRecipes.dummyRecipes
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

@Composable
fun CategoryScreen(
	tag: String,
	onBackButton: () -> Unit = {},
	navigateToDetail: (String) -> Unit,
) {
	val data = dummyRecipes
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
				IconButton(
					onClick = {},
					Modifier.padding(0.dp)
				) {
					Icon(
						Icons.Default.ArrowBack, contentDescription = null,
						Modifier.size(28.dp).padding(horizontal = 0.dp))
				}
				SectionText(title = "Category: " + tag, modifier = Modifier.padding(horizontal = 0.dp))
			}
		}
		items(data) {
			RecipeItem(
				modifier = Modifier
					.width(200.dp),
				title = it.title,
				photoUrl = it.image,
				tags = listOf(),
				enableTags = true,
				onClick = { navigateToDetail(it.id) }
			)
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
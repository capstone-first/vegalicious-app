package com.bangkit.vegalicious.screens.favorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.models.dummyRecipes
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.dicoding.jetcoffee.components.SectionText

class FavoriteActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VegaliciousTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					FavoriteApp()
				}
			}
		}
	}
}

@Composable
fun FavoriteApp() {
	val data = dummyRecipes
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

@Preview(showBackground = true)
@Composable
fun FavoriteAppPreview() {
	VegaliciousTheme {
		// A surface container using the 'background' color from the theme
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			FavoriteApp()
		}
	}
}
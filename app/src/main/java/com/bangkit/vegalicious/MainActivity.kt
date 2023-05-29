package com.bangkit.vegalicious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.components.CategoryItem
import com.bangkit.vegalicious.components.RecipeItem
import com.bangkit.vegalicious.models.Category
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.models.dummyCategories
import com.bangkit.vegalicious.models.dummyRecipes
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.dicoding.jetcoffee.components.SearchBar
import com.dicoding.jetcoffee.components.SectionText

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VegaliciousTheme(dynamicColor = false) {
				// A surface container using the 'background' color from the theme
				Surface(
					color = MaterialTheme.colorScheme.background
				) {
					VegaliciousApp()
				}
			}
		}
	}
}

@Composable
fun VegaliciousApp() {
	Column(
		modifier = Modifier
			.verticalScroll(rememberScrollState())
	) {
		Banner()
		CategoryRow(listCategory = dummyCategories)
		RecommendedRow(listRecipes = dummyRecipes)
	}
}

@Preview
@Composable
fun VegaliciousAppPreview() {
	VegaliciousTheme {
		Surface(
			color = MaterialTheme.colorScheme.background
		) {
			VegaliciousApp()
		}
	}
}

@Composable
fun Banner(
	modifier: Modifier = Modifier
) {
	Box(modifier = modifier) {
		Image(
			painter = painterResource(id = R.drawable.banner),
			contentDescription = "Banner Image",
			contentScale = ContentScale.Crop,
			modifier = Modifier.height(220.dp)
		)
		SearchBar()
	}
}

@Composable
fun CategoryRow(
	listCategory: List<Category>,
	modifier: Modifier = Modifier
) {
	SectionText(title = stringResource(id = R.string.main_category_section))
	LazyRow(
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = PaddingValues(horizontal = 16.dp),
		modifier = modifier,
	) {
		items(listCategory) {
			CategoryItem(title = it.title, photoUrl = "https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg")
		}
	}
}

@Preview
@Composable
fun CategoryRowPreview() {
	VegaliciousTheme() {
		CategoryRow(listCategory = dummyCategories)
	}
}

@Composable
fun RecommendedRow(
	listRecipes: List<Recipe>,
	modifier: Modifier = Modifier
) {
	SectionText(title = stringResource(id = R.string.main_recommended_section))
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
					.width(200.dp),
				title = it.title,
				photoUrl = it.photoUrl,
				tags = it.tags,
				description = it.description,
				enableTags = false)
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
			RecommendedRow(listRecipes = dummyRecipes)
		}
	}
}
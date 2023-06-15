package com.bangkit.vegalicious.ui.screen.recipedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.components.SectionText
import com.bangkit.vegalicious.models.Recipe
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.Injection
import com.bangkit.vegalicious.utils.ViewModelFactory

@Composable
fun RecipeDetailsScreen(
	viewModel: RecipeDetailsViewModel = viewModel(
		factory = ViewModelFactory(
			Injection.provideRecipeRepository()
		)
	),
	recipeId: String,
	navigateBack: () -> Unit,
) {
	viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
		when(it) {
			is UiState.Loading -> {
				viewModel.getRecipeById(recipeId)
			}
			is UiState.Success -> {
				val data = it.data
				Box(
					contentAlignment = Alignment.TopCenter,
					modifier = Modifier
						.verticalScroll(rememberScrollState())
						.padding(bottom = 180.dp),
				) {
					Header(photoUrl = data.image)
					Column(
						modifier = Modifier
							.padding(horizontal = 16.dp)
							.offset(y = 170.dp),
						verticalArrangement = Arrangement.spacedBy(16.dp),
					) {
						TitleRow(data)
						NutritionFactsRow(data = data.nutrition)
						IngredientsRow(data.ingredients)
						DirectionsRow(data.directions)
					}
				}
			}
			is UiState.Error -> {
			
			}
		}
	}
}

@Composable
fun Header(
	photoUrl: String,
) {
	Box(
		contentAlignment = Alignment.TopStart
	) {
		AsyncImage(
			model = photoUrl,
			contentScale = ContentScale.Crop,
			contentDescription = "Recipe name",
			modifier = Modifier.height(210.dp)
		)
		IconButton(
			onClick = { /*TODO*/ },
//				modifier = Modifier
//					.clip(CircleShape)
		) {
			Icon(
				imageVector = Icons.Default.ArrowBack,
				tint = Color.White,
				contentDescription = null,
				modifier = Modifier
					.size(20.dp)
			)
		}
	}
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TitleRow (
	data: Recipe
){
	ElevatedCard(
		modifier = Modifier
			.fillMaxWidth(),
		colors = CardDefaults.elevatedCardColors(
			containerColor = MaterialTheme.colorScheme.background
		),
	) {
		Column(
			modifier = Modifier
				.padding(horizontal = 24.dp, vertical = 16.dp)
				.fillMaxWidth()
		) {
			Row() {
				Text(
					modifier = Modifier
						.padding(bottom = 8.dp)
						.fillMaxWidth()
						.weight(1f),
					text = data.title,
					style = MaterialTheme.typography.headlineMedium.copy(
						fontWeight = FontWeight.Bold
					)
				)
				IconButton(
					onClick = { /*TODO*/ },
					colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.surface),
					modifier = Modifier
						.padding(4.dp)
						.clip(CircleShape)
						.size(32.dp)
				) {
					Icon(
						painterResource(id = R.drawable.bookmark_border),
						contentDescription = null,
						modifier = Modifier
							.size(24.dp)
					)
				}
			}
			FlowRow(
				modifier = Modifier
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(4.dp),
			) {
				for(tag in data.tags) {
					Card(
						modifier = Modifier
							.padding(bottom = 4.dp),
						colors = CardDefaults.cardColors(
							containerColor = MaterialTheme.colorScheme.surfaceVariant
						),
					) {
						Text(
							modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
							text = tag,
							style = MaterialTheme.typography.labelSmall
						)
						
					}
				}
			}
			Text(
				modifier = Modifier
					.padding(top = 8.dp)
					.fillMaxWidth(),
				text = data.description,
				style = MaterialTheme.typography.bodyMedium
			)
		}
	}
}

@Composable
fun NutritionFactsRow(data: Map<String, Float>) {
	Column(
		Modifier.padding(horizontal = 24.dp)
	) {
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 4.dp)
		) {
			Text(
				"Total Calories ${data["calories"]}mg",
				style = MaterialTheme.typography.labelLarge
			)
			Text(
				"Total Fat ${data["fat"]}mg",
				style = MaterialTheme.typography.labelLarge
			)
		}
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			Text(
				"Total Protein ${data["protein"]}mg",
				style = MaterialTheme.typography.labelLarge
			)
			Text(
				"Total Sodium ${data["sodium"]}mg",
				style = MaterialTheme.typography.labelLarge
			)
		}
	}
}

@Composable
fun IngredientsRow(ingredients: List<String>) {
	ElevatedCard(
		modifier = Modifier
			.fillMaxWidth(),
		colors = CardDefaults.elevatedCardColors(
			containerColor = MaterialTheme.colorScheme.background
		),
	) {
		Column(
			modifier = Modifier
				.padding(horizontal = 24.dp, vertical = 16.dp)
				.fillMaxWidth()
		) {
			SectionText(title = "Ingredients", modifier = Modifier)
			for(item in ingredients) {
				Text(
					text = item,
					modifier = Modifier
						.padding(top = 8.dp)
						.fillMaxWidth(),
					style = MaterialTheme.typography.bodyMedium
				)
			}
		}
	}
}

@Composable
fun DirectionsRow(directions: List<String>) {
	ElevatedCard(
		modifier = Modifier
			.fillMaxWidth(),
		colors = CardDefaults.elevatedCardColors(
			containerColor = MaterialTheme.colorScheme.background
		),
	) {
		Column(
			modifier = Modifier
				.padding(horizontal = 24.dp, vertical = 16.dp)
				.fillMaxWidth()
		) {
			SectionText(title = "Directions", modifier = Modifier)
			for(item in directions) {
				Text(
					text = item,
					modifier = Modifier
						.padding(top = 8.dp)
						.fillMaxWidth(),
					style = MaterialTheme.typography.bodyMedium
				)
				Divider(
					Modifier.padding(top = 8.dp)
				)
			}
		}
	}
}

@Preview
@Composable
fun RecipeDetailsScreenPreview() {
	VegaliciousTheme(dynamicColor = false) {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.surface
		) {
			RecipeDetailsScreen(
				recipeId = "1",
				navigateBack = {}
			)
		}
	}
}
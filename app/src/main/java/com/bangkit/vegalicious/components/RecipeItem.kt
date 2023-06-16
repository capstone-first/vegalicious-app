package com.bangkit.vegalicious.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.data.remote.response.Category
import com.bangkit.vegalicious.data.remote.response.RecipeCategoryItem
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeItem(
	title: String,
	photoUrl: String,
	tags: List<RecipeCategoryItem>,
	modifier: Modifier = Modifier,
	enableTags: Boolean,
	enableFavorite: Boolean = false,
	onClick: () -> Unit = {},
) {
	ElevatedCard(
		modifier = modifier.height((if(enableTags)220 else 200).dp),
		colors = CardDefaults.elevatedCardColors(
			containerColor = MaterialTheme.colorScheme.background
		),
		onClick = onClick
	) {
		Box(
			contentAlignment = Alignment.TopEnd,
		) {
			AsyncImage(
				model = photoUrl,
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.height(120.dp)
			)
			if(enableFavorite) {
				IconButton(
					onClick = { /*TODO*/ },
					colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.surface),
					modifier = Modifier
						.padding(4.dp)
						.clip(CircleShape)
						.size(24.dp)
				) {
					Icon(
						painterResource(id = R.drawable.bookmark_border),
						contentDescription = null,
						modifier = Modifier
							.size(16.dp)
					)
				}
			}
			
		}
		
		Column(
			horizontalAlignment = Alignment.Start
		) {
			Text(
				text = title,
				style = MaterialTheme.typography.titleSmall.copy(
					fontWeight = FontWeight.ExtraBold
				),
				modifier = Modifier
					.padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 4.dp),
				maxLines = 3,
				overflow = TextOverflow.Ellipsis
			)
			if(enableTags) {
				LazyRow(
					horizontalArrangement = Arrangement.spacedBy(2.dp),
					contentPadding = PaddingValues(horizontal = 8.dp),
				) {
					items(tags) {
						Card(
							colors = CardDefaults.cardColors(
								containerColor = MaterialTheme.colorScheme.surfaceVariant
							),
						) {
							Text(
								modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
								text = it.category.name,
								style = MaterialTheme.typography.labelSmall
							)
							
						}
					}
				}
			}
//			Text(
//				text = description,
//				style = MaterialTheme.typography.bodySmall,
//				maxLines = 2,
//				overflow = TextOverflow.Ellipsis,
//				modifier = Modifier
//					.padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
//			)
		}
		
	}
}

@Preview
@Composable
fun RecipeItemTruePreview() {
	VegaliciousTheme {
		RecipeItem(
			"Jackfruit Curry",
			"https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
			listOf(
				RecipeCategoryItem(Category("Jackfruit", "1")),
				RecipeCategoryItem(Category("Curry", "2")),
				RecipeCategoryItem(Category("Indian", "3")),
				RecipeCategoryItem(Category("Lunch", "4")),
			),
//			"This recipe was excerpted from 'Chetna's Healthy Indian: Vegetarian' by Chetna Makan.",
			enableTags = true
		)
	}
}

@Preview
@Composable
fun RecipeItemFalsePreview() {
	VegaliciousTheme {
		RecipeItem(
			"Jackfruit Curry",
			"https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
			listOf(
				RecipeCategoryItem(Category("Jackfruit", "1")),
				RecipeCategoryItem(Category("Curry", "2")),
				RecipeCategoryItem(Category("Indian", "3")),
				RecipeCategoryItem(Category("Lunch", "4")),
			),
//			"This recipe was excerpted from 'Chetna's Healthy Indian: Vegetarian' by Chetna Makan.",
			enableTags = false
		)
	}
}
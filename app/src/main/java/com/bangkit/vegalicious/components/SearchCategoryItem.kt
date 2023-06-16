package com.bangkit.vegalicious.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

@Composable
fun SearchCategoryItem(
	title: String,
	id: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	) {
	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
			.clickable {
				onClick()
			}
			.fillMaxWidth()
			.clip(RoundedCornerShape(8.dp))
			.background(color = MaterialTheme.colorScheme.primary)
			.padding(8.dp)
	) {
		Text(
			text = title,
			style = MaterialTheme.typography.bodyMedium.copy(
				fontWeight = FontWeight.Bold
			),
			color = Color.White,
			modifier = modifier,
			textAlign = TextAlign.Center
		)
	}
}

@Preview
@Composable
fun SearchCategoryItemPreview() {
	VegaliciousTheme {
		SearchCategoryItem(
			"Indian",
			"1",
			onClick = {},
		)
	}
}
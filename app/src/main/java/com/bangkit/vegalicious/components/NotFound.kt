package com.bangkit.vegalicious.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

@Composable
fun NotFound(
	imageId: Int = R.drawable.notfound,
	text: String = "Data Not Found"
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Image(
			painter = painterResource(id = imageId),
			contentDescription = "NotFound",
			contentScale = ContentScale.FillHeight,
			modifier = Modifier.height(206.dp).padding(vertical = 32.dp)
		)
		Text(
			text = "Data Not Found",
			style = MaterialTheme.typography.titleSmall,
		)
	}
}

@Preview
@Composable
fun NotFoundPreview() {
	VegaliciousTheme {
		Surface(
			color = MaterialTheme.colorScheme.background
		) {
			NotFound()
		}
	}
}
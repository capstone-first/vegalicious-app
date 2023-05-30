package com.bangkit.vegalicious.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
	modifier: Modifier = Modifier,
) {
	TextField(
		value = "",
		onValueChange = {},
		leadingIcon = {
			Icon(
				imageVector = Icons.Default.Search,
				contentDescription = null,
			)
		},
		colors = TextFieldDefaults.textFieldColors(
			containerColor = MaterialTheme.colorScheme.surface,
			disabledIndicatorColor = Color.Transparent,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
		),
		placeholder = {
			Text(stringResource(R.string.search_hint))
		},
		modifier = modifier
			.padding(16.dp)
			.fillMaxWidth()
			.heightIn(min = 48.dp),
		shape = RoundedCornerShape(27.dp)
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedSearchBar(
	modifier: Modifier = Modifier,
) {
	OutlinedTextField(
		value = "",
		onValueChange = {  },
		modifier = modifier
			.fillMaxWidth()
			.height(56.dp),
		placeholder = {
			Text(
				text = stringResource(R.string.search_hint),
				style = MaterialTheme.typography.bodyMedium
			)
		},
		leadingIcon = {
			Icon(
				imageVector = Icons.Default.Search,
				contentDescription = null,
				modifier = Modifier.height(48.dp)
			)
		},
		colors = TextFieldDefaults.textFieldColors(
			containerColor = MaterialTheme.colorScheme.surface,
		),
		shape = RoundedCornerShape(24.dp)
	)
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
	VegaliciousTheme() {
		SearchBar()
	}
}
package com.bangkit.vegalicious.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.vegalicious.ui.screen.ui.theme.VegaliciousTheme

class Test : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VegaliciousTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					SearchScreen()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableA(text: String, onTextChanged: (String) -> Unit) {
	TextField(
		value = text,
		onValueChange = onTextChanged,
		label = { Text("Enter text") }
	)
}

@Composable
fun ComposableB(text: String, onTextChanged: (String) -> Unit) {
	ComposableA(text = text, onTextChanged = onTextChanged)
}

@Composable
fun SearchScreen() {
	var searchText by remember { mutableStateOf("") }
	
	Column {
		ComposableB(
			text = searchText,
			onTextChanged = { newText -> searchText = newText }
		)
		
		Button(
			onClick = { /* Lakukan pencarian */ }
		) {
			Text("Search")
		}
	}
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	VegaliciousTheme {
		SearchScreen()
	}
}
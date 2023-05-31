package com.bangkit.vegalicious.screens.auth.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.components.AnnotatedClickableText
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

class LoginActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VegaliciousTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					LoginApp()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginApp(modifier: Modifier = Modifier) {
	var emailInput by remember { mutableStateOf("") }
	var passwordInput by remember { mutableStateOf("")}
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier.padding(horizontal = 16.dp)
	) {
		Image(
			painter = painterResource(id = R.drawable.loginimage),
			contentDescription = "Login Image",
			contentScale = ContentScale.Crop,
			modifier = Modifier.height(206.dp)
		)
		Text(
			text = "Sign In",
			style = MaterialTheme.typography.titleLarge,
			color = MaterialTheme.colorScheme.primary,
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = "Sign in and start cooking with us!",
			style = MaterialTheme.typography.bodySmall,
			color = MaterialTheme.colorScheme.onSurfaceVariant,
			modifier = Modifier.fillMaxWidth()
		)
		TextField(
			value = emailInput,
			onValueChange = { emailInput = it },
			trailingIcon = {
			   if(emailInput.isNotEmpty()) {
				   IconButton(
					   onClick = { emailInput = "" },
				   ) {
					   Icon(
						   Icons.Default.Cancel,
						   contentDescription = "Clear"
					   )
				   }
			   }
			},
			modifier = Modifier
				.fillMaxWidth(),
			label = { Text("Email")},
			placeholder = { Text("example@email.com")}
		)
		TextField(
			value = passwordInput,
			onValueChange = { passwordInput = it },
			trailingIcon = {
				if(passwordInput.isNotEmpty()) {
					IconButton(
						onClick = { passwordInput = "" },
					) {
						Icon(
							Icons.Default.Cancel,
							contentDescription = "Clear"
						)
					}
				}
			},
			modifier = Modifier
				.fillMaxWidth(),
			label = { Text("Password")},
			placeholder = { Text("yourpass123")}
		)
		Button(onClick = { /*TODO*/ }, Modifier.width(156.dp)) {
			Text("Sign In")
		}
		Divider()
		AnnotatedClickableText(text1 = "Don't have an account? ", text2 = "Click here to Sign Up!", action = { /*TODO*/ })
	}
}

@Preview(showBackground = true)
@Composable
fun LoginAppPreview() {
	VegaliciousTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			LoginApp()
		}
	}
}
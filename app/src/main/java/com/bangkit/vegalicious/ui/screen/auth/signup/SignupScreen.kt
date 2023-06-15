package com.bangkit.vegalicious.ui.screen.auth.signup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.components.AnnotatedClickableText
import com.bangkit.vegalicious.ui.common.UiState
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.StoreUserData
import com.bangkit.vegalicious.utils.ViewModelFactory
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
	modifier: Modifier = Modifier,
	navigateToLogin: () -> Unit,
	navigateToHome: () -> Unit,
	signupViewModel: SignupViewModel = viewModel(
		factory = ViewModelFactory()
	)
) {
	var nameInput by remember { mutableStateOf("") }
	var emailInput by remember { mutableStateOf("") }
	var passwordInput by remember { mutableStateOf("")}
	var confirmPasswordInput by remember { mutableStateOf("")}
	
	val context = LocalContext.current
	val scope = rememberCoroutineScope()
	val dataStore = StoreUserData(context)
	
	signupViewModel.uiStateRegister.collectAsState(initial = UiState.Loading).value.let { uiState ->
		when(uiState) {
			is UiState.Loading -> {
				// TODO: tampilkan loading
			}
			is UiState.Success -> {
				if(signupViewModel.isDone) {
					Log.d("SignUp", "Running UiState.Success")
					LaunchedEffect(Unit) {
						scope.launch {
							dataStore.saveAuthKey(uiState.data.accessToken)
						}
					}
					navigateToHome()
					signupViewModel.isDone = false
				}
			}
			is UiState.Error -> {
				// TODO: tampilkan pesan error
			}
		}
	}
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier.padding(horizontal = 16.dp)
	) {
		Image(
			painter = painterResource(id = R.drawable.signupimage),
			contentDescription = "Sign Up image",
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.height(206.dp)
				.padding(top = 32.dp)
		)
		Text(
			text = "Sign Up",
			style = MaterialTheme.typography.titleLarge,
			color = MaterialTheme.colorScheme.primary,
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = "Your vegetarian lifestyle starts here!",
			style = MaterialTheme.typography.bodySmall,
			color = MaterialTheme.colorScheme.onSurfaceVariant,
			modifier = Modifier.fillMaxWidth()
		)
		TextField(
			value = nameInput,
			onValueChange = { nameInput = it },
			trailingIcon = {
				if(nameInput.isNotEmpty()) {
					IconButton(
						onClick = { nameInput = "" },
					) {
						Icon(
							Icons.Default.Cancel,
							contentDescription = "Clear email"
						)
					}
				}
			},
			modifier = Modifier
				.fillMaxWidth(),
			label = { Text("Name")},
			placeholder = { Text("Your Name")}
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
							contentDescription = "Clear email"
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
							contentDescription = "Clear password"
						)
					}
				}
			},
			modifier = Modifier
				.fillMaxWidth(),
			label = { Text("Password")},
			placeholder = { Text("yourpass123")},
			visualTransformation =  PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
		)
		TextField(
			value = confirmPasswordInput,
			onValueChange = { confirmPasswordInput = it },
			trailingIcon = {
				if(confirmPasswordInput.isNotEmpty()) {
					IconButton(
						onClick = { confirmPasswordInput = "" },
					) {
						Icon(
							Icons.Default.Cancel,
							contentDescription = "Clear password confirmation"
						)
					}
				}
			},
			modifier = Modifier
				.fillMaxWidth(),
			label = { Text("Confirm Password")},
			placeholder = { Text("yourpass123")},
			visualTransformation =  PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
		)
		Button(
			onClick = {
				if(passwordInput == confirmPasswordInput) {
					signupViewModel.signupUser(emailInput, passwordInput, nameInput)
				}
		  	},
			Modifier.width(156.dp)) {
			Text("Sign up")
		}
		Divider()
		AnnotatedClickableText(text1 = "Already have an account? ", text2 = "Click here to Sign In!", action = { navigateToLogin() })
	}
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
	VegaliciousTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			SignupScreen(navigateToHome = {}, navigateToLogin = {})
		}
	}
}
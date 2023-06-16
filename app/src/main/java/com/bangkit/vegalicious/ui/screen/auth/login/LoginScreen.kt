package com.bangkit.vegalicious.ui.screen.auth.login

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
import androidx.compose.material3.CircularProgressIndicator
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
fun LoginScreen(
	modifier: Modifier = Modifier,
	navigateToSignup: () -> Unit,
	navigateToHome: () -> Unit,
	loginViewModel: LoginViewModel = viewModel(
		factory = ViewModelFactory()
	)
) {
	var emailInput by remember { mutableStateOf("") }
	var passwordInput by remember { mutableStateOf("")}
	var isLoading by remember { mutableStateOf(false)}
	
	val context = LocalContext.current
	val scope = rememberCoroutineScope()
	val dataStore = StoreUserData(context)
	
	loginViewModel.uiStateLogin.collectAsState(initial = UiState.Loading).value.let {uiState ->
		when(uiState) {
			is UiState.Loading -> {
				if(isLoading)
					Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
						.fillMaxWidth()
						.padding(top = 32.dp)) {
						CircularProgressIndicator(modifier = Modifier.padding(8.dp))
					}
			}
			is UiState.Success -> {
				if(loginViewModel.isDone) {
					Log.d("LoginScreen", "Running UiState.Success")
					LaunchedEffect(Unit) {
						scope.launch {
							dataStore.saveAuthKey(uiState.data.accessToken)
						}
					}
					navigateToHome()
					loginViewModel.isDone = false
				}
			}
			is UiState.Error -> {
				isLoading = false
			}
		}
	}
	
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
			placeholder = { Text("yourpass123")},
			visualTransformation =  PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
		)
		Button(onClick = {
				loginViewModel.loginUser(emailInput, passwordInput).also { Log.d("LoginScreen", "calling loginViewModel.loginUser()") }
				isLoading = true
			},
			Modifier.width(156.dp)
		) {
			Text("Sign In")
		}
		Divider()
		AnnotatedClickableText(text1 = "Don't have an account? ", text2 = "Click here to Sign Up!", action = { navigateToSignup() })
	}
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
	VegaliciousTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			LoginScreen(navigateToHome = {}, navigateToSignup = {})
		}
	}
}
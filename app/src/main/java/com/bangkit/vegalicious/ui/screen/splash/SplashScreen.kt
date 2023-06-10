package com.bangkit.vegalicious.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.vegalicious.R
import com.bangkit.vegalicious.ui.navigation.Screen
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme
import com.bangkit.vegalicious.utils.StoreUserData
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navigateToHome: () -> Unit, navigateToLogin: () -> Unit) {
	
	val context = LocalContext.current
	val scope = rememberCoroutineScope()
	val dataStore = StoreUserData(context)
	val auth = dataStore.getAuthKey.collectAsState(initial = "")
	
	Box(
		modifier = Modifier
			.background(MaterialTheme.colorScheme.primary)
			.fillMaxSize()
		,
		contentAlignment = Alignment.Center,
		
	) {
		Image(
			painter = painterResource(id = R.drawable.logo_white),
			contentDescription = "App Logo",
			contentScale = ContentScale.FillHeight,
			modifier = Modifier.height(206.dp)
		)
	}
	
	if(auth.value != "") {
		navigateToHome()
	} else {
		navigateToLogin()
	}
}

@Preview
@Composable
fun SplashScreenPreview() {
	VegaliciousTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			SplashScreen(
				navigateToLogin = {},
				navigateToHome = {}
			)
		}
	}
}
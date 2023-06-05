package com.bangkit.vegalicious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bangkit.vegalicious.ui.navigation.NavigationItem
import com.bangkit.vegalicious.ui.navigation.Screen
import com.bangkit.vegalicious.ui.theme.VegaliciousTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VegaliciousTheme(dynamicColor = false) {
				// A surface container using the 'background' color from the theme
				Surface(
					color = MaterialTheme.colorScheme.background
				) {
					VegaliciousApp()
				}
			}
		}
	}
}


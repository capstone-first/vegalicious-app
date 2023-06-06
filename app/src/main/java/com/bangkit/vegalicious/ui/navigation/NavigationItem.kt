package com.bangkit.vegalicious.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem (
	val title: String,
	var icon: ImageVector,
	val screen: Screen,
)
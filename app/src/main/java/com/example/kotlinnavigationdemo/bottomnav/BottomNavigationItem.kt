package com.example.kotlinnavigationdemo.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kotlinnavigationdemo.navigation.BottomFavoriteScreen
import com.example.kotlinnavigationdemo.navigation.BottomHomeScreen
import com.example.kotlinnavigationdemo.navigation.BottomProfileScreen
import com.example.kotlinnavigationdemo.navigation.BottomSettingsScreen

data class CustomBottomNavigationItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val bottomNavigationItems = listOf(
    CustomBottomNavigationItem(
        route = BottomHomeScreen.route,
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        badgeCount = 2
    ),
    CustomBottomNavigationItem(
        route = BottomProfileScreen.route,
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        hasNews = true,
        badgeCount = null
    ),
    CustomBottomNavigationItem(
        route = BottomSettingsScreen.route,
        label = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = false,
        badgeCount = null
    ),
    CustomBottomNavigationItem(
        route = BottomFavoriteScreen.route,
        label = "Favorite",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        hasNews = true,
        badgeCount = 55
    )
)
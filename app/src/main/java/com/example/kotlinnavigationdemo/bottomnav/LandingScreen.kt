package com.example.kotlinnavigationdemo.bottomnav

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kotlinnavigationdemo.navigateSingleTopTo
import com.example.kotlinnavigationdemo.navigation.BottomFavoriteScreen
import com.example.kotlinnavigationdemo.navigation.BottomHomeScreen
import com.example.kotlinnavigationdemo.navigation.BottomProfileScreen
import com.example.kotlinnavigationdemo.navigation.BottomSettingsScreen


/**
 * This is the landing screen which only contains the bottom navigation bar.
 * and the NavHost which contains the bottom navigation screens.
 */
@Composable
fun LandingScreen(
    modifier: Modifier = Modifier
) {
    val bottomNavController: NavHostController = rememberNavController()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Scaffold(
            topBar = { TopAppBar() },
            bottomBar = { BottomAppBar(bottomNavController) }
        ){
            val padding = it
            BottomNavGraph(bottomNavController)
        }
    }

}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(start = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            modifier = modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = "TopAppBar for Bottom Nav",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}


@Composable
fun BottomAppBar(
    bottomNavController: NavHostController
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected =
                currentDestination?.hierarchy?.any {
                    it.route == bottomNavigationItem.route
                } == true,
                onClick = {
                    selectedItem = index
                    bottomNavController.navigateSingleTopTo(bottomNavigationItem.route)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (bottomNavigationItem.badgeCount != null) {
                                Badge {
                                    Text(bottomNavigationItem.badgeCount.toString())
                                }
                            } else if (bottomNavigationItem.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (selectedItem == index) {
                                bottomNavigationItem.selectedIcon
                            } else {
                                bottomNavigationItem.unselectedIcon
                            },
                            contentDescription = bottomNavigationItem.label
                        )
                    }
                },
                label = {
                    Text(text = bottomNavigationItem.label)
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomAppBarPreview() {
    BottomAppBar(rememberNavController())
}


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomHomeScreen.route
    ){
        composable(route = BottomHomeScreen.route) {
            BottomHomeScreen()
        }
        composable(BottomProfileScreen.route) {
            BottomProfileScreen()
        }
        composable(BottomSettingsScreen.route) {
            BottomSettingsScreen()
        }
        composable(BottomFavoriteScreen.route) {
            BottomFavoriteScreen()
        }
    }
}
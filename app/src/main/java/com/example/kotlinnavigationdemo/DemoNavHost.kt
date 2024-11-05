package com.example.kotlinnavigationdemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kotlinnavigationdemo.bottomnav.BottomFavoriteScreen
import com.example.kotlinnavigationdemo.bottomnav.BottomHomeScreen
import com.example.kotlinnavigationdemo.bottomnav.BottomProfileScreen
import com.example.kotlinnavigationdemo.bottomnav.BottomSettingsScreen
import com.example.kotlinnavigationdemo.bottomnav.LandingScreen
import com.example.kotlinnavigationdemo.navigation.BottomFavoriteScreen
import com.example.kotlinnavigationdemo.navigation.BottomHomeScreen
import com.example.kotlinnavigationdemo.navigation.BottomProfileScreen
import com.example.kotlinnavigationdemo.navigation.BottomSettingsScreen
import com.example.kotlinnavigationdemo.navigation.FifthScreen
import com.example.kotlinnavigationdemo.navigation.FirstScreen
import com.example.kotlinnavigationdemo.navigation.FourthScreen
import com.example.kotlinnavigationdemo.navigation.LandingScreen
import com.example.kotlinnavigationdemo.navigation.SecondScreen
import com.example.kotlinnavigationdemo.navigation.ThirdScreen

@Composable
fun DemoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "main_nav_graph",
        modifier = modifier
    ) {
        /**
         * Using nested navigation graphs.
         * start by giving a route to the navigation() component and
         * a start destination that route should open when called
         *
         * when calling another graph which is denoted below in navigation() component
         * it should call the route of the graph which in turn will open the start destination
         * of that graph
         */
        navigation(
            route = "main_nav_graph",
            startDestination = FirstScreen.route
        ) {
            composable(route = FirstScreen.route) {
                FirstScreen(
                    toBottomHomeScreen = {
                        navController.navigate("bottom_nav_graph") {
                            popUpTo("main_nav_graph") {
                                inclusive = true
                            }
                        }
                    },
                    toSecondScreen = { navController.navigateSingleTopTo(SecondScreen.route) },
                    toThirdScreen = { navController.navigateSingleTopTo(ThirdScreen.route) },
                    toFourthScreen = { navController.navigateSingleTopTo(FourthScreen.route) }
                )
            }
            composable(route = SecondScreen.route) {
                SecondScreen(
                    toThirdScreen = { navController.navigateSingleTopTo(ThirdScreen.route) },
                    toFourthScreen = { navController.navigateSingleTopTo(FourthScreen.route) }
                )
            }
            composable(route = ThirdScreen.route) {
                ThirdScreen(
                    toFourthScreen = { navController.navigateSingleTopTo(FourthScreen.route) },
                    toFifthScreen = { navController.navigateToScreenWithArguments("This is coming from the DemoNavHost from Third Screen") }
                )
            }
            composable(route = FourthScreen.route) {
                FourthScreen(
                    toFifthScreen = { navController.navigateToScreenWithArguments("This is coming from the FourthScreen from DemoNavHost") }
                )
            }
            //Example of how to make a composable that takes arguments for Navigation.
            //Also DeepLinks support.
            composable(
                route = FifthScreen.routeWithArgs,
                arguments = FifthScreen.arguments,
                deepLinks = FifthScreen.deepLinks
            ) { navBackStackEntry ->
                val someArgument = navBackStackEntry.arguments?.getString(FifthScreen.arg1)
                FifthScreen(somearg = someArgument)
            }
        }
        /**
         * This is another graph entry point.
         *
         * when creating BottomNavigationBar
         * create a screen which just hosts the Bottom Navigation Bar
         * lets say we call it landing screen where we land
         * after we land there we open the first screen which will be one of the bottom navigation bar screens
         */
        navigation(
            route = "bottom_nav_graph",
            startDestination = LandingScreen.route
        ) {
            composable(LandingScreen.route) {
                LandingScreen()
            }

        }
    }
}

/**
 * This helper function to avoid opening multiple copies of the same destination.
 */
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

private fun NavHostController.navigateToScreenWithArguments(someArgs: String) {
    this.navigateSingleTopTo("${FifthScreen.route}/$someArgs")
}


/**
 * adb shell am start -d "example://FifthScreen/why_does_it_not_take_spaces" -a android.intent.action.VIEW
 *
 * TODO: Figure out how to send a string with spaces.
 */
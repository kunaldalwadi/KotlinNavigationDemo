package com.example.kotlinnavigationdemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgs
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinnavigationdemo.navigation.FirstScreen
import com.example.kotlinnavigationdemo.navigation.SecondScreen
import com.example.kotlinnavigationdemo.navigation.ThirdScreen
import com.example.kotlinnavigationdemo.navigation.FourthScreen
import com.example.kotlinnavigationdemo.navigation.FifthScreen

@Composable
fun DemoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = FirstScreen.route,
        modifier = modifier
    ) {
        composable(route = FirstScreen.route) {
            FirstScreen(
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
        ) {
                navBackStackEntry ->
            val someArgument = navBackStackEntry.arguments?.getString(FifthScreen.arg1)
            FifthScreen(somearg = someArgument)
        }
    }
}


//Helper functions to make the code cleaner.

/**
 * This is to avoid opening multiple copies of the same destination.
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
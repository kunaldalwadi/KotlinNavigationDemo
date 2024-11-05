package com.example.kotlinnavigationdemo.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

/**
 * Created this interface in order to share the properties between all the destinations.
 */

interface Destinations {
    val route: String
}

object FirstScreen : Destinations {
    override val route = "FirstScreen"
}
object SecondScreen : Destinations {
    override val route = "SecondScreen"
}
object ThirdScreen : Destinations {
    override val route = "ThirdScreen"
}
object FourthScreen : Destinations{
    override val route = "FourthScreen"
}
object FifthScreen : Destinations {
    override val route = "FifthScreen"
    const val arg1 = "some_argument"
    val routeWithArgs = "$route/{$arg1}"
    val deepLinks = listOf(navDeepLink { uriPattern = "example://$route/{$arg1}" })
    val arguments = listOf(navArgument(arg1) { type = NavType.StringType })
}

//----------------------------- Bottom Navigation Screens --------------------------------------

object LandingScreen : Destinations {
    override val route = "LandingScreen"
}
object BottomHomeScreen : Destinations {
    override val route = "BottomHomeScreen"
}
object BottomProfileScreen : Destinations {
    override val route = "BottomProfileScreen"
}
object BottomSettingsScreen : Destinations {
    override val route = "BottomSettingsScreen"
}
object BottomFavoriteScreen : Destinations {
    override val route = "BottomFavoriteScreen"
}

//---------------------------------------------------------------------------------------------
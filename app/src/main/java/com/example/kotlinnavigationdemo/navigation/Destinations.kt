package com.example.kotlinnavigationdemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface Destinations {
    val icon: ImageVector
    val route: String
}

object FirstScreen : Destinations {
    override val icon = Icons.Filled.Home
    override val route = "FirstScreen"
}
object SecondScreen : Destinations {
    override val icon = Icons.Filled.Call
    override val route = "SecondScreen"
}
object ThirdScreen : Destinations {
    override val icon = Icons.Filled.Done
    override val route = "ThirdScreen"
}
object FourthScreen : Destinations{
    override val icon = Icons.Filled.Edit
    override val route = "FourthScreen"
}
object FifthScreen : Destinations {
    override val icon = Icons.Filled.AddCircle
    override val route = "FifthScreen"
    const val arg1 = "some_argument"
    val routeWithArgs = "$route/{$arg1}"
    val deepLinks = listOf(navDeepLink { uriPattern = "example://$route/{$arg1}" })
    val arguments = listOf(navArgument(arg1) { type = NavType.StringType })
}
package com.example.vidyarthibus.ui.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")

    object Home : Screen("home")

    object Live : Screen("live")

    object Alerts : Screen("alerts")

    object Profile : Screen("profile")
}
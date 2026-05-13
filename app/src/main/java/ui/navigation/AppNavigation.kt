package com.example.vidyarthibus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vidyarthibus.ui.screens.AlertsScreen
import com.example.vidyarthibus.ui.screens.HomeScreen
import com.example.vidyarthibus.ui.screens.LiveScreen
import com.example.vidyarthibus.ui.screens.LoginScreen
import com.example.vidyarthibus.ui.screens.ProfileScreen
import com.example.vidyarthibus.ui.screens.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {

            SplashScreen(navController)
        }

        composable("login") {

            LoginScreen(navController)
        }

        composable(Screen.Home.route) {

            HomeScreen(navController)
        }

        composable(Screen.Live.route) {

            LiveScreen(navController)
        }

        composable(Screen.Alerts.route) {

            AlertsScreen(navController)
        }

        composable(Screen.Profile.route) {

            ProfileScreen(navController)
        }
    }
}
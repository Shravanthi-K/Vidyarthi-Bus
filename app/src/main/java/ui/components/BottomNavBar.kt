package com.example.vidyarthibus.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vidyarthibus.ui.navigation.Screen
import com.example.vidyarthibus.ui.utils.UserSession

@Composable
fun BottomNavBar(navController: NavController) {

    val navBackStackEntry =
        navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF0F172A),

        tonalElevation = 8.dp
    ) {

        NavigationBarItem(
            selected = currentRoute == Screen.Home.route,

            onClick = {
                navController.navigate(Screen.Home.route)
            },

            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = null,

                    tint =
                        if (currentRoute == Screen.Home.route)
                            Color(0xFF2563EB)
                        else
                            Color.Gray
                )
            },

            label = {
                Text(
                    "Home",

                    color =
                        if (currentRoute == Screen.Home.route)
                            Color.White
                        else
                            Color.Gray
                )
            }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.Live.route,

            onClick = {
                navController.navigate(Screen.Live.route)
            },

            icon = {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,

                    tint =
                        if (currentRoute == Screen.Live.route)
                            Color(0xFF2563EB)
                        else
                            Color.Gray
                )
            },

            label = {
                Text(
                    "Live",

                    color =
                        if (currentRoute == Screen.Live.route)
                            Color.White
                        else
                            Color.Gray
                )
            }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.Alerts.route,

            onClick = {
                navController.navigate(Screen.Alerts.route)
            },

            icon = {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = null,

                    tint =
                        if (currentRoute == Screen.Alerts.route)
                            Color(0xFF2563EB)
                        else
                            Color.Gray
                )
            },

            label = {
                Text(
                    "Alerts",

                    color =
                        if (currentRoute == Screen.Alerts.route)
                            Color.White
                        else
                            Color.Gray
                )
            }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.Profile.route,

            onClick = {
                navController.navigate(Screen.Profile.route)
            },

            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,

                    tint =
                        if (currentRoute == Screen.Profile.route)
                            Color(0xFF2563EB)
                        else
                            Color.Gray
                )
            },

            label = {
                Text(
                    "Profile",

                    color =
                        if (currentRoute == Screen.Profile.route)
                            Color.White
                        else
                            Color.Gray
                )
            }
        )
    }
}
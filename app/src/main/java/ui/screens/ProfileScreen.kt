package com.example.vidyarthibus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidyarthibus.ui.components.BottomNavBar
import com.example.vidyarthibus.ui.navigation.Screen
import com.example.vidyarthibus.ui.utils.UserSession

@Composable
fun ProfileScreen(navController: NavController) {

    val isAdmin =
        UserSession.role == "admin"

    val userName =
        if (isAdmin)
            "Admin User"
        else
            "Student User"

    val userEmail =
        if (isAdmin)
            "admin@vidyarthi.com"
        else
            "student@vidyarthi.com"

    val roleText =
        if (isAdmin)
            "Administrator Access"
        else
            "Student Access"

    val roleColor =
        if (isAdmin)
            Color(0xFFEF4444)
        else
            Color(0xFF22C55E)

    Scaffold(

        bottomBar = {
            BottomNavBar(navController)
        }

    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black,
                            Color(0xFF020617),
                            Color(0xFF071329)
                        )
                    )
                )
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.Center
            ) {

                Card(
                    shape = CircleShape,

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xCC111827)
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,

                        tint = roleColor,

                        modifier = Modifier
                            .padding(30.dp)
                            .height(70.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = userName,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = userEmail,
                    color = Color(0xFF94A3B8),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = roleColor.copy(alpha = 0.15f)
                    ),

                    shape = RoundedCornerShape(50.dp)
                ) {

                    Text(
                        text = roleText,
                        color = roleColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,

                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xCC111827)
                    ),

                    shape = RoundedCornerShape(24.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "ACCOUNT INFORMATION",
                            color = Color.Cyan,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text =
                                if (isAdmin)
                                    "Transport Control Access Enabled"
                                else
                                    "Student Transport Access Enabled",

                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Realtime Firebase Sync Active",
                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "AI Smart Monitoring Connected",
                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text =
                                if (isAdmin)
                                    "Admin Management Privileges Granted"
                                else
                                    "Live Tracking & Alerts Enabled",

                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(34.dp))

                Button(

                    onClick = {

                        UserSession.role = "student"

                        navController.navigate(
                            Screen.Login.route
                        ) {

                            popUpTo(0)
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {

                    Text(
                        text = "LOGOUT",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
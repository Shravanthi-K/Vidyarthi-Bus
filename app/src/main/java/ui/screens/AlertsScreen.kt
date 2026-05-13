package com.example.vidyarthibus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidyarthibus.ui.components.BottomNavBar
import com.example.vidyarthibus.ui.utils.showBusNotification
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun AlertsScreen(navController: NavController) {

    val context = LocalContext.current

    var campusStatus by remember {
        mutableStateOf("Loading...")
    }

    var northStatus by remember {
        mutableStateOf("Loading...")
    }

    var cityStatus by remember {
        mutableStateOf("Loading...")
    }

    LaunchedEffect(Unit) {

        val database =
            FirebaseDatabase
                .getInstance(
                    "https://vidyarthibus-4a377-default-rtdb.asia-southeast1.firebasedatabase.app/"
                )
                .reference

        database.child("routes")
            .child("Campus Express")
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val status =
                            snapshot.child("status")
                                .getValue(String::class.java)

                        if (status != null) {

                            campusStatus = status

                            if (status == "Bus Full") {

                                showBusNotification(
                                    context,
                                    "Campus Express",
                                    "Campus Express is currently full"
                                )
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }
            )

        database.child("routes")
            .child("North Loop")
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val status =
                            snapshot.child("status")
                                .getValue(String::class.java)

                        if (status != null) {

                            northStatus = status

                            if (status == "Bus Full") {

                                showBusNotification(
                                    context,
                                    "North Loop",
                                    "North Loop is heavily crowded"
                                )
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }
            )

        database.child("routes")
            .child("City Link")
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val status =
                            snapshot.child("status")
                                .getValue(String::class.java)

                        if (status != null) {

                            cityStatus = status

                            if (status == "Bus Full") {

                                showBusNotification(
                                    context,
                                    "City Link",
                                    "City Link is currently full"
                                )
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }
            )
    }

    Scaffold(

        bottomBar = {
            BottomNavBar(navController)
        }

    ) { padding ->

        Column(
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
                .padding(16.dp),

            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "SMART ALERTS",
                color = Color.Cyan,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            AlertCard(
                route = "Campus Express",
                status = campusStatus,
                navController = navController
            )

            Spacer(modifier = Modifier.height(12.dp))

            AlertCard(
                route = "North Loop",
                status = northStatus,
                navController = navController
            )

            Spacer(modifier = Modifier.height(12.dp))

            AlertCard(
                route = "City Link",
                status = cityStatus,
                navController = navController
            )
        }
    }
}

@Composable
fun AlertCard(
    route: String,
    status: String,
    navController: NavController
) {

    val alertColor =
        when (status) {

            "Seats Available" ->
                Color(0xFF22C55E)

            "Limited Seats" ->
                Color(0xFFF59E0B)

            "Bus Full" ->
                Color(0xFFEF4444)

            else ->
                Color.Cyan
        }

    val alertMessage =
        when (status) {

            "Seats Available" ->
                "🚍 Seats available on $route"

            "Limited Seats" ->
                "⚠ Moderate crowd detected on $route"

            "Bus Full" ->
                "🚨 $route is currently full"

            else ->
                "Fetching realtime updates..."
        }

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                navController.navigate("live")
            },

        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC0F172A)
        ),

        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Text(
                text = "SMART ALERT",
                color = alertColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = alertMessage,
                color = Color.White,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Tap to open live tracking",
                color = Color.Cyan,
                fontSize = 11.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "AI Transport Monitoring Active",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
            )
        }
    }
}
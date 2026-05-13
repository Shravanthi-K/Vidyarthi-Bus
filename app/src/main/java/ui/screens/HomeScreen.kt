package com.example.vidyarthibus.ui.screens

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidyarthibus.ui.components.BottomNavBar
import com.example.vidyarthibus.ui.utils.UserSession
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun HomeScreen(navController: NavController) {

    val database =
        FirebaseDatabase
            .getInstance(
                "https://vidyarthibus-4a377-default-rtdb.asia-southeast1.firebasedatabase.app/"
            )
            .reference

    var campusStatus by remember {
        mutableStateOf("Seats Available")
    }

    var northStatus by remember {
        mutableStateOf("Limited Seats")
    }

    var cityStatus by remember {
        mutableStateOf("Bus Full")
    }

    LaunchedEffect(Unit) {

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
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(18.dp)
            ) {

                Text(
                    text =
                        if (UserSession.role == "admin")
                            "Transport Control Center"
                        else
                            "Good Evening",

                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text =
                        if (UserSession.role == "admin")
                            "Realtime Smart Transport Monitoring"
                        else
                            "Welcome back, Student",

                    color = Color(0xFF94A3B8),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                MainHighlightCard()

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "ACTIVE ROUTES",
                    color = Color.Cyan,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                RouteCard(
                    title = "Campus Express",
                    status = campusStatus
                )

                Spacer(modifier = Modifier.height(12.dp))

                RouteCard(
                    title = "North Loop",
                    status = northStatus
                )

                Spacer(modifier = Modifier.height(12.dp))

                RouteCard(
                    title = "City Link",
                    status = cityStatus
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (UserSession.role == "admin") {

                    Text(
                        text = "ADMIN CONTROLS",
                        color = Color.Cyan,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    AdminControlCard(
                        route = "Campus Express",
                        database = database
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AdminControlCard(
                        route = "North Loop",
                        database = database
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AdminControlCard(
                        route = "City Link",
                        database = database
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                AnalyticsCard()
            }
        }
    }
}

@Composable
fun MainHighlightCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC0F172A)
        ),

        shape = RoundedCornerShape(24.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,

                    tint = Color.Cyan,

                    modifier = Modifier.size(34.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Column {

                    Text(
                        text = "Campus Express",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Arriving in 5 mins",
                        color = Color(0xFF94A3B8),
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,

                modifier = Modifier.fillMaxWidth()
            ) {

                MiniInfoCard(
                    icon = Icons.Default.Speed,
                    value = "42 km/h",
                    title = "Speed"
                )

                MiniInfoCard(
                    icon = Icons.Default.LocationOn,
                    value = "2.4 km",
                    title = "Distance"
                )

                MiniInfoCard(
                    icon = Icons.Default.Person,
                    value = "68%",
                    title = "Occupancy"
                )
            }
        }
    }
}

@Composable
fun MiniInfoCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    title: String
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF111827)
        ),

        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier.padding(12.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,

                tint = Color.Cyan
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = value,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = title,
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun RouteCard(
    title: String,
    status: String
) {

    val statusColor =
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

    Card(
        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC111827)
        ),

        shape = RoundedCornerShape(20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = statusColor.copy(alpha = 0.18f)
                ),

                shape = RoundedCornerShape(16.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,

                    tint = statusColor,

                    modifier = Modifier
                        .padding(12.dp)
                        .size(26.dp)
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = status,
                    color = statusColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "LIVE",
                color = statusColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun AdminControlCard(
    route: String,
    database: com.google.firebase.database.DatabaseReference
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC111827)
        ),

        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = route,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,

                modifier = Modifier.fillMaxWidth()
            ) {

                StatusChip(
                    text = "Available",

                    onClick = {
                        database.child("routes")
                            .child(route)
                            .child("status")
                            .setValue("Seats Available")
                    }
                )

                StatusChip(
                    text = "Limited",

                    onClick = {
                        database.child("routes")
                            .child(route)
                            .child("status")
                            .setValue("Limited Seats")
                    }
                )

                StatusChip(
                    text = "Full",

                    onClick = {
                        database.child("routes")
                            .child(route)
                            .child("status")
                            .setValue("Bus Full")
                    }
                )
            }
        }
    }
}

@Composable
fun StatusChip(
    text: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.clickable {
            onClick()
        },

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E293B)
        ),

        shape = RoundedCornerShape(50.dp)
    ) {

        Text(
            text = text,
            color = Color.Cyan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        )
    }
}

@Composable
fun AnalyticsCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC0F172A)
        ),

        shape = RoundedCornerShape(22.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "AI INSIGHTS",
                color = Color.Cyan,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            InsightRow(
                icon = Icons.Default.Notifications,
                text = "Smart Alerts Active"
            )

            Spacer(modifier = Modifier.height(10.dp))

            InsightRow(
                icon = Icons.Default.LocationOn,
                text = "Realtime GPS Tracking Running"
            )

            Spacer(modifier = Modifier.height(10.dp))

            InsightRow(
                icon = Icons.Default.Person,
                text = "Live Occupancy Monitoring Enabled"
            )
        }
    }
}

@Composable
fun InsightRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,

            tint = Color.Cyan,

            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.padding(6.dp))

        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}
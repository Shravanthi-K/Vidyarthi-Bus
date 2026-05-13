package com.example.vidyarthibus.ui.screens

import android.location.Location
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidyarthibus.ui.components.BottomNavBar
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

fun calculateDistance(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Double {

    val results = FloatArray(1)

    Location.distanceBetween(
        lat1,
        lon1,
        lat2,
        lon2,
        results
    )

    return results[0].toDouble()
}

@Composable
fun LiveScreen(navController: NavController) {

    val routePoints = listOf(
        LatLng(12.9716, 77.5946),
        LatLng(12.9850, 77.6100),
        LatLng(12.9600, 77.6200)
    )

    val busStop = LatLng(
        12.9765,
        77.6030
    )

    val darkMapStyle = """
[
  {
    "elementType": "geometry",
    "stylers": [
      {
        "color": "#0f172a"
      }
    ]
  }
]
""".trimIndent()

    var campusBusLocation by remember {
        mutableStateOf(LatLng(12.9716, 77.5946))
    }

    var northLoopLocation by remember {
        mutableStateOf(LatLng(12.9850, 77.6100))
    }

    var cityLinkLocation by remember {
        mutableStateOf(LatLng(12.9600, 77.6200))
    }

    var campusStatus by remember {
        mutableStateOf("Seats Available")
    }

    var northStatus by remember {
        mutableStateOf("Limited Seats")
    }

    var cityStatus by remember {
        mutableStateOf("Bus Full")
    }

    val animatedLat by animateFloatAsState(
        targetValue = campusBusLocation.latitude.toFloat(),
        animationSpec = tween(2000),
        label = "lat"
    )

    val animatedLng by animateFloatAsState(
        targetValue = campusBusLocation.longitude.toFloat(),
        animationSpec = tween(2000),
        label = "lng"
    )

    val animatedBusLocation = LatLng(
        animatedLat.toDouble(),
        animatedLng.toDouble()
    )

    val distance = calculateDistance(
        animatedBusLocation.latitude,
        animatedBusLocation.longitude,
        busStop.latitude,
        busStop.longitude
    )

    val etaMinutes = ((distance / 1000) / 0.5).toInt()

    val cameraPositionState =
        rememberCameraPositionState {

            position =
                CameraPosition.fromLatLngZoom(
                    animatedBusLocation,
                    11f
                )
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

                        val lat =
                            snapshot.child("latitude")
                                .getValue(Double::class.java)

                        val lng =
                            snapshot.child("longitude")
                                .getValue(Double::class.java)

                        val status =
                            snapshot.child("status")
                                .getValue(String::class.java)

                        if (lat != null && lng != null) {
                            campusBusLocation = LatLng(lat, lng)
                        }

                        if (status != null) {
                            campusStatus = status
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }
            )
    }

    val mapProperties = MapProperties(
        mapStyleOptions =
            MapStyleOptions(darkMapStyle)
    )

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

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties
            ) {

                Polyline(
                    points = routePoints,
                    color = Color(0xFF2563EB),
                    width = 10f
                )

                Marker(
                    state = remember {
                        MarkerState(position = animatedBusLocation)
                    }.apply {
                        position = animatedBusLocation
                    },

                    title = "Campus Express",
                    snippet = campusStatus
                )

                Marker(
                    state = remember {
                        MarkerState(position = northLoopLocation)
                    }.apply {
                        position = northLoopLocation
                    },

                    title = "North Loop",
                    snippet = northStatus,

                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN
                    )
                )

                Marker(
                    state = remember {
                        MarkerState(position = cityLinkLocation)
                    }.apply {
                        position = cityLinkLocation
                    },

                    title = "City Link",
                    snippet = cityStatus,

                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {

                Card(
                    modifier = Modifier.clickable {
                        navController.navigate("alerts")
                    },

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xCC071329)
                    ),

                    shape = RoundedCornerShape(18.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {

                        Text(
                            text = "LIVE AI TRACKING",
                            color = Color.Cyan,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Campus Express → $campusStatus",
                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "North Loop → $northStatus",
                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "City Link → $cityStatus",
                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "ETA: $etaMinutes mins",
                            color = Color.Cyan,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Distance: ${
                                "%.2f".format(distance / 1000)
                            } km",
                            color = Color(0xFF94A3B8),
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xCC0F172A)
                    ),

                    shape = RoundedCornerShape(18.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp),

                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "AI ANALYTICS",
                            color = Color.Cyan,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Occupancy Prediction Active",
                            color = Color.White,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Traffic Delay: Low",
                            color = Color(0xFF94A3B8),
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Realtime Firebase Tracking Enabled",
                            color = Color.Cyan,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
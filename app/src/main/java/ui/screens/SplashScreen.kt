package com.example.vidyarthibus.ui.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    var progress by remember {
        mutableFloatStateOf(0f)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 3000,
            easing = FastOutSlowInEasing
        ),
        label = "progress"
    )

    LaunchedEffect(Unit) {

        progress = 1f

        delay(3500)

        navController.navigate("login") {

            popUpTo("splash") {
                inclusive = true
            }
        }
    }

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
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center,

                modifier = Modifier
                    .shadow(
                        elevation = 20.dp,
                        shape = CircleShape,
                        ambientColor = Color.Cyan,
                        spotColor = Color.Cyan
                    )
                    .background(
                        color = Color(0xFF071329),
                        shape = CircleShape
                    )
                    .padding(36.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,
                    tint = Color.Cyan,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "VIDYARTHI",
                color = Color.White,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "BUS",
                color = Color.Cyan,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "INITIALIZING AI PROTOCOL",
                color = Color(0xFF94A3B8),
                fontSize = 13.sp,
                letterSpacing = 3.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            LinearProgressIndicator(
                progress = { animatedProgress },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),

                color = Color.Cyan,

                trackColor = Color(0xFF162033),

                gapSize = 0.dp,

                drawStopIndicator = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${(animatedProgress * 100).toInt()}% SYSTEMS READY",
                color = Color.Cyan,
                fontSize = 12.sp
            )
        }
    }
}
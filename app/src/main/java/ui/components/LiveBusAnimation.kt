package com.example.vidyarthibus.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LiveBusAnimation() {

    val infiniteTransition =
        rememberInfiniteTransition(label = "")

    val animatedPosition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 850f,

        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000,
                easing = LinearEasing
            ),

            repeatMode = RepeatMode.Restart
        ),

        label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .blur(0.5.dp),

        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x99203045)
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "LIVE BUS MOVEMENT",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {

                val centerY = size.height / 2

                drawLine(
                    color = Color(0xFF334155),
                    start = Offset(60f, centerY),
                    end = Offset(size.width - 60f, centerY),
                    strokeWidth = 14f
                )

                drawCircle(
                    color = Color(0xFF64748B),
                    radius = 14f,
                    center = Offset(80f, centerY)
                )

                drawCircle(
                    color = Color(0xFF64748B),
                    radius = 14f,
                    center = Offset(size.width / 2, centerY)
                )

                drawCircle(
                    color = Color(0xFF64748B),
                    radius = 14f,
                    center = Offset(size.width - 80f, centerY)
                )

                drawCircle(
                    color = Color(0xFF2563EB),
                    radius = 38f,
                    alpha = 0.25f,
                    center = Offset(
                        animatedPosition.coerceIn(
                            80f,
                            size.width - 80f
                        ),
                        centerY
                    )
                )

                drawCircle(
                    color = Color(0xFF38BDF8),
                    radius = 24f,
                    alpha = 0.95f,
                    center = Offset(
                        animatedPosition.coerceIn(
                            80f,
                            size.width - 80f
                        ),
                        centerY
                    )
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Main Gate",
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp
                )

                Text(
                    text = "Library",
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp
                )

                Text(
                    text = "North Block",
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Bus approaching next stop...",
                color = Color(0xFF94A3B8),
                fontSize = 13.sp
            )
        }
    }
}
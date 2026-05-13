package com.example.vidyarthibus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun AICrowdForecastCard() {

    val hour =
        Calendar.getInstance()
            .get(Calendar.HOUR_OF_DAY)

    val forecast =
        when {

            hour in 8..10 ->
                "⚠️ Heavy crowd expected"

            hour in 17..19 ->
                "🟠 Moderate crowd expected"

            else ->
                "🟢 Low crowd expected"
        }

    val recommendation =
        when {

            hour in 8..10 ->
                "AI suggests boarding early."

            hour in 17..19 ->
                "Crowd may increase soon."

            else ->
                "Best time to travel."
        }

    Card(
        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF1E293B),
                            Color(0xFF0F172A)
                        )
                    )
                )
                .padding(22.dp),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "🤖 AI Crowd Forecast",

                color = Color.White,

                fontSize = 20.sp,

                fontWeight = FontWeight.Bold
            )

            Text(
                text = forecast,

                color = Color(0xFF38BDF8),

                fontSize = 16.sp,

                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = recommendation,

                color = Color(0xFFCBD5E1),

                fontSize = 14.sp
            )
        }
    }
}
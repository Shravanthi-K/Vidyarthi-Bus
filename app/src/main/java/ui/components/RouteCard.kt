package com.example.vidyarthibus.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RouteCard(
    route: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                if (selected)
                    Color(0xFF2563EB)
                else
                    Color(0xFF162033)
        )
    ) {

        Box(
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 16.dp
            ),

            contentAlignment = Alignment.Center
        ) {

            Text(
                text = route,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
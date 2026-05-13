package com.example.vidyarthibus

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.example.vidyarthibus.ui.theme.ThemeState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import com.example.vidyarthibus.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(

                colorScheme =
                    if (ThemeState.isDarkMode.value)
                        darkColorScheme()
                    else
                        lightColorScheme()

            ) {

                AppNavigation()
            }
        }
    }
}

data class BusData(
    var status: String,
    var statusColor: Color,
    var progress: Float,
    var crowdLevel: String,
    var aiInsight: String,
    var crowdPrediction: String,
    var currentStop: String,
    var eta: String,
    var capacityLabel: String
)


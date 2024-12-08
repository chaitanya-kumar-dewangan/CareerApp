package com.example.aspirehub

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun getRandomColorWithOpacity(): Color {
    // Generate random RGB values
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    // Set alpha to 20% opacity (51 in hexadecimal)
    val alpha = 51

    // Combine alpha and RGB values into a color integer
    return Color(alpha = alpha, red = red, green = green, blue = blue)
}



//const val COURSE ="course"
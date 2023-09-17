package com.example.translateit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.example.translateit.R

// Set of Material typography styles to start with

val fonts = FontFamily(
    Font(R.font.baloo_regular, weight = FontWeight.Normal),
    Font(R.font.baloo_medium, weight = FontWeight.Medium),
    Font(R.font.baloo_semibold, weight = FontWeight.SemiBold),
    Font(R.font.baloo_bold, weight = FontWeight.Bold),
)
val Typography = Typography(
    headlineSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Content
    ),
    titleLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        textDirection = TextDirection.Content
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        textDirection = TextDirection.Content
    ),
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        textDirection = TextDirection.Content
    ),
    labelMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        textDirection = TextDirection.Content
    )
)
package com.sid.PortfolioAppNew.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sid.PortfolioAppNew.R

@Composable
fun NeonText(
    text: String,
    fontSize: TextUnit = 32.sp,
    modifier: Modifier = Modifier
) {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF00FFE7), Color(0xFF7F00FF))
    )

    Text(
        text = text,
        fontSize = fontSize,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            brush = gradient,
            shadow = Shadow(
                color = Color(0xAA00FFE7),
                offset = Offset(2f, 2f),
                blurRadius = 8f
            )
        ),
        modifier = modifier
    )
} 
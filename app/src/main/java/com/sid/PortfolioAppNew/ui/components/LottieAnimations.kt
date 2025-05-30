package com.sid.PortfolioAppNew.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.Asset("LOADING Animation.json")
    )
    
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition.value,
            iterations = iterations,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun SkillsAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = 1
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.Asset("Skills Animation.json")
    )
    
    LottieAnimation(
        composition = composition.value,
        iterations = iterations,
        modifier = modifier
    )
}

@Composable
fun TimelineAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = 1
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.Asset("Timeline Animation.json")
    )
    
    LottieAnimation(
        composition = composition.value,
        iterations = iterations,
        modifier = modifier
    )
}

@Composable
fun XRAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = 1
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.Asset("XR Animation.json")
    )
    
    LottieAnimation(
        composition = composition.value,
        iterations = iterations,
        modifier = modifier
    )
}

@Composable
fun SuccessAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = 1
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.Asset("Animation - 1748582018683.json")
    )
    
    LottieAnimation(
        composition = composition.value,
        iterations = iterations,
        modifier = modifier
    )
}

@Composable
fun EmptyStateAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = 1
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.Asset("Animation - 1748582181078.json")
    )
    
    LottieAnimation(
        composition = composition.value,
        iterations = iterations,
        modifier = modifier
    )
} 
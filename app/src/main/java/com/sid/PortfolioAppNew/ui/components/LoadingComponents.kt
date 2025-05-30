package com.sid.PortfolioAppNew.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.ui.theme.NeonPrimary
import kotlinx.coroutines.launch

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("LOADING Animation.json")
    )
    val lottieAnimatable = rememberLottieAnimatable()
    val coroutineScope = rememberCoroutineScope()
    
    LaunchedEffect(composition) {
        coroutineScope.launch {
            lottieAnimatable.animate(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        }
    }
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { lottieAnimatable.progress },
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
fun EmptyStateAnimation(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("Animation - 1748582018683.json")
    )
    val lottieAnimatable = rememberLottieAnimatable()
    val coroutineScope = rememberCoroutineScope()
    
    LaunchedEffect(composition) {
        coroutineScope.launch {
            lottieAnimatable.animate(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        }
    }
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { lottieAnimatable.progress },
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun SectionTransitionAnimation(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("Animation - 1748582181078.json")
    )
    val lottieAnimatable = rememberLottieAnimatable()
    val coroutineScope = rememberCoroutineScope()
    
    LaunchedEffect(composition) {
        coroutineScope.launch {
            lottieAnimatable.animate(
                composition = composition,
                iterations = 1
            )
        }
    }
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { lottieAnimatable.progress },
            modifier = Modifier.size(80.dp)
        )
    }
} 
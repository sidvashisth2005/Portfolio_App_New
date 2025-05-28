package com.sid.PortfolioAppNew.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sid.PortfolioAppNew.ui.theme.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = DarkBackground,
            contentColor = Primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Primary
                )
            }
        ) {
            Tab(
                selected = pagerState.currentPage == 0,
                onClick = { /* Handled by pager */ },
                text = {
                    Text(
                        "Timeline",
                        color = if (pagerState.currentPage == 0) Primary else TextWhite
                    )
                }
            )
            Tab(
                selected = pagerState.currentPage == 1,
                onClick = { /* Handled by pager */ },
                text = {
                    Text(
                        "LinkedIn",
                        color = if (pagerState.currentPage == 1) Primary else TextWhite
                    )
                }
            )
            Tab(
                selected = pagerState.currentPage == 2,
                onClick = { /* Handled by pager */ },
                text = {
                    Text(
                        "GitHub",
                        color = if (pagerState.currentPage == 2) Primary else TextWhite
                    )
                }
            )
        }
        
        HorizontalPager(
            state = pagerState
        ) { page ->
            when (page) {
                0 -> TimelineTab()
                1 -> LinkedInFeedTab()
                2 -> GitHubPlaceholderTab()
            }
        }
    }
} 
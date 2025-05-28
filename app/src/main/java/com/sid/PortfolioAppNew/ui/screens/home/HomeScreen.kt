// Remove everything except the cyberpunk HomeScreen implementation.
// The HomeScreen should have the following signature for navigation:
// fun HomeScreen(
//     portfolioViewModel: PortfolioViewModel,
//     onNavigateToAbout: () -> Unit,
//     onNavigateToSkills: () -> Unit,
//     onNavigateToProjects: () -> Unit,
//     onNavigateToContact: () -> Unit,
//     onNavigateToAdmin: () -> Unit,
//     onNavigateToAR: () -> Unit,
//     onNavigateToSettings: () -> Unit,
//     onNavigateToResume: () -> Unit
// )
// The body should use the cyberpunk layout, GlowButton, GlowingNavBar, etc.
// Remove NavigationButton and NavigationButtons composables entirely. 

package com.sid.PortfolioAppNew.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sid.PortfolioAppNew.R
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*
import com.sid.PortfolioAppNew.ui.viewmodel.PortfolioViewModel
import org.koin.androidx.compose.koinViewModel
import com.sid.PortfolioAppNew.data.model.PortfolioState
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.launch
import com.google.accompanist.pager.*

data class TimelineEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val icon: Int? = null,
    val imageUrl: String? = null
)

data class LinkedInPost(
    val id: String,
    val title: String,
    val content: String,
    val timestamp: Date,
    val mediaUrl: String? = null,
    val mediaType: MediaType? = null
)

enum class MediaType {
    IMAGE,
    VIDEO
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val tabTitles = listOf("Timeline", "LinkedIn", "GitHub")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        // Tab Row
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color(0xFF1A1A1A),
            contentColor = NeonPrimary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(3.dp),
                    color = NeonPrimary
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            color = if (pagerState.currentPage == index) NeonPrimary else Color.White.copy(alpha = 0.6f),
                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        // Pager content
        HorizontalPager(
            count = tabTitles.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> TimelineTab()
                1 -> LinkedInFeedTab()
                2 -> GitHubPlaceholderTab()
            }
        }
    }
}

@Composable
private fun FABMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NeonBlue.copy(alpha = 0.1f)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                color = Color.White
            )
        }
        FloatingActionButton(
            onClick = onClick,
            containerColor = NeonBlue,
            contentColor = Color.White,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text
            )
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun SkillChip(
    text: String
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ProfileSection() {
    NeonCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image Placeholder
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.DarkGray, RoundedCornerShape(75.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    tint = Color.LightGray,
                    modifier = Modifier.size(72.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "SID",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Android Developer",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
private fun NavigationButtons(
    onProjectsClick: () -> Unit,
    onSkillsClick: () -> Unit,
    onARClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NeonButton(
            onClick = onProjectsClick,
            text = "Projects"
        )
        NeonButton(
            onClick = onSkillsClick,
            text = "Skills"
        )
        NeonButton(
            onClick = onARClick,
            text = "AR"
        )
    }
}

@Composable
private fun TimelineSection() {
    NeonCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Timeline",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Timeline items will be added here
        }
    }
}

@Composable
private fun LinkedInSection() {
    NeonCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "LinkedIn Posts",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            // LinkedIn posts will be added here
        }
    }
}

@Composable
private fun TimelineTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Timeline items will be added here
    }
}

@Composable
private fun LinkedInFeedTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // LinkedIn posts will be added here
    }
}

@Composable
private fun GitHubPlaceholderTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("GitHub integration coming soon!")
    }
} 
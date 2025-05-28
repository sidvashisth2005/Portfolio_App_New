package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*
import com.sid.PortfolioAppNew.ui.components.FlowRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Resume") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Contact Information
            ResumeSection(
                title = "Contact",
                icon = Icons.Default.ContactMail
            ) {
                ContactItem(Icons.Default.Email, "siddhantvashisth05@gmail.com")
                ContactItem(Icons.Default.Phone, "+91 8871592579")
                ContactItem(Icons.Default.Link, "linkedin.com/in/siddhant-vashisth")
                ContactItem(Icons.Default.Code, "github.com/sidvashisth2005")
                ContactItem(Icons.Default.LocationOn, "Guna, Madhya Pradesh, India")
            }

            // Professional Summary
            ResumeSection(
                title = "Professional Summary",
                icon = Icons.Default.Person
            ) {
                Text(
                    text = "Innovative and self-driven engineering student with expertise in Java-based Android development and immersive technologies including AR/VR/XR. Experienced in leading technical projects, managing cross-functional teams, and delivering creative solutions using tools like Unity, Blender, and Firebase.",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }

            // Skills
            ResumeSection(
                title = "Skills",
                icon = Icons.Default.Build
            ) {
                SkillCategory("Programming Languages", listOf("Java", "Kotlin", "Python", "C++", "C", "HTML", "CSS", "MySQL"))
                SkillCategory("Tools & Platforms", listOf("Android Studio", "Firebase", "Unity", "Blender", "Vuforia", "Figma"))
                SkillCategory("Expertise", listOf("App Development", "AR/VR/XR", "UI/UX", "3D Modeling", "AI Integration", "Creative Design"))
                SkillCategory("Soft Skills", listOf("Leadership", "Communication", "Problem Solving", "Team Management"))
                SkillCategory("Languages", listOf("English", "Hindi"))
            }

            // Education
            ResumeSection(
                title = "Education",
                icon = Icons.Default.School
            ) {
                EducationItem(
                    degree = "Bachelor of Technology",
                    institution = "Jaypee University of Engineering and Technology",
                    period = "2023–2027",
                    details = listOf(
                        "CGPA: 7.95",
                        "Winner of University Ideathon (1st place)",
                        "Finalist in SBI CYI Hackathon (Top 100)",
                        "Qualified for Round 2 - All India XR Creators Hackathon",
                        "Selected for Bhopal Vigyan Mela (Govt. of India)",
                        "Research paper on Library Sciences presented at JUET International Conference",
                        "Internship at Skill Desire"
                    )
                )
            }

            // Certifications
            ResumeSection(
                title = "Certifications",
                icon = Icons.Default.CardMembership
            ) {
                CertificationItem("Infosys C/C++ Foundation Course", "Infosys Springboard", "Feb 2024")
                CertificationItem("Advanced HTML & CSS", "Infosys Springboard", "Sept 2024")
            }

            // Projects
            ResumeSection(
                title = "Projects",
                icon = Icons.Default.Work
            ) {
                ProjectItem(
                    name = "ARound You",
                    description = "Multi-device location-based AR app enabling real-time user interaction and geo-tagged 3D memory sharing",
                    tech = "Android Studio, Firebase, ARCore"
                )
                ProjectItem(
                    name = "CampusFlow",
                    description = "University app enabling role-based access for booking rooms and managing events",
                    tech = "Java, Android Studio, Firebase, Figma"
                )
                ProjectItem(
                    name = "Dare Tiles",
                    description = "AR Challenge Game: Scans real-world objects to trigger dares in an AR environment",
                    tech = "Unity, Blender, Vuforia, AR Kit"
                )
                ProjectItem(
                    name = "AR Hunt",
                    description = "Puzzle-based AR game using real-world markers and C# scripting",
                    tech = "Unity, Blender, Vuforia"
                )
                ProjectItem(
                    name = "HomeRental",
                    description = "Property rental app for listings and tenant management",
                    tech = "Java, Android Studio, Firebase"
                )
            }

            // Extra-Curricular & Leadership
            ResumeSection(
                title = "Extra-Curricular & Leadership",
                icon = Icons.Default.Group
            ) {
                LeadershipItem("Coordinator", listOf(
                    "VRARMR Club",
                    "Bitwise Coding Club",
                    "Mozilla Club",
                    "NSS Club"
                ))
                LeadershipItem("JYC Drama Wing", listOf(
                    "Performer & Coordinator"
                ))
                LeadershipItem("TACHYON (Tech Fest)", listOf(
                    "Organizing team and VRARMR Club representative"
                ))
                LeadershipItem("Achievements", listOf(
                    "HACKTRON, University - Competed in coding challenges",
                    "ICMME Poster Making Certificate - Awarded for creative and immersive idea",
                    "Tech Event Certificates - Participated in various technical events"
                ))
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
        }
    }
}

@Composable
private fun ResumeSection(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = NeonBlue,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = title,
                color = NeonBlue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        HorizontalDivider(color = NeonBlue.copy(alpha = 0.3f))
        content()
    }
}

@Composable
private fun ContactItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = NeonBlue,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SkillCategory(
    title: String,
    skills: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = NeonBlue.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = skill,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = NeonBlue,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun EducationItem(
    degree: String,
    institution: String,
    period: String,
    details: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = degree,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = institution,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
        Text(
            text = period,
            color = NeonBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        details.forEach { detail ->
            Text(
                text = "• $detail",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}

@Composable
private fun CertificationItem(
    name: String,
    issuer: String,
    date: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "$issuer • $date",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
    }
}

@Composable
private fun ProjectItem(
    name: String,
    description: String,
    tech: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = description,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
        Text(
            text = tech,
            color = NeonBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun LeadershipItem(
    role: String,
    details: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = role,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        details.forEach { detail ->
            Text(
                text = "• $detail",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
} 
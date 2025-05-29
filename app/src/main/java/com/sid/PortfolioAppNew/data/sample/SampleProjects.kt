package com.sid.PortfolioAppNew.data.sample

import com.sid.PortfolioAppNew.data.models.Project
import com.sid.PortfolioAppNew.data.models.ProjectCategory
import com.sid.PortfolioAppNew.data.models.ProjectColorPalette
import com.sid.PortfolioAppNew.data.models.ProjectStatus

object SampleProjects {
    private const val GITHUB_RAW_BASE = "https://raw.githubusercontent.com/sidvashisth2005/PortfolioAppNew/main/app/src/main/assets"
    private const val GITHUB_RELEASES_BASE = "https://github.com/sidvashisth2005/PortfolioAppNew/releases/download"

    val projects = listOf(
        Project(
            id = "1",
            title = "ARound You",
            shortDesc = "Multi-Device Augmented Reality Interaction App",
            longDesc = "Developing a location-based AR app using Android Studio, Figma, Firebase, and Google's ARCore and Legends APIs. " +
                      "Facilitates real-time interaction between nearby users through spatial scanning and request-based connections. " +
                      "Allows users to leave geo-tagged 3D \"memory\" models at specific locations, accessible from multiple devices. " +
                      "Merges digital and physical realms to enable immersive, location-aware social experiences in augmented reality.",
            images = listOf(
                "$GITHUB_RAW_BASE/images/projects/around-you/screen1.png",
                "$GITHUB_RAW_BASE/images/projects/around-you/screen2.png"
            ),
            githubLink = "https://github.com/sidvashisth2005/around-you",
            demoLink = "$GITHUB_RELEASES_BASE/v1.0/around-you.apk",
            technologies = listOf(
                "Android Studio",
                "Figma",
                "Firebase",
                "ARCore",
                "Legends API",
                "Kotlin"
            ),
            category = ProjectCategory.AR,
            colorPalette = ProjectColorPalette.AR,
            status = ProjectStatus.IN_PROGRESS
        ),
        Project(
            id = "2",
            title = "Dare Tiles",
            shortDesc = "Augmented Reality Challenge Game",
            longDesc = "Designed a multi-level AR-based game where users scan real-world objects to trigger random dares on their phone screens. " +
                      "Built using Unity, Blender, and Vuforia, leveraging AR markers and dynamic gameplay progression. " +
                      "Encouraged creativity and real-world interaction, enhancing user immersion with custom-selected objects and tasks.",
            images = listOf(
                "$GITHUB_RAW_BASE/images/projects/dare-tiles/screen1.png",
                "$GITHUB_RAW_BASE/images/projects/dare-tiles/screen2.png"
            ),
            githubLink = "https://github.com/sidvashisth2005/dare-tiles",
            demoLink = "$GITHUB_RELEASES_BASE/v1.0/dare-tiles.apk",
            technologies = listOf(
                "Unity",
                "Blender",
                "Vuforia",
                "C#",
                "AR Markers"
            ),
            category = ProjectCategory.GAME,
            colorPalette = ProjectColorPalette.GAME,
            status = ProjectStatus.COMPLETED
        ),
        Project(
            id = "3",
            title = "AR Hunt",
            shortDesc = "Augmented Reality Treasure Hunt Application",
            longDesc = "Developed an immersive AR-based treasure hunt app using Unity, Blender, and Vuforia, with C# for backend scripting. " +
                      "Players receive their first clue and progress through the game by scanning AR-marked locations to reveal the next hint. " +
                      "Emphasized creativity and interactive design to enhance user engagement and puzzle-solving experience.",
            images = listOf(
                "$GITHUB_RAW_BASE/images/projects/ar-hunt/screen1.png",
                "$GITHUB_RAW_BASE/images/projects/ar-hunt/screen2.png"
            ),
            githubLink = "https://github.com/sidvashisth2005/ar-hunt",
            demoLink = "$GITHUB_RELEASES_BASE/v1.0/ar-hunt.apk",
            technologies = listOf(
                "Unity",
                "Blender",
                "Vuforia",
                "C#",
                "AR Markers"
            ),
            category = ProjectCategory.AR,
            colorPalette = ProjectColorPalette.AR,
            status = ProjectStatus.COMPLETED
        ),
        Project(
            id = "4",
            title = "HomeRentalApp",
            shortDesc = "Property Rental and Tenant Management Android App",
            longDesc = "A property rental and tenant management Android app built using Java, Android Studio, and Firebase. " +
                      "Allows landlords to list rental properties with images, descriptions, rent details, and location (Google Maps API), " +
                      "while tenants can search using filters like location, budget, and property type. " +
                      "Key features include Firebase Authentication with role-based access (landlord/tenant), " +
                      "property listing and search with real-time updates, image/document uploads via Firebase Storage, " +
                      "rental request management, and personalized dashboards.",
            images = listOf(
                "$GITHUB_RAW_BASE/images/projects/homerental/screen1.png",
                "$GITHUB_RAW_BASE/images/projects/homerental/screen2.png"
            ),
            githubLink = "https://github.com/sidvashisth2005/homerental",
            demoLink = "$GITHUB_RELEASES_BASE/v1.0/homerental.apk",
            technologies = listOf(
                "Java",
                "Android Studio",
                "Firebase",
                "Google Maps API",
                "Firebase Storage",
                "Firebase Realtime Database"
            ),
            category = ProjectCategory.ANDROID,
            colorPalette = ProjectColorPalette.ANDROID,
            status = ProjectStatus.COMPLETED
        )
    )
} 
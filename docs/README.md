# Portfolio App Documentation

## Overview
Portfolio App is a modern Android application built with Jetpack Compose, showcasing professional projects and skills. The app follows clean architecture principles and uses the latest Android development practices. It features a sleek, cyberpunk-inspired design with neon accents and smooth animations.

## Architecture
The application follows MVVM (Model-View-ViewModel) architecture pattern and is organized into the following layers:

- **UI Layer**: Contains all UI components, screens, and view models
  - Screens: Projects, Skills, About, Contact
  - Components: Custom cards, buttons, animations
  - ViewModels: State management and business logic
- **Domain Layer**: Contains business logic and use cases
  - Project management
  - Skills categorization
  - Data synchronization
- **Data Layer**: Handles data operations and repositories
  - Firebase integration
  - Local caching
  - Sample data management
- **DI Layer**: Manages dependency injection using Hilt

## Key Features
- Modern UI with Jetpack Compose and Material3
- Cyberpunk-inspired design with neon accents
- Dark theme with custom color schemes
- Project showcase with detailed views
  - Project overview
  - Technical details
  - Image gallery
  - Download functionality
- Skills display with categories
- Contact information
- Responsive design
- Offline support with local caching
- GitHub integration for project downloads
- Smooth animations and transitions

## Technical Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Dependency Injection**: Hilt
- **Asynchronous Programming**: Coroutines & Flow
- **Design System**: Material3 with custom theming
- **Navigation**: Compose Navigation
- **Image Loading**: Coil
- **Backend Integration**: Firebase
- **Build System**: Gradle with Kotlin DSL
- **Testing**: JUnit, Espresso, Compose Testing

## Project Structure
```
app/src/main/java/com/sid/PortfolioAppNew/
├── data/           # Data layer implementation
│   ├── models/     # Data models
│   ├── remote/     # Remote data sources
│   ├── repository/ # Repository implementations
│   └── sample/     # Sample data
├── di/             # Dependency injection
├── navigation/     # Navigation components
├── ui/             # UI components and screens
│   ├── components/ # Reusable UI components
│   ├── projects/   # Project-related screens
│   ├── screens/    # Main app screens
│   ├── theme/      # Theme configuration
│   └── viewmodel/  # ViewModels
└── utils/          # Utility classes
    ├── cache/      # Caching utilities
    ├── network/    # Network utilities
    └── download/   # Download management
```

## Getting Started
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application

## Development Guidelines
- Follow Kotlin coding conventions
- Use Compose best practices
- Maintain clean architecture principles
- Write unit tests for business logic
- Document public APIs
- Use meaningful commit messages

## Contributing
Please read our contributing guidelines before submitting pull requests.

## License
This project is licensed under the MIT License - see the LICENSE file for details. 
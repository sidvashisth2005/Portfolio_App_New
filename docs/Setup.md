# Setup Guide

## Prerequisites
- Android Studio Arctic Fox or newer
- JDK 17 or newer
- Android SDK 34 or newer
- Git

## Installation Steps

1. **Clone the Repository**
   ```bash
   git clone [repository-url]
   cd PortfolioAppNew
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory
   - Click "OK"

3. **Sync Project**
   - Wait for the initial Gradle sync to complete
   - If prompted, update the Android Gradle Plugin
   - Install any missing SDK components if prompted

4. **Configure Environment**
   - Create a `local.properties` file in the root directory
   - Add your Android SDK path:
     ```properties
     sdk.dir=/path/to/your/Android/Sdk
     ```

5. **Build the Project**
   ```bash
   ./gradlew clean build
   ```

## Development Setup

### Code Style
- Use the provided code style settings
- Enable "Format on Save" in Android Studio
- Use ktlint for Kotlin code formatting

### Git Workflow
1. Create a new branch for each feature
2. Follow the commit message convention
3. Create a pull request for code review
4. Merge only after approval

### Running Tests
```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "com.sid.PortfolioAppNew.ExampleUnitTest"
```

### Debugging
- Use Android Studio's debugger
- Enable developer options on your device
- Use the provided logging utilities

## Common Issues

### Build Issues
- Clean and rebuild the project
- Invalidate caches and restart
- Update Gradle version if needed

### Runtime Issues
- Check logcat for error messages
- Verify device compatibility
- Ensure all permissions are granted

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a pull request

## Support
For issues and questions:
- Create an issue in the repository
- Contact the maintainers
- Check the documentation 
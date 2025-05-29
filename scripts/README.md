# GitHub Asset Management Script

This script helps manage assets for your portfolio projects by automating the process of uploading images and APKs to GitHub and updating Firestore metadata.

## Prerequisites

1. Python 3.7 or higher
2. GitHub Personal Access Token with the following permissions:
   - `repo` (Full control of private repositories)
   - `write:packages` (Upload packages to GitHub Package Registry)

## Installation

1. Install required Python packages:
   ```bash
   pip install requests
   ```

2. Make the script executable:
   ```bash
   chmod +x manage_assets.py
   ```

## Usage

### Upload Project Images

```bash
./manage_assets.py \
  --token YOUR_GITHUB_TOKEN \
  --owner YOUR_GITHUB_USERNAME \
  --repo portfolio-assets \
  --project around-you \
  --images path/to/image1.png path/to/image2.png
```

### Upload APK and Create Release

```bash
./manage_assets.py \
  --token YOUR_GITHUB_TOKEN \
  --owner YOUR_GITHUB_USERNAME \
  --repo portfolio-assets \
  --project around-you \
  --version v1.0 \
  --apk path/to/app-release.apk
```

### Upload Both Images and APK

```bash
./manage_assets.py \
  --token YOUR_GITHUB_TOKEN \
  --owner YOUR_GITHUB_USERNAME \
  --repo portfolio-assets \
  --project around-you \
  --version v1.0 \
  --images path/to/image1.png path/to/image2.png \
  --apk path/to/app-release.apk \
  --project-id firestore-project-id
```

## Directory Structure

The script will organize your assets in the following structure:

```
portfolio-assets/
├── mockups/
│   ├── around-you/
│   │   ├── screen1.png
│   │   └── screen2.png
│   └── ...
└── releases/
    ├── v1.0/
    │   ├── around-you-v1.apk
    │   └── ...
    └── ...
```

## Firestore Integration

The script will generate the following metadata structure:

```json
{
  "images": [
    "https://raw.githubusercontent.com/username/portfolio-assets/main/mockups/around-you/screen1.png",
    "https://raw.githubusercontent.com/username/portfolio-assets/main/mockups/around-you/screen2.png"
  ],
  "demoLink": "https://github.com/username/portfolio-assets/releases/download/v1.0/around-you-v1.apk"
}
```

## Security Notes

1. Never commit your GitHub token to version control
2. Store your token in a secure environment variable or configuration file
3. Use the minimum required permissions for your GitHub token
4. Regularly rotate your GitHub token

## Error Handling

The script includes error handling for:
- Network connectivity issues
- Invalid file paths
- GitHub API rate limits
- Authentication failures
- Invalid file types

## Contributing

Feel free to submit issues and enhancement requests! 
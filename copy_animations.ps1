# Create assets directory if it doesn't exist
$assetsDir = "app/src/main/assets"
if (-not (Test-Path $assetsDir)) {
    New-Item -ItemType Directory -Path $assetsDir -Force
}

# Copy animation files
$animations = @(
    "LOADING Animation.json",
    "Skills Animation.json",
    "Timeline Animation.json",
    "XR Animation.json",
    "Animation - 1748582018683.json",
    "Animation - 1748582181078.json"
)

foreach ($animation in $animations) {
    if (Test-Path $animation) {
        Copy-Item -Path $animation -Destination $assetsDir -Force
        Write-Host "Copied $animation to $assetsDir"
    } else {
        Write-Host "Warning: $animation not found"
    }
}

Write-Host "Animation files copied successfully!" 
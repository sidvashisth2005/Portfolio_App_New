#!/usr/bin/env python3

import os
import sys
import json
import argparse
import requests
from pathlib import Path
from typing import List, Dict
import base64
from datetime import datetime

class GitHubAssetManager:
    def __init__(self, token: str, owner: str, repo: str):
        self.token = token
        self.owner = owner
        self.repo = repo
        self.base_url = f"https://api.github.com/repos/{owner}/{repo}"
        self.headers = {
            "Authorization": f"token {token}",
            "Accept": "application/vnd.github.v3+json"
        }

    def upload_image(self, image_path: str, project_name: str) -> str:
        """Upload an image to the mockups directory."""
        try:
            # Read image file
            with open(image_path, 'rb') as f:
                content = f.read()

            # Create path in GitHub
            filename = os.path.basename(image_path)
            path = f"mockups/{project_name}/{filename}"

            # Upload file
            url = f"{self.base_url}/contents/{path}"
            data = {
                "message": f"Add {project_name} mockup: {filename}",
                "content": base64.b64encode(content).decode('utf-8')
            }

            response = requests.put(url, headers=self.headers, json=data)
            response.raise_for_status()

            # Return raw URL
            return f"https://raw.githubusercontent.com/{self.owner}/{self.repo}/main/{path}"
        except Exception as e:
            print(f"Error uploading image: {e}")
            sys.exit(1)

    def create_release(self, version: str, apk_path: str, project_name: str) -> str:
        """Create a release and upload APK."""
        try:
            # Create release
            release_url = f"{self.base_url}/releases"
            release_data = {
                "tag_name": version,
                "name": f"{project_name} {version}",
                "body": f"Release {version} of {project_name}",
                "draft": False,
                "prerelease": False
            }

            response = requests.post(release_url, headers=self.headers, json=release_data)
            response.raise_for_status()
            release = response.json()

            # Upload APK
            with open(apk_path, 'rb') as f:
                apk_content = f.read()

            upload_url = release['upload_url'].split('{')[0]
            params = {'name': f"{project_name}-{version}.apk"}
            headers = {
                **self.headers,
                'Content-Type': 'application/vnd.android.package-archive'
            }

            response = requests.post(
                upload_url,
                headers=headers,
                params=params,
                data=apk_content
            )
            response.raise_for_status()

            # Return download URL
            return f"https://github.com/{self.owner}/{self.repo}/releases/download/{version}/{project_name}-{version}.apk"
        except Exception as e:
            print(f"Error creating release: {e}")
            sys.exit(1)

    def update_firestore_metadata(self, project_id: str, metadata: Dict):
        """Update Firestore metadata with new URLs."""
        try:
            # This is a placeholder for Firestore update
            # You'll need to implement the actual Firestore update logic
            print(f"Updating Firestore metadata for project {project_id}")
            print(json.dumps(metadata, indent=2))
        except Exception as e:
            print(f"Error updating Firestore metadata: {e}")
            sys.exit(1)

def main():
    parser = argparse.ArgumentParser(description='Manage GitHub assets for portfolio projects')
    parser.add_argument('--token', required=True, help='GitHub personal access token')
    parser.add_argument('--owner', required=True, help='GitHub repository owner')
    parser.add_argument('--repo', required=True, help='GitHub repository name')
    parser.add_argument('--project', required=True, help='Project name')
    parser.add_argument('--version', default='v1.0', help='Release version')
    parser.add_argument('--images', nargs='+', help='List of image paths to upload')
    parser.add_argument('--apk', help='Path to APK file')
    parser.add_argument('--project-id', help='Firestore project ID for metadata update')

    args = parser.parse_args()

    manager = GitHubAssetManager(args.token, args.owner, args.repo)
    metadata = {
        "images": [],
        "demoLink": None
    }

    # Upload images
    if args.images:
        for image_path in args.images:
            url = manager.upload_image(image_path, args.project)
            metadata["images"].append(url)

    # Upload APK and create release
    if args.apk:
        url = manager.create_release(args.version, args.apk, args.project)
        metadata["demoLink"] = url

    # Update Firestore metadata
    if args.project_id:
        manager.update_firestore_metadata(args.project_id, metadata)

if __name__ == "__main__":
    main() 
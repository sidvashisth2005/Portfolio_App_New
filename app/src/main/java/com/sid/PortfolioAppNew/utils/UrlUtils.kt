package com.sid.PortfolioAppNew.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import android.content.ActivityNotFoundException

object UrlUtils {
    fun openUrl(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open URL: $url", Toast.LENGTH_SHORT).show()
        }
    }

    fun openEmail(context: Context, email: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open email client", Toast.LENGTH_SHORT).show()
        }
    }

    fun openPhone(context: Context, phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        context.startActivity(intent)
    }

    fun downloadResume(context: Context) {
        try {
            // Get the resume file from the workspace folder
            val workspaceDir = File(context.getExternalFilesDir(null), "workspace")
            if (!workspaceDir.exists()) {
                workspaceDir.mkdirs()
            }

            // Copy the resume from assets to workspace if it doesn't exist
            val resumeFile = File(workspaceDir, "Siddhant_Vashisth_Resume.pdf")
            if (!resumeFile.exists()) {
                context.assets.open("Siddhant_Vashisth_Resume.pdf").use { input ->
                    FileOutputStream(resumeFile).use { output ->
                        input.copyTo(output)
                    }
                }
            }

            // Create a content URI for the file
            val contentUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                resumeFile
            )

            // Create and start the download intent
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(contentUri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY
            }

            // Create a chooser intent
            val chooser = Intent.createChooser(intent, "Open Resume")
            try {
                context.startActivity(chooser)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Unable to open resume: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Social media profile URLs
    const val GITHUB_PROFILE = "https://github.com/yourusername"
    const val LINKEDIN_PROFILE = "https://linkedin.com/in/yourusername"
    const val TWITTER_PROFILE = "https://twitter.com/yourusername"
}

@Composable
fun rememberUrlUtils(): UrlUtils {
    val context = LocalContext.current
    return UrlUtils
} 
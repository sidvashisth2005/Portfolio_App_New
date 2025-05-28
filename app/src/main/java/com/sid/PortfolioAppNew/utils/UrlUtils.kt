package com.sid.PortfolioAppNew.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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

    fun shareContent(context: Context, subject: String, text: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, text)
            }
            context.startActivity(Intent.createChooser(intent, "Share via"))
        } catch (e: Exception) {
            Toast.makeText(context, "Could not share content", Toast.LENGTH_SHORT).show()
        }
    }

    fun downloadResume(context: Context, resumeUrl: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resumeUrl))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not download resume", Toast.LENGTH_SHORT).show()
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
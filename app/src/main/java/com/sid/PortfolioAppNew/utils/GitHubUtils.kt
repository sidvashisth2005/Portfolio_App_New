package com.sid.PortfolioAppNew.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubUtils @Inject constructor(
    private val cacheManager: CacheManager,
    private val networkUtils: NetworkUtils
) {
    companion object {
        private const val GITHUB_RAW_BASE = "https://raw.githubusercontent.com/sidvashisth2005/portfolio-assets/main"
        private const val GITHUB_RELEASES_BASE = "https://github.com/sidvashisth2005/portfolio-assets/releases/download"
    }

    fun getImageUrl(projectName: String, imageName: String): String {
        return "$GITHUB_RAW_BASE/mockups/$projectName/$imageName"
    }

    fun getApkUrl(projectName: String, version: String = "v1.0"): String {
        return "$GITHUB_RELEASES_BASE/$version/$projectName-$version.apk"
    }

    fun downloadApk(context: Context, url: String): Flow<DownloadState> = flow {
        try {
            emit(DownloadState.Starting)
            
            // Check network connectivity
            if (!networkUtils.isNetworkAvailable()) {
                emit(DownloadState.Error("No internet connection available"))
                return@flow
            }
            
            // Check cache first
            cacheManager.getCachedFile(url)?.let { cachedFile ->
                emit(DownloadState.Progress(100, 0L))
                openApk(context, cachedFile)
                emit(DownloadState.Success)
                return@flow
            }

            val fileName = url.substringAfterLast("/")
            val tempFile = File(context.cacheDir, fileName)

            // Get file size
            val connection = URL(url).openConnection()
            val fileSize = connection.contentLength.toLong()
            var downloadedSize = 0L
            var lastUpdateTime = System.currentTimeMillis()
            var lastDownloadedSize = 0L

            // Download the file with progress tracking
            connection.getInputStream().use { input ->
                FileOutputStream(tempFile).use { output ->
                    val buffer = ByteArray(8192)
                    var bytes = input.read(buffer)
                    while (bytes >= 0) {
                        output.write(buffer, 0, bytes)
                        downloadedSize += bytes
                        
                        // Calculate download speed
                        val currentTime = System.currentTimeMillis()
                        val timeDiff = currentTime - lastUpdateTime
                        if (timeDiff >= 1000) { // Update every second
                            val speed = (downloadedSize - lastDownloadedSize) * 1000 / timeDiff
                            val progress = (downloadedSize * 100 / fileSize).toInt()
                            emit(DownloadState.Progress(progress, speed))
                            lastUpdateTime = currentTime
                            lastDownloadedSize = downloadedSize
                        }
                        
                        bytes = input.read(buffer)
                    }
                }
            }

            // Cache the downloaded file
            cacheManager.cacheFile(url, tempFile)

            // Open the APK
            openApk(context, tempFile)
            emit(DownloadState.Success)
        } catch (e: Exception) {
            emit(DownloadState.Error(e.message ?: "Unknown error"))
            Toast.makeText(
                context,
                "Failed to download APK: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openApk(context: Context, file: File) {
        val contentUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(contentUri, "application/vnd.android.package-archive")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(intent)
    }
}

sealed class DownloadState {
    object Starting : DownloadState()
    data class Progress(val percentage: Int, val speedBytesPerSecond: Long) : DownloadState()
    object Success : DownloadState()
    data class Error(val message: String) : DownloadState()
} 
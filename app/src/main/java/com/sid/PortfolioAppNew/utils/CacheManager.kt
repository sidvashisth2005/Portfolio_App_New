package com.sid.PortfolioAppNew.utils

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheManager @Inject constructor(
    private val context: Context
) {
    private val cacheDir: File
        get() = File(context.cacheDir, "apk_cache").apply { mkdirs() }

    fun getCachedFile(url: String): File? {
        val fileName = getCacheFileName(url)
        val file = File(cacheDir, fileName)
        return if (file.exists() && !isFileExpired(file)) file else null
    }

    fun cacheFile(url: String, sourceFile: File) {
        val fileName = getCacheFileName(url)
        val cacheFile = File(cacheDir, fileName)
        
        FileInputStream(sourceFile).use { input ->
            FileOutputStream(cacheFile).use { output ->
                input.copyTo(output)
            }
        }
    }

    fun clearCache() {
        cacheDir.listFiles()?.forEach { it.delete() }
    }

    private fun getCacheFileName(url: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(url.toByteArray())
        return md.digest().joinToString("") { "%02x".format(it) }
    }

    private fun isFileExpired(file: File): Boolean {
        val maxAge = 7 * 24 * 60 * 60 * 1000L // 7 days
        return System.currentTimeMillis() - file.lastModified() > maxAge
    }
} 
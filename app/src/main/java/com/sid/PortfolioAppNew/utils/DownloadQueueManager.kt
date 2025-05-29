package com.sid.PortfolioAppNew.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadQueueManager @Inject constructor() {
    private val _downloadQueue = MutableStateFlow<List<DownloadItem>>(emptyList())
    val downloadQueue: StateFlow<List<DownloadItem>> = _downloadQueue

    fun addToQueue(url: String, projectName: String) {
        val currentQueue = _downloadQueue.value
        if (!currentQueue.any { it.url == url }) {
            _downloadQueue.value = currentQueue + DownloadItem(url, projectName)
        }
    }

    fun removeFromQueue(url: String) {
        val currentQueue = _downloadQueue.value
        _downloadQueue.value = currentQueue.filter { it.url != url }
    }

    fun clearQueue() {
        _downloadQueue.value = emptyList()
    }
}

data class DownloadItem(
    val url: String,
    val projectName: String
) 
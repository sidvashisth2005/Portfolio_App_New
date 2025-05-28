package com.sid.PortfolioAppNew.ui.components

import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.graphics.Bitmap
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun PdfViewer(
    pdfUri: Uri,
    modifier: Modifier = Modifier
) {
    var currentPage by remember { mutableStateOf(0) }
    var pageCount by remember { mutableStateOf(0) }
    var pages by remember { mutableStateOf<List<Bitmap>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    LaunchedEffect(pdfUri) {
        try {
            withContext(Dispatchers.IO) {
                val input = context.contentResolver.openFileDescriptor(pdfUri, "r")
                input?.let { fileDescriptor ->
                    val renderer = PdfRenderer(fileDescriptor)
                    pageCount = renderer.pageCount
                    val newPages = mutableListOf<Bitmap>()
                    
                    for (i in 0 until pageCount) {
                        val page = renderer.openPage(i)
                        val bitmap = Bitmap.createBitmap(
                            page.width * 2,
                            page.height * 2,
                            Bitmap.Config.ARGB_8888
                        )
                        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                        newPages.add(bitmap)
                        page.close()
                    }
                    
                    pages = newPages
                    renderer.close()
                    fileDescriptor.close()
                }
                isLoading = false
            }
        } catch (e: Exception) {
            error = e.message
            isLoading = false
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> CircularProgressIndicator()
            error != null -> Text(text = "Error: $error")
            pages.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(pages) { page ->
                        Image(
                            bitmap = page.asImageBitmap(),
                            contentDescription = "PDF Page",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
} 
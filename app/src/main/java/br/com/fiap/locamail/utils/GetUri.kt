package br.com.fiap.locamail.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

fun getFileNameFromUri(uri: Uri, context: Context): String {
    var fileName = "Unknown"
    context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex >= 0) {
                fileName = cursor.getString(nameIndex)
            }
        }
    }
    return fileName.substringBeforeLast(".")
}

fun getSizeFromUri(uri: Uri, context: Context): String {
    var fileSize = "Unknown"
    context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            if (sizeIndex >= 0) {
                fileSize = cursor.getString(sizeIndex)
            }
        }
    }
    return fileSize
}
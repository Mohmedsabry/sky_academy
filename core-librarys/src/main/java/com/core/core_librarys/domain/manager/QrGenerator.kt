package com.core.core_librarys.domain.manager

import android.graphics.Bitmap

interface QrGenerator {
    suspend fun generateQr(
        text: String,
        bgColor: Int,
        qrColor: Int,
        size: Int = 512
    ): Bitmap
}
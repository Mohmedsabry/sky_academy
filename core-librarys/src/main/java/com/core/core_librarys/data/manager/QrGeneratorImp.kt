package com.core.core_librarys.data.manager

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import com.core.core_librarys.domain.manager.QrGenerator
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QrGeneratorImp : QrGenerator {
    @SuppressLint("UseKtx")
    override suspend fun generateQr(
        text: String, bgColor: Int, qrColor: Int, size: Int
    ): Bitmap {
        return withContext(Dispatchers.IO) {
            val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                text,
                BarcodeFormat.QR_CODE,
                size,
                size
            )
            createBitmap(size, size, Bitmap.Config.RGB_565).apply {
                for (x in 0 until size) {
                    for (y in 0 until size) {
                        setPixel(
                            x,
                            y,
                            if (bitMatrix[x, y]) {
                                qrColor
                            } else {
                                bgColor
                            }
                        )
                    }
                }
            }
        }
    }
}
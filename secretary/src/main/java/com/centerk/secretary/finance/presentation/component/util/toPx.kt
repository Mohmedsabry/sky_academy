package com.centerk.secretary.finance.presentation.component.util

import android.content.Context
import androidx.compose.ui.unit.Dp

fun Dp.toPx(
    context: Context
): Float {
    return value *
            context.resources.displayMetrics.density
}
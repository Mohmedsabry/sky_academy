package com.core.ui.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.centery.ui.R
import com.core.core_librarys.domain.util.PaymentStatues
import com.core.ui.theme.OrangeBg
import com.core.ui.theme.OrangeText
import com.core.ui.theme.successBg
import com.core.ui.theme.successColor

@Composable
fun PaymentStatues.getText(): String {
    return when (this) {
        PaymentStatues.Active -> stringResource(R.string.active)
        PaymentStatues.NotPayed -> stringResource(R.string.not_payed)
        PaymentStatues.Suspended -> stringResource(R.string.suspended)
        PaymentStatues.Filter -> {
            ""
        }
    }
}

@Composable
fun PaymentStatues.getBgColor(): Color {
    return when (this) {
        PaymentStatues.Active -> successBg
        PaymentStatues.NotPayed -> OrangeBg
        PaymentStatues.Suspended -> MaterialTheme.colorScheme.errorContainer
        else -> {
            MaterialTheme.colorScheme.secondary
        }
    }
}

@Composable
fun PaymentStatues.getColor(): Color {
    return when (this) {
        PaymentStatues.Active -> successColor
        PaymentStatues.NotPayed -> OrangeText
        PaymentStatues.Suspended -> MaterialTheme.colorScheme.error
        else -> {
            MaterialTheme.colorScheme.secondary
        }
    }
}
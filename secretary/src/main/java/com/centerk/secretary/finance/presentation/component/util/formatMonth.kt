package com.centerk.secretary.finance.presentation.component.util

import androidx.compose.ui.text.capitalize
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatMonth(
    value: String,
    locale: Locale
): String {
    return runCatching {
        val yearMonth = YearMonth.parse(
            value,
            DateTimeFormatter.ofPattern("yyyy/M", locale)
        )
        val formatter = DateTimeFormatter.ofPattern(
            if (locale.language == "ar") {
                "LLLL"
            } else {
                "LLL"
            },
            locale
        )

        yearMonth
            .atDay(1)
            .format(formatter)
            .lowercase(locale)
    }.getOrDefault(value).capitalize(locale)
}
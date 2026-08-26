package com.centerk.secretary.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.centerk.secretary.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.getDayMonthYearFormat(): String {
    val pattern =
        DateTimeFormatter.ofPattern("EEEE,dd LLLL")
    return this.format(pattern)
}

fun LocalDateTime.getStartAndEndTimeFormat(endTime: LocalDateTime): String {
    val pattern =
        DateTimeFormatter.ofPattern("K:mm a")
    return "${this.format(pattern)} - ${endTime.format(pattern)}"
}

@Composable
fun LocalDateTime.getPaidInfo(): String {
    val timePattern = DateTimeFormatter.ofPattern("K:mm a")
    val datePattern = DateTimeFormatter.ofPattern("dd LLLL, k:mm a")
    val currentDate = LocalDateTime.now()
    if (this.toLocalDate() == currentDate.toLocalDate()) {
        return stringResource(R.string.today_with_time, this.format(timePattern))
    }
    if (this.toLocalDate() == currentDate.minusDays(1).toLocalDate()) {
        return stringResource(R.string.yesterday, this.format(timePattern))
    }
    return this.format(datePattern)
}
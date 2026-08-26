package com.centerk.secretary.groups.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.centerk.secretary.util.getDayMonthYearFormat
import com.centerk.secretary.util.getStartAndEndTimeFormat
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime
import java.util.Locale


class GetDayMonthYearFormatTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Locale.setDefault(Locale.of("en"))
    }

    @Test
    fun `test data is formated in day(text),month day`() {
        val local = LocalDateTime.of(2026, 8, 24, 2, 0).getDayMonthYearFormat()
        assertThat(local).isEqualTo("Monday,24 August")
    }

    @Test
    fun `test data is formated in day(text),month day for arabic`() {
        val local = LocalDateTime.of(2026, 10, 21, 2, 0)
        Locale.setDefault(
            Locale.Builder()
                .setLanguage("ar")
                .setRegion("EG")
                .build()
        )
        assertThat(local.getDayMonthYearFormat()).isEqualTo("الأربعاء,21 أكتوبر")
    }
    @Test
    fun `test data is formated in hour and munit`() {
        val local = LocalDateTime.of(2026, 8, 24, 11, 0).getStartAndEndTimeFormat(LocalDateTime.of(2026, 8, 24, 11, 0))
        assertThat(local).isEqualTo("11:00 AM")
    }

    @Test
    fun `test data is formated in hour and munit for arabic`() {
        val local = LocalDateTime.of(2026, 10, 21, 2, 0)
        Locale.setDefault(
            Locale.Builder()
                .setLanguage("ar")
                .setRegion("EG")
                .build()
        )
        assertThat(local.getStartAndEndTimeFormat(LocalDateTime.of(2026, 8, 24, 11, 0))).isEqualTo("2:00 ص")
    }
    @Test
    fun `test data is formated in hour and munit for pm`() {
        val local = LocalDateTime.of(2026, 8, 24, 13, 0).getStartAndEndTimeFormat(LocalDateTime.of(2026, 8, 24, 11, 0))
        assertThat(local).isEqualTo("1:00 PM")
    }

    @Test
    fun `test data is formated in hour and munit for arabic for pm`() {
        val local = LocalDateTime.of(2026, 10, 21, 13, 0)
        Locale.setDefault(
            Locale.Builder()
                .setLanguage("ar")
                .setRegion("EG")
                .build()
        )
        assertThat(local.getStartAndEndTimeFormat(LocalDateTime.of(2026, 8, 24, 11, 0))).isEqualTo("1:00 م")
    }

}
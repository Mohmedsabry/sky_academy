package com.centerk.secretary.finance.presentation.component.util

import com.google.common.truth.Truth
import org.junit.Test
import java.util.Locale


class FormatMonthTest {
    @Test
    fun `test from month with 8 should be Aug`() {
        Truth.assertThat(formatMonth("2026/08", Locale("en"))).isEqualTo("Aug")
    }
    @Test
    fun `test from month with 8 should be Aug in arabic`() {
        Truth.assertThat(formatMonth("2026/08", Locale("ar"))).isEqualTo("أغسطس")
    }
    @Test
    fun `test from month with 1 should be Aug in arabic`() {
        Truth.assertThat(formatMonth("2026/1", Locale("ar"))).isEqualTo("يناير")
    }
}
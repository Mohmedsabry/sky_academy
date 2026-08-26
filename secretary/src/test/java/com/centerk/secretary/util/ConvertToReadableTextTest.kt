package com.centerk.secretary.util

import com.google.common.truth.Truth
import org.junit.Test


class ConvertToReadableTextTest {
    @Test
    fun `test number 1000000 should be 1,000,000`() {
        Truth.assertThat(1000000L.convertToReadableText()).isEqualTo("1,000,000")
    }
    @Test
    fun `test number 1000001 should be 1,000,001`() {
        Truth.assertThat(1000001L.convertToReadableText()).isEqualTo("1,000,001")
    }
    @Test
    fun `test number 1000090 should be 1,000,090`() {
        Truth.assertThat(1000090L.convertToReadableText()).isEqualTo("1,000,090")
    }
    @Test
    fun `test number 1000900 should be 1,000,900`() {
        Truth.assertThat(1000900L.convertToReadableText()).isEqualTo("1,000,900")
    }
    @Test
    fun `test number 99999 should be 99,999`() {
        Truth.assertThat(99999L.convertToReadableText()).isEqualTo("99,999")
    }
    @Test
    fun `test number 100 should be 100`() {
        Truth.assertThat(100L.convertToReadableText()).isEqualTo("100")
    }
    @Test
    fun `test number 999 should be 999`() {
        Truth.assertThat(999L.convertToReadableText()).isEqualTo("999")
    }
    @Test
    fun `test number 1000 should be 1,000`() {
        Truth.assertThat(1000L.convertToReadableText()).isEqualTo("1,000")
    }
}
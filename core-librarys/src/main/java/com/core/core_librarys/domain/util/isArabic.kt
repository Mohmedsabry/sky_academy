package com.core.core_librarys.domain.util

fun String.isArabic(): Boolean {
    return any { it in '\u0600'..'\u06FF' }
}
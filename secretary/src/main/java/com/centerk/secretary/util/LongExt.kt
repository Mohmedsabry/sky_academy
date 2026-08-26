package com.centerk.secretary.util

fun Long.convertToReadableText(): String {
    var number = this
    val str = StringBuilder()
    var mod = 1
    when (number) {
        in 1000000..1000000000 -> {
            mod = 1000000
        }

        in 1000..999999 -> {
            mod = 1000
        }
    }
    do {
        val txt = (number / mod).takeIf { it != 0.toLong() }
        if (txt == null) {
            str.append("000")
        } else {
            when (number) {
                in 10..99 -> str.append('0')
                in 1..9 -> str.append("00")
            }
            str.append(txt)
        }
        str.append(",")
        number %= mod
        mod /= 1000
    } while (mod != 0)
    return str.removeSuffix(",").toString()
}
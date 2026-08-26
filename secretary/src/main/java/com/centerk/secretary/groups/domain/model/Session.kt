package com.centerk.secretary.groups.domain.model

import java.time.LocalDateTime

data class Session(
    val name: String,
    val sessionNumber: String,
    val sessionStartDate: LocalDateTime,
    val sessionEndDate: LocalDateTime,
) {
    companion object {
        fun empty() =
            Session(name = "", sessionNumber = "", LocalDateTime.now(), LocalDateTime.now())
    }
}

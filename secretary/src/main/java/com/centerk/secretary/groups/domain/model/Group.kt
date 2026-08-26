package com.centerk.secretary.groups.domain.model

import com.centerk.secretary.student.domain.model.Student

data class Group(
    val name: String,
    val level: String,
    val teacherName: String,
    val time: String,
    val days: List<String>,
    val students: List<Student>,
    val sessions: List<Session>
) {
    companion object {
        fun empty() = Group(
            name = "",
            level = "",
            teacherName = "",
            time = "",
            days = listOf(),
            students = listOf(),
            sessions = listOf()
        )
    }
}
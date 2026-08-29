package com.centerk.secretary.home.domain

import com.centerk.secretary.groups.domain.model.Session

data class GroupInfo(
    val groupId: String,
    val teacherName: String,
    val startTime: String,
    val endTime: String,
    val groupName: String,
    val groupLevel: String,
    val sessions: List<Session>
) {
    companion object {
        fun empty() = GroupInfo(
            "", "", "", "", "", "",listOf()
        )
    }
}

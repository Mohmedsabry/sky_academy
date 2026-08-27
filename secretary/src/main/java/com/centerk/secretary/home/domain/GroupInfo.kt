package com.centerk.secretary.home.domain

data class GroupInfo(
    val groupId: String,
    val teacherName: String,
    val startTime: String,
    val endTime: String,
    val groupName: String,
    val groupLevel: String
) {
    companion object {
        fun empty() = GroupInfo(
            "", "", "", "", "", ""
        )
    }
}

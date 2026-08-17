package com.centerk.secretary.home.domain

data class Statics(
    val totalStudentNumber: Int,
    val totalStudentPresentToday: Int,
    val totalStudentHasBills: Int,
    val totalGroups: Int,
    val groups: List<GroupInfo>
) {
    companion object {
        fun empty() = Statics(0, 0, 0,0, listOf())
    }
}

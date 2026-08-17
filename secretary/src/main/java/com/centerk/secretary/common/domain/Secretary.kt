package com.centerk.secretary.common.domain

data class Secretary(
    val name: String,
    val phone: String,
    val email: String,
    val profilePic: String?,
    val professionCode: String
) {
    companion object {
        fun empty() = Secretary("", "", "", "", "")
    }
}
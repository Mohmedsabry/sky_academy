package com.centerk.secretary.home.presentation

import com.centerk.secretary.common.domain.Secretary
import com.centerk.secretary.home.domain.Statics

data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val secretary: Secretary = Secretary.empty(),
    val statics: Statics = Statics.empty(),
)

package com.centerk.secretary.groups.presentation

import androidx.compose.runtime.Immutable
import com.centerk.secretary.groups.domain.model.Group

@Immutable
data class GroupState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val groups: List<Group> = listOf(),
)

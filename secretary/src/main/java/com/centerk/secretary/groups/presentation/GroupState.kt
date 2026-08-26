package com.centerk.secretary.groups.presentation

import androidx.compose.runtime.Immutable
import com.centerk.secretary.groups.domain.model.Group
import com.centerk.secretary.groups.domain.model.Session
import com.centerk.secretary.student.domain.model.Student

@Immutable
data class GroupState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val group: Group = Group.empty(),
)

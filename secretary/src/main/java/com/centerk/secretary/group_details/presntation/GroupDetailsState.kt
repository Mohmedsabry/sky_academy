package com.centerk.secretary.group_details.presntation

import com.centerk.secretary.groups.domain.model.Group

data class GroupDetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val group: Group = Group.empty(),
)

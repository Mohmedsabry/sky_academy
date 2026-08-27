package com.centerk.secretary.groups.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.centerk.secretary.R
import com.centerk.secretary.common.presentation.BottomBar
import com.centerk.secretary.common.presentation.util.NavigationRoute
import com.centerk.secretary.navigation.HomeRoutes
import com.core.ui.GroupInfo
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun GroupScreen(
    state: GroupState,
    onAction: (GroupEvents) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.group_details),
                    style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },
        bottomBar = {
            BottomBar(
                selectedItem = NavigationRoute.Groups
            ) { route ->
                if (route == null) return@BottomBar
                onAction(GroupEvents.Navigate(route))
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(5.sdp)
                .padding(top = 5.sdp),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            items(state.groups, key = { it.groupId }) { group ->
                GroupInfo(
                    modifier = Modifier.fillMaxWidth(),
                    groupName = group.name,
                    groupLevel = group.level,
                    teacher = group.teacherName,
                    days = group.days,
                    time = group.time,
                    enabled = true,
                    onClick = { onAction(GroupEvents.Navigate(HomeRoutes.GroupDetails(group.groupId))) }
                )
            }
            item {
                Spacer(Modifier.height(15.sdp))
            }
        }
    }
}
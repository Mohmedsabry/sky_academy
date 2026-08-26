package com.centerk.secretary.groups.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.centerk.secretary.util.getDayMonthYearFormat
import com.centerk.secretary.util.getStartAndEndTimeFormat
import com.centerk.secretary.navigation.HomeRoutes
import com.core.ui.GroupInfo
import com.core.ui.SessionDetails
import com.core.ui.StudentComponent
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp
import kotlin.math.roundToInt

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(5.sdp)
                .padding(top = 5.sdp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            GroupInfo(
                modifier = Modifier.fillMaxWidth(),
                groupName = state.group.name,
                groupLevel = state.group.level,
                teacher = state.group.teacherName,
                days = state.group.days,
                time = state.group.time
            )
            Spacer(Modifier.height(10.sdp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.registered_students, state.group.students.size),
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.add_student),
                    color = MaterialTheme.colorScheme.primary,
                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable {
                        onAction(GroupEvents.Navigate(HomeRoutes.AddStudent))
                    }
                )
            }
            state.group.students.take(4).forEach { student ->
                val presentScore = student.presentScore.div(state.group.sessions.size)
                    .times(100).roundToInt()
                StudentComponent(
                    imagePic = student.studentPic,
                    name = student.name,
                    description = student.studentId,
                    replacementOfQr = "% " + stringResource(
                        R.string.entrance,
                        presentScore
                    )
                )
            }
            Spacer(Modifier.height(10.sdp))
            Text(
                text = stringResource(R.string.upcoming_sessions),
                color = MaterialTheme.colorScheme.secondary,
                style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            state.group.sessions.firstOrNull()?.let { session ->
                SessionDetails(
                    title = session.name,
                    sessionNumber = session.sessionNumber,
                    date = session.sessionStartDate.getDayMonthYearFormat(),
                    time = session.sessionStartDate.getStartAndEndTimeFormat(session.sessionEndDate)
                )
            }
            Spacer(Modifier.height(15.sdp))
        }
    }
}
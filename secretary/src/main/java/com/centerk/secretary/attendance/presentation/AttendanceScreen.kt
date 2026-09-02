package com.centerk.secretary.attendance.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.centerk.secretary.R
import com.core.ui.GroupLayout
import com.core.ui.SearchComponent
import com.core.ui.SnackBarComponent
import com.core.ui.StudentComponent
import com.core.ui.TripleLoading
import com.core.ui.TripleLoadingWithDialog
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp
import kotlin.math.roundToInt

@Composable
fun AttendanceScreen(
    state: AttendanceState,
    snackbarHostState: SnackbarHostState,
    onAction: (AttendanceEvents) -> Unit
) {
    val qrBgColor by animateColorAsState(
        targetValue = if (state.attendanceMethod == AttendanceMethod.QR) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        animationSpec = tween(300, easing = LinearOutSlowInEasing),
        label = "",
    )
    val manualBgColor by animateColorAsState(
        targetValue = if (state.attendanceMethod == AttendanceMethod.Manual) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        animationSpec = tween(300, easing = LinearOutSlowInEasing),
        label = "",
    )
    val keyboard = LocalSoftwareKeyboardController.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.surfaceContainerLowest,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { onAction(AttendanceUiEvents.NavigateUp) }
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(5.sdp)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.take_attendance),
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.titleMedium
                )
                Spacer(Modifier.weight(1f))
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                SnackBarComponent(
                    it.visuals.message
                )
            }
        }
    ) { paddingValues ->
        Crossfade(state.isLoading) { isLoading ->
            when (isLoading) {
                true -> {
                    TripleLoadingWithDialog()
                }

                false -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(5.sdp)
                            .padding(top = 5.sdp),
                        verticalArrangement = Arrangement.spacedBy(5.sdp)
                    ) {
                        item {
                            Text(
                                text = stringResource(R.string.choose_the_group_to_take_attendance),
                                color = MaterialTheme.colorScheme.secondary,
                                style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                        item {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(MaterialTheme.colorScheme.surface),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.sdp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(qrBgColor)
                                        .clickable {
                                            onAction(
                                                AttendanceEvents.OnSelectMethod(
                                                    AttendanceMethod.QR
                                                )
                                            )
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(R.string.scan_qr),
                                        color = if (state.attendanceMethod == AttendanceMethod.QR) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.tertiary,
                                        style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier.padding(5.sdp)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(manualBgColor)
                                        .clickable {
                                            onAction(
                                                AttendanceEvents.OnSelectMethod(
                                                    AttendanceMethod.Manual
                                                )
                                            )
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(R.string.search_manual),
                                        color = if (state.attendanceMethod == AttendanceMethod.Manual) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.tertiary,
                                        style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier.padding(5.sdp)
                                    )
                                }
                            }
                        }
                        item {
                            AnimatedVisibility(
                                visible = state.showSearchBox,
                            ) {
                                SearchComponent(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = state.query,
                                    showFilterIcon = false,
                                    onSearchDone = { keyboard?.hide() },
                                    onTextChange = { onAction(AttendanceEvents.OnQueryChange(it)) },
                                )
                            }
                        }
                        item {
                            AnimatedVisibility(state.showSearchBox) {
                                Text(
                                    text = stringResource(com.centery.ui.R.string.student),
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                        item {
                            Crossfade(
                                Triple(
                                    state.isLoadingStudents,
                                    state.showSearchBox,
                                    state.filteredStudents
                                )
                            ) { localState ->
                                Log.d("state", localState.toString())
                                when {
                                    localState.second && localState.third.isEmpty() -> {
                                        Box(
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(10.sdp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                stringResource(R.string.there_is_no_students),
                                                color = MaterialTheme.colorScheme.secondary,
                                                style = newTypography.bodyLarge
                                            )
                                        }
                                    }

                                    localState.first && localState.second -> {
                                        TripleLoading()
                                    }
                                }
                            }
                        }
                        items(state.filteredStudents) { student ->
                            if (state.showSearchBox && state.isLoadingStudents.not()) {
                                val presentScore =
                                    student.presentScore.div(state.groups.firstOrNull { it.groupId == state.selectedGroup }?.sessions?.size.takeIf { it != 0 }
                                        ?: 1)
                                        .times(100).roundToInt()
                                StudentComponent(
                                    imagePic = student.studentPic,
                                    name = student.name,
                                    description = student.studentId,
                                    replacementOfQr = "% " + stringResource(
                                        R.string.entrance,
                                        presentScore
                                    ),
                                    enabled = true,
                                    isSelected = state.selectedStudentId == student.studentId,
                                    onClick = {
                                        onAction(
                                            AttendanceEvents.OnSelectingStudent(
                                                student.studentId
                                            )
                                        )
                                    }
                                )
                            }
                        }
                        item {
                            Text(
                                text = stringResource(com.centery.ui.R.string.groups),
                                color = MaterialTheme.colorScheme.secondary,
                                style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                        items(
                            items = state.groups,
                            key = { it.groupId }
                        ) { groupInfo ->
                            GroupLayout(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 5.dp),
                                title = groupInfo.groupName,
                                groupCode = groupInfo.groupLevel,
                                teacherName = groupInfo.teacherName,
                                startTime = groupInfo.startTime,
                                endTime = groupInfo.endTime,
                                enabled = true,
                                onClick = { onAction(AttendanceEvents.OnSelectingGroup(groupInfo.groupId)) },
                                isSelected = groupInfo.groupId == state.selectedGroup
                            )
                        }
                        item {
                            Spacer(Modifier.height(10.sdp))
                        }
                        item {
                            OutlinedButton(
                                onClick = {
                                    when (state.attendanceMethod) {
                                        AttendanceMethod.QR -> onAction(AttendanceEvents.OnClickQrScan)
                                        AttendanceMethod.Manual -> onAction(AttendanceEvents.OnClickManaulSearch)
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.background
                                ),
                                border = null,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = if (state.attendanceMethod == AttendanceMethod.QR) stringResource(
                                        R.string.start_scan_qr
                                    ) else stringResource(
                                        R.string.mark_as_attended
                                    ),
                                    style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AttendanceScreenPrev() {
    CenteryTheme {
        AttendanceScreen(AttendanceState(), remember { SnackbarHostState() }) { }
    }
}
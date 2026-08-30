package com.centerk.secretary.confirm_attendance.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.centerk.secretary.R
import com.centerk.secretary.util.getPaidInfo
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.blackAndWhite
import com.core.ui.theme.newTypography
import com.core.ui.theme.successColor
import ir.kaaveh.sdpcompose.sdp
import java.time.LocalDateTime

@Composable
fun ConfirmAttendanceScreen(
    state: ConfirmAttendanceState,
    onAction: (ConfirmAttendanceUiEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, MaterialTheme.colorScheme.surfaceContainerLowest,RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(5.sdp)
                        .clickable { onAction(ConfirmAttendanceUiEvent.NavigateUp) }
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.confirm_attendance),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = newTypography.titleMedium
                )
                Spacer(Modifier.weight(1f))
            }
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 10.sdp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.sdp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.teal_success_circle),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.attendance_taken_successfully),
                color = successColor,
                style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = LocalDateTime.now().getPaidInfo(),
                color = MaterialTheme.colorScheme.tertiary,
                style = newTypography.bodyMedium
            )
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.sdp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.sdp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(5.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.sdp)
                    ) {
                        AsyncImage(
                            model = state.student.studentPic,
                            contentDescription = state.student.name,
                            placeholder = painterResource(R.drawable.educational_academy),
                            error = painterResource(com.centery.ui.R.drawable.educational_academy),
                            modifier = Modifier
                                .size(50.sdp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    MaterialTheme.colorScheme.secondary,
                                    CircleShape
                                ),
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = state.student.name,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = stringResource(
                                    R.string.student_code,
                                    state.student.studentId
                                ),
                                color = MaterialTheme.colorScheme.tertiary,
                                style = newTypography.bodyMedium
                            )
                        }
                    }
                    HorizontalDivider(color = MaterialTheme.colorScheme.tertiary)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(5.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            stringResource(R.string.student_degree),
                            color = MaterialTheme.colorScheme.tertiary,
                            style = newTypography.bodyMedium
                        )
                        Text(
                            state.student.studentLevel,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(5.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            stringResource(R.string.current_group),
                            color = MaterialTheme.colorScheme.tertiary,
                            style = newTypography.bodyMedium
                        )
                        Text(
                            "${state.group.name} - ${state.group.level}",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
            OutlinedButton(
                onClick = { onAction(ConfirmAttendanceUiEvent.NavigateUp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ), border = null
            ) {
                Text(
                    stringResource(R.string.scan_new_student),
                    style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            OutlinedButton(
                onClick = { onAction(ConfirmAttendanceUiEvent.NavigateToHome) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                border = null
            ) {
                Text(
                    stringResource(R.string.end_process),
                    style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ConfirmAttendanceScreenPrev() {
    CenteryTheme {
        ConfirmAttendanceScreen(ConfirmAttendanceState()) { }
    }
}
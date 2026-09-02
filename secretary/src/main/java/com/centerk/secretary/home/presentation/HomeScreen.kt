package com.centerk.secretary.home.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.centerk.secretary.R
import com.centerk.secretary.common.presentation.BottomBar
import com.centerk.secretary.common.presentation.ConfigurationManager
import com.centerk.secretary.home.domain.GroupInfo
import com.centerk.secretary.home.domain.Statics
import com.centerk.secretary.navigation.HomeRoutes
import com.core.ui.GroupLayout
import com.core.ui.QuickActions
import com.core.ui.QuickInfo
import com.core.ui.TripleLoadingWithDialog
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.OrangeBg
import com.core.ui.theme.OrangeText
import com.core.ui.theme.newTypography
import com.core.ui.theme.successBg
import com.core.ui.theme.successColor
import com.core.ui.util.UiMode
import ir.kaaveh.sdpcompose.sdp
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    state: HomeState,
    configurationManager: ConfigurationManager,
    onAction: (HomeEvents) -> Unit
) {
    val pullState = rememberPullToRefreshState()
    val language by configurationManager.language.collectAsStateWithLifecycle()
    val ui by configurationManager.uiMode.collectAsStateWithLifecycle()
    Crossfade(state.isLoading, modifier = Modifier.fillMaxSize()) { isLoading ->
        when (isLoading) {
            true -> {
                TripleLoadingWithDialog()
            }

            false -> {
                PullToRefreshBox(
                    isRefreshing = false,
                    onRefresh = { onAction(HomeEvents.PullToRefresh) },
                    state = pullState,
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .navigationBarsPadding()
                            .statusBarsPadding(),
                        bottomBar = {
                            BottomBar { route ->
                                if (route == null) return@BottomBar
                                onAction(HomeUiEvents.Navigation(route))
                            }
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .padding(10.dp)
                                .verticalScroll(rememberScrollState())
                                .background(MaterialTheme.colorScheme.background),
                            verticalArrangement = Arrangement.spacedBy(8.sdp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.sdp)
                            ) {
                                AsyncImage(
                                    model = state.secretary.profilePic,
                                    contentDescription = state.secretary.name,
                                    placeholder = painterResource(R.drawable.educational_academy),
                                    error = painterResource(com.centery.ui.R.drawable.educational_academy),
                                    modifier = Modifier
                                        .size(40.sdp)
                                        .clip(CircleShape)
                                        .border(
                                            2.dp,
                                            MaterialTheme.colorScheme.secondary,
                                            CircleShape
                                        ),
                                )
                                Text(
                                    text = "${stringResource(R.string.hello)}.${state.secretary.name}",
                                    style = newTypography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(Modifier.weight(1f))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            MaterialTheme.colorScheme.surface,
                                        )
                                        .clickable {
                                            when (ui) {
                                                UiMode.Night.mode -> configurationManager.saveUiMode(
                                                    UiMode.Dark.mode
                                                )

                                                UiMode.Dark.mode -> configurationManager.saveUiMode(
                                                    UiMode.Night.mode
                                                )
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (ui == UiMode.Night.mode) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(20.dp)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(MaterialTheme.colorScheme.surface)
                                        .clickable {
                                            configurationManager.saveLanguage(if (language == "ar") "en" else "ar")
                                        }
                                        .padding(horizontal = 5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = language.capitalize(LocalLocale.current),
                                        color = MaterialTheme.colorScheme.secondary,
                                        style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                            }
                            Text(
                                text = stringResource(R.string.quick_statics),
                                color = MaterialTheme.colorScheme.secondary,
                                style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            FlowRow(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(5.sdp),
                                maxItemsInEachRow = 2,
                                verticalArrangement = Arrangement.spacedBy(5.sdp)
                            ) {
                                QuickInfo(
                                    modifier = Modifier.weight(1f),
                                    title = stringResource(R.string.presents_today),
                                    description = "${state.statics.totalStudentPresentToday} ${
                                        stringResource(
                                            com.centery.ui.R.string.student
                                        )
                                    }",
                                    descriptionColor = successColor,
                                    onClick = {},
                                    bgColor = MaterialTheme.colorScheme.surface
                                )
                                QuickInfo(
                                    modifier = Modifier.weight(1f),
                                    title = stringResource(R.string.total_students),
                                    description = "${state.statics.totalStudentNumber} ${
                                        stringResource(
                                            com.centery.ui.R.string.student
                                        )
                                    }",
                                    onClick = {},
                                    bgColor = MaterialTheme.colorScheme.surface
                                )
                                QuickInfo(
                                    modifier = Modifier.weight(1f),
                                    title = stringResource(R.string.payment_arrears),
                                    description = "${state.statics.totalStudentHasBills} ${
                                        stringResource(
                                            com.centery.ui.R.string.student
                                        )
                                    }",
                                    descriptionColor = MaterialTheme.colorScheme.error,
                                    onClick = {},
                                    bgColor = MaterialTheme.colorScheme.surface
                                )
                                QuickInfo(
                                    modifier = Modifier.weight(1f),
                                    title = stringResource(R.string.total_groups),
                                    description = "${state.statics.totalGroups} ${
                                        stringResource(
                                            com.centery.ui.R.string.groups
                                        )
                                    }",
                                    onClick = {},
                                    bgColor = MaterialTheme.colorScheme.surface
                                )
                            }
                            Text(
                                text = stringResource(R.string.quick_actions),
                                color = MaterialTheme.colorScheme.secondary,
                                style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.sdp)
                            ) {
                                QuickActions(
                                    modifier = Modifier.weight(1f),
                                    title = stringResource(R.string.add_student),
                                    icon = R.drawable.add_user,
                                    background = OrangeBg,
                                    tintColor = OrangeText,
                                    onClick = {}
                                )
                                QuickActions(
                                    modifier = Modifier.weight(1f),
                                    title = stringResource(R.string.pay_bills),
                                    icon = R.drawable.wallet,
                                    background = successBg,
                                    tintColor = successColor,
                                    onClick = { onAction(HomeUiEvents.Navigation(HomeRoutes.ReceiveBills)) }
                                )
                                QuickActions(
                                    modifier = Modifier.weight(1f),
                                    title = stringResource(R.string.scan_qr),
                                    icon = R.drawable.scan,
                                    background = MaterialTheme.colorScheme.background,
                                    tintColor = MaterialTheme.colorScheme.secondary,
                                    onClick = { onAction(HomeUiEvents.Navigation(HomeRoutes.AttendanceScreen)) }
                                )
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(R.string.today_sessions),
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = stringResource(R.string.show_all),
                                    color = MaterialTheme.colorScheme.primary,
                                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.clickable {}
                                )
                            }
                            state.statics.groups.forEach { groupInfo ->
                                GroupLayout(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 5.dp),
                                    title = groupInfo.groupName,
                                    groupCode = groupInfo.groupLevel,
                                    teacherName = groupInfo.teacherName,
                                    startTime = groupInfo.startTime,
                                    endTime = groupInfo.endTime
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
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomePrev() {
    CenteryTheme {
        HomeScreen(
            HomeState(
                statics = Statics.empty().copy(
                    groups = listOf(
                        GroupInfo(
                            teacherName = "mohmed",
                            startTime = "4:00 pm",
                            endTime = "5:30 pm",
                            groupName = "math",
                            groupLevel = "Group A",
                            groupId = "id",
                            sessions = listOf()
                        )
                    )
                )
            ),
            configurationManager = ConfigurationManager(koinInject())
        ) { }
    }
}
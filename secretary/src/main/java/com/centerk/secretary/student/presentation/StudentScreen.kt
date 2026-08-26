@file:OptIn(ExperimentalMaterial3Api::class)

package com.centerk.secretary.student.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.centerk.secretary.R
import com.centerk.secretary.common.presentation.BottomBar
import com.centerk.secretary.common.presentation.util.NavigationRoute
import com.centerk.secretary.navigation.HomeRoutes
import com.core.core_librarys.domain.util.PaymentStatues
import com.core.ui.ChipComponent
import com.core.ui.SearchBottomSheet
import com.core.ui.SearchComponent
import com.core.ui.StudentComponent
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@Composable
fun StudentScreen(
    state: StudentState,
    onAction: (StudentEvents) -> Unit
) {
    val searchSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    AnimatedVisibility(searchSheetState.isVisible) {
        SearchBottomSheet(
            sheetState = searchSheetState,
            items = state.filterTags,
            onDismiss = { scope.launch { searchSheetState.hide() } },
            onSelectItem = { onAction(StudentEvents.OnSelectingTags(it)) },
            isSelected = state.selectedTags
        )
    }
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
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                horizontalArrangement = Arrangement.spacedBy(5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.student_management),
                    style = newTypography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable {
                            onAction(StudentUiEvents.OnNavigation(HomeRoutes.AddStudent))
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_user),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .padding(5.sdp)
                    )
                }
            }
        },
        bottomBar = {
            BottomBar(
                selectedItem = NavigationRoute.Student,
                onClick = { route ->
                    if (route == null) return@BottomBar
                    onAction(StudentUiEvents.OnNavigation(route))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAction(StudentUiEvents.OnNavigation(HomeRoutes.AddStudent)) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ) {
                Text(
                    text = stringResource(R.string.add_student),
                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(5.sdp)
                )
            }
        }
    ) { paddingValues ->
        val scroll = rememberScrollState()
        val focusManager = LocalFocusManager.current
        val focusRequester = remember {
            FocusRequester()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(paddingValues)
                .padding(5.sdp),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            SearchComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                text = state.query,
                onSearchDone = {
                    focusManager.clearFocus()
                }, onClickFilter = {
                    scope.launch {
                        searchSheetState.expand()
                    }
                },
                onTextChange = { onAction(StudentEvents.OnTyping(it)) }
            )
            FlowRow(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.sdp),
                maxItemsInEachRow = 4,
                maxLines = 2,
            ) {
                ChipComponent(
                    text = stringResource(R.string.suspended_numeric, state.suspendedStudent),
                    isSelected = state.paymentStatues != null && state.paymentStatues == PaymentStatues.Suspended,
                    onSelect = { onAction(StudentEvents.OnSelectingPayment(PaymentStatues.Suspended)) }
                )
                ChipComponent(
                    text = stringResource(R.string.not_payed_numeric, state.unPaidStudent),
                    isSelected = state.paymentStatues != null && state.paymentStatues == PaymentStatues.NotPayed,
                    onSelect = { onAction(StudentEvents.OnSelectingPayment(PaymentStatues.NotPayed)) }
                )
                ChipComponent(
                    text = stringResource(R.string.active_numeric, state.activaStudent),
                    isSelected = state.paymentStatues != null && state.paymentStatues == PaymentStatues.Active,
                    onSelect = { onAction(StudentEvents.OnSelectingPayment(PaymentStatues.Active)) }
                )
                ChipComponent(
                    text = stringResource(R.string.all_numeric, state.students.size),
                    isSelected = state.paymentStatues == null,
                    onSelect = { onAction(StudentEvents.OnSelectingPayment(null)) }
                )
            }
            state.filteredStudent.forEach { student ->
                StudentComponent(
                    modifier = Modifier.fillMaxWidth(),
                    imagePic = student.studentPic,
                    name = student.name,
                    description = student.studentLevel,
                    statues = student.paymentStatutes,
                    qrIcon = state.qrCodes[student.studentId]
                )
            }
        }
    }
}
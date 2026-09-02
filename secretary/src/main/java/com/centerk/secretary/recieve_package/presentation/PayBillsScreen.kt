package com.centerk.secretary.recieve_package.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.centerk.secretary.R
import com.core.ui.SearchComponent
import com.core.ui.StudentPaymentComponent
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PayBillsScreen(
    state: PayBillsState,
    onAction: (PayBillsEvents) -> Unit
) {
    val keyBoard = LocalSoftwareKeyboardController.current
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
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.surfaceContainerLowest,
                            RoundedCornerShape(10.dp)
                        )
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable { onAction(PayBillsUiEvents.NavigateUp) }
                        .padding(5.sdp)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.recieve_bills),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = newTypography.titleMedium
                )
                Spacer(Modifier.weight(1f))
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.sdp)
                .padding(paddingValues)
                .padding(top = 10.sdp),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            item {
                SearchComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.query,
                    onSearchDone = {
                        keyBoard?.hide()
                    },
                    showFilterIcon = false,
                    onTextChange = { onAction(PayBillsEvents.OnTyping(it)) }
                )
            }
            item {
                Text(
                    stringResource(R.string.students_with_outstanding_payments),
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodyLarge
                )
            }
            items(state.filteredStudents, key = { it.studentId }) { student ->
                StudentPaymentComponent(
                    name = student.name,
                    level = student.studentLevel,
                    paymentStatues = student.paymentStatutes,
                    pic = student.studentPic,
                    amount = state.billsDetails[student.studentId] ?: 0L,
                    onClick = { onAction(PayBillsEvents.OnStudentClicked(student.studentId)) }
                )
            }
        }
    }
}
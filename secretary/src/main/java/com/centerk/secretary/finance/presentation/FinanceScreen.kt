package com.centerk.secretary.finance.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.centerk.secretary.R
import com.centerk.secretary.common.presentation.BottomBar
import com.centerk.secretary.common.presentation.util.NavigationRoute
import com.centerk.secretary.finance.presentation.component.RevenueChart
import com.centerk.secretary.util.convertToReadableText
import com.centerk.secretary.util.getPaidInfo
import com.core.ui.QuickInfo
import com.core.ui.TransactionComponent
import com.core.ui.theme.newTypography
import com.core.ui.theme.successBg
import com.core.ui.theme.successColor
import ir.kaaveh.sdpcompose.sdp

@Composable
fun FinanceScreen(
    state: FinanceState,
    onAction: (FinanceEvents) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.finance_history),
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        },
        bottomBar = {
            BottomBar(selectedItem = NavigationRoute.Finance) { route ->
                if (route == null) return@BottomBar
                onAction(FinanceUiEvents.Navigate(route))
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(5.sdp)
                .padding(top = 10.sdp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                QuickInfo(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.total_income),
                    titleColor = successColor,
                    bgColor = successBg,
                    description = stringResource(
                        R.string.in_come,
                        state.totalIncome.convertToReadableText()
                    ),
                    descriptionColor = MaterialTheme.colorScheme.secondary,
                    onClick = {}
                )
                QuickInfo(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.total_outcome),
                    titleColor = MaterialTheme.colorScheme.error,
                    bgColor = MaterialTheme.colorScheme.errorContainer,
                    description = stringResource(
                        R.string.out_come,
                        state.totalOutCome.convertToReadableText()
                    ),
                    descriptionColor = MaterialTheme.colorScheme.secondary,
                    onClick = {}
                )
            }
            RevenueChart(
                title = stringResource(R.string.total_income_last_months, state.monthsCount),
                chartItems = state.statics.takeLast(state.monthsCount),
                showValues = state.showValues,
                onChangeShowValus = { onAction(FinanceEvents.UpdateShowValus) },
                monthCount = state.monthsCount,
                onChangeMonthCount = { onAction(FinanceEvents.NumberOfMonthsShown(it)) }
            )
            Text(
                text = stringResource(R.string.latest_transaction),
                color = MaterialTheme.colorScheme.secondary,
                style = newTypography.bodyMedium
            )
            state.transactions.take(3).forEach { transaction ->
                TransactionComponent(
                    title = transaction.studentName,
                    description = "${transaction.transactionDate.getPaidInfo()} • ${stringResource(R.string.subscription)} ${transaction.paidMonth}",
                    amount = "${transaction.amount} ${stringResource(R.string.eg)}"
                )
            }
            Spacer(Modifier.height(10.sdp))
        }
    }
}
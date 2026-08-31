package com.centerk.secretary.payment_details.presentation

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.centerk.secretary.R
import com.centerk.secretary.payment_details.presentation.util.PaymentStrategy
import com.core.ui.PaymentStrategyComponent
import com.core.ui.SnackBarComponent
import com.core.ui.StudentComponent
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PaymentDetailsScreen(
    state: PaymentDetailsState,
    onAction: (PaymentDetailsEvents) -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Scaffold(
        Modifier
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
                        .clickable { onAction(PaymentDetailsUiEvents.NavigateUp) }
                        .padding(5.sdp)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.payment_details),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = newTypography.titleMedium
                )
                Spacer(Modifier.weight(1f))
            }
        },
        snackbarHost = {
            SnackbarHost(state.snackbarHostState) {
                SnackBarComponent(
                    it.visuals.message
                )
            }
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.sdp)
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 10.sdp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            StudentComponent(
                modifier = Modifier.fillMaxWidth(),
                imagePic = state.student.studentPic,
                name = state.student.name,
                description = state.student.studentLevel
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(10.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.alert_circle),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
                Text(
                    stringResource(com.centery.ui.R.string.due_for_payment),
                    color = MaterialTheme.colorScheme.error,
                    style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "${state.amountShouldPaid} ${stringResource(R.string.eg)}",
                    color = MaterialTheme.colorScheme.error,
                    style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
            Text(
                "${stringResource(R.string.recieved_money)} (${stringResource(R.string.eg)})",
                color = MaterialTheme.colorScheme.tertiary,
                style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            OutlinedTextField(
                value = state.receivedAmount,
                onValueChange = { onAction(PaymentDetailsEvents.OnChangeAmount(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp)),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.wallet),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboard?.hide() }
                ),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                )
            )
            Text(
                stringResource(R.string.payment_strategy),
                color = MaterialTheme.colorScheme.tertiary,
                style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                PaymentStrategyComponent(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.cash),
                    icon = R.drawable.banknote,
                    isSelected = state.paymentStrategy == PaymentStrategy.Cash,
                    onClick = { onAction(PaymentDetailsEvents.OnChangePayment(PaymentStrategy.Cash)) }
                )
                PaymentStrategyComponent(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.wallet),
                    icon = R.drawable.smartphone,
                    isSelected = state.paymentStrategy == PaymentStrategy.Wallet,
                    onClick = { onAction(PaymentDetailsEvents.OnChangePayment(PaymentStrategy.Wallet)) }
                )
            }
            Spacer(Modifier.weight(1f))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onClick = { onAction(PaymentDetailsEvents.OnConfirmPayment) },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(10.dp),
                border = null,
            ) {
                Text(
                    stringResource(R.string.confirm_payment),
                    style = newTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
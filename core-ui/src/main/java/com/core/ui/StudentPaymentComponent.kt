package com.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.centery.ui.R
import com.core.core_librarys.domain.util.PaymentStatues
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import com.core.ui.util.getBgColor
import com.core.ui.util.getColor
import com.core.ui.util.getText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun StudentPaymentComponent(
    modifier: Modifier = Modifier,
    name: String,
    level: String,
    paymentStatues: PaymentStatues,
    pic: String?,
    amount: Long,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 5.dp),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = pic,
                    contentDescription = null,
                    error = painterResource(R.drawable.groups),
                    placeholder = painterResource(R.drawable.groups),
                    modifier = Modifier
                        .size(25.sdp)
                        .clip(CircleShape)
                )
                Text(
                    text = paymentStatues.getText(),
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(paymentStatues.getBgColor())
                        .padding(5.dp),
                    color = paymentStatues.getColor(),
                    style = newTypography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.ssp
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(2.sdp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = name,
                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = level,
                    style = newTypography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.spacedBy(5.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.due_for_payment),
                    style = newTypography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "$amount ${stringResource(R.string.eg)}",
                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = paymentStatues.getColor()
                )
            }
        }
    }
}

@Preview
@Composable
private fun StudentPaymentComponentPrev() {
    CenteryTheme {
        StudentPaymentComponent(
            Modifier,
            name = "Mohamed",
            level = "first grade",
            paymentStatues = PaymentStatues.NotPayed,
            pic = null,
            amount = 450,
            onClick = { },
        )
    }
}
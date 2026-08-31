package com.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TransactionComponent(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    amount: String
) {
    OutlinedCard(
        enabled = false,
        onClick = {},
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 15.sdp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.sdp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = newTypography.bodySmall
                )
            }
            Text(
                text = amount,
                color = MaterialTheme.colorScheme.secondary,
                style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}
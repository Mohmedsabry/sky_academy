package com.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.centery.ui.R
import com.core.ui.theme.OrangeText
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SessionDetails(
    modifier: Modifier = Modifier,
    title: String,
    sessionNumber: String,
    date: String,
    time: String,
    enableClick: Boolean = false
) {
    OutlinedCard(
        onClick = {},
        enabled = enableClick,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 5.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 15.sdp),
            verticalArrangement = Arrangement.spacedBy(5.sdp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$sessionNumber : $title",
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.next_lesson),
                    color = OrangeText,
                    style = newTypography.bodyMedium
                )
            }
            HorizontalDivider()
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = newTypography.bodySmall
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = time,
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodySmall.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
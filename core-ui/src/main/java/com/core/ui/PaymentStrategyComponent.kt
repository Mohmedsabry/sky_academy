package com.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.ui.theme.newTypography
import com.core.ui.theme.selectedColor
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PaymentStrategyComponent(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier
            .clickable(onClick=onClick)
            .clip(RoundedCornerShape(10.dp))
            .background(if (!isSelected) MaterialTheme.colorScheme.surface else selectedColor)
            .border(
                if (isSelected) 1.sdp else 0.sdp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(10.dp)
            )
            .shadow(5.dp)
            .padding(10.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.sdp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
        )
        Text(
            title,
            color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary,
            style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}
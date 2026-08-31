package com.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SnackBarComponent(message: String) {
    Text(
        message,
        color = MaterialTheme.colorScheme.primary,
        style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(12.sdp)
    )
}
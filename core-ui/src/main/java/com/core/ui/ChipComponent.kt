package com.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.core.core_librarys.domain.util.isArabic
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ChipComponent(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = if (text.isArabic()) 11.ssp else 8.ssp,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    InputChip(
        selected = isSelected,
        onClick = onSelect,
        label = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.secondary,
                style = newTypography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize,
                    textDirection = if (text.isArabic()) TextDirection.Rtl else TextDirection.Ltr
                ),
                modifier = Modifier.padding(5.dp)
            )
        },
        modifier = modifier,
        colors = InputChipDefaults.inputChipColors(
            containerColor = MaterialTheme.colorScheme.surface,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(20.dp)
    )
}
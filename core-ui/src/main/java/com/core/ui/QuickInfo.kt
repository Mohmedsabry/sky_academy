package com.core.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@OptIn(ExperimentalGridApi::class)
@Composable
fun QuickInfo(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    descriptionColor: Color = MaterialTheme.colorScheme.secondary,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 15.sdp, horizontal = 5.sdp),
            verticalArrangement = Arrangement.spacedBy(5.sdp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = description,
                style = newTypography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = descriptionColor
            )
        }
    }
}

@Preview(locale = "ar")
@Preview(uiMode = UI_MODE_NIGHT_YES, locale = "ar")
@Composable
private fun QuickInfoPrev() {
    CenteryTheme {
        val list = listOf(
            "حضور اليوم" to "112 طالب",
            "إجمالي الطلاب" to "127 طالباً",
            "متأخر السداد" to "12 طالباً",
            "الإيراد الشهرى" to "50 الف"
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(5.sdp)
                .background(MaterialTheme.colorScheme.background),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(5.sdp),
            verticalArrangement = Arrangement.spacedBy(5.sdp)
        ) {
            items(list) { item ->
                QuickInfo(
                    title = item.first,
                    description = item.second,
                    onClick = {}
                )
            }
        }
    }
}
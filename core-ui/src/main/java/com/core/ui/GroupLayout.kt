package com.core.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.centery.ui.R
import com.core.core_librarys.domain.util.isArabic
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun GroupLayout(
    modifier: Modifier = Modifier,
    title: String,
    groupCode: String,
    teacherName: String,
    startTime: String,
    endTime: String
) {
    OutlinedCard(
        onClick = {},
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        textDirection = if (title.isArabic()) TextDirection.Rtl else TextDirection.Ltr
                    )
                )
                Text(
                    text = groupCode,
                    color = MaterialTheme.colorScheme.primary,
                    style = newTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        textDirection = if (groupCode.isArabic()) TextDirection.Rtl else TextDirection.Ltr
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                        .padding(5.sdp)
                )
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${stringResource(R.string.mr)} $teacherName",
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccessTime,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = "$startTime - $endTime",
                        color = MaterialTheme.colorScheme.secondary,
                        style = newTypography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textDirection = if (startTime.isArabic() || endTime.isArabic()) TextDirection.Rtl else TextDirection.Ltr
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun GroupLayoutPrev() {
    CenteryTheme {
        GroupLayout(
            modifier = Modifier.fillMaxWidth(),
            title = "حصة math",
            groupCode = "مجموعة A",
            teacherName = "محمد صبرى",
            startTime = "4:00 م",
            endTime = "5:30 م",
        )
    }
}
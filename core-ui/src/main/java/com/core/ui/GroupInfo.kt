package com.core.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.centery.ui.R
import com.core.core_librarys.domain.util.isArabic
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun GroupInfo(
    modifier: Modifier = Modifier,
    groupName: String,
    groupLevel: String,
    teacher: String,
    days: List<String>,
    time: String,
    enabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    OutlinedCard(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.outlinedCardElevation(5.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.sdp),
            verticalArrangement = Arrangement.spacedBy(5.sdp),
        ) {
            Text(
                text = groupName,
                color = MaterialTheme.colorScheme.secondary,
                style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = groupLevel,
                color = MaterialTheme.colorScheme.tertiary,
                style = newTypography.bodyMedium
            )
            HorizontalDivider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(R.string.mr)} $teacher",
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodySmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = days.joinToString(" ${stringResource(R.string.dlimater)}")
                        .removeSuffix(stringResource(R.string.dlimater)).plus(" "),
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.ssp
                    ),
                    modifier = Modifier.widthIn(max = 100.sdp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = time,
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.ssp,
                        textDirection = if (time.isArabic()) TextDirection.Rtl else TextDirection.Ltr
                    )
                )
                Icon(
                    imageVector = Icons.Rounded.AccessTime,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(10.sdp)
                )
            }
        }
    }
}

@Preview(locale = "ar")
@Preview(uiMode = UI_MODE_NIGHT_YES, locale = "ar")
@Composable
private fun GroupInfoPrev() {
    CenteryTheme {
        GroupInfo(
            modifier = Modifier.fillMaxWidth(),
            groupName = "حصة math",
            groupLevel = "مجموعة A",
            teacher = "محمد صبرى عبدالعظيم",
            time = "4:00 - 5:00 م",
            days = listOf("الإربعاء", "الأثنين", "الثلاثاء", "الإربعاء", "الجمعة")
        )
    }
}
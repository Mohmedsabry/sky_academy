package com.core.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.centery.ui.R
import com.core.core_librarys.domain.util.PaymentStatues
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import com.core.ui.theme.selectedColor
import com.core.ui.util.getBgColor
import com.core.ui.util.getColor
import com.core.ui.util.getText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun StudentComponent(
    modifier: Modifier = Modifier,
    imagePic: String?,
    name: String,
    description: String,
    statues: PaymentStatues? = null,
    qrIcon: Bitmap? = null,
    replacementOfQr: String? = null,
    enabled: Boolean = false,
    onClick: () -> Unit = {},
    isSelected: Boolean = false
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (!isSelected) MaterialTheme.colorScheme.surface else selectedColor,
            disabledContainerColor = if (!isSelected) MaterialTheme.colorScheme.surface else selectedColor,
        ),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 5.dp),
        onClick = onClick,
        enabled = enabled,
        border = BorderStroke(
            if (isSelected) 1.sdp else 0.sdp,
            MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.sdp, horizontal = 10.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = imagePic,
                    contentDescription = null,
                    error = painterResource(R.drawable.groups),
                    placeholder = painterResource(R.drawable.groups),
                    modifier = Modifier
                        .size(25.sdp)
                        .clip(CircleShape)
                )
                if (statues == null) return@Column
                Text(
                    text = statues.getText(),
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(statues.getBgColor())
                        .padding(5.dp),
                    color = statues.getColor(),
                    style = newTypography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 7.ssp
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(2.sdp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = name,
                    style = newTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = description,
                    style = newTypography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerLowest),
                contentAlignment = Alignment.Center
            ) {
                if (qrIcon != null) {
                    Image(
                        bitmap = qrIcon.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.sdp)
                            .size(30.sdp)
                    )
                    return@Box
                }
                if (replacementOfQr != null) {
                    Text(
                        text = replacementOfQr,
                        color = MaterialTheme.colorScheme.primary,
                        style = newTypography.bodySmall,
                        modifier = Modifier.padding(5.sdp)
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun StudentPrev() {
    CenteryTheme {
        StudentComponent(
            imagePic = null,
            name = "Mohmed sabry",
            description = "Math",
            statues = PaymentStatues.Active,
            replacementOfQr = "٩٥٪ حضور"
        )
    }
}
package com.centerk.secretary.qr_scanner.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.centerk.secretary.R
import com.core.core_librarys.domain.util.isArabic
import com.core.ui.TripleLoadingWithDialog
import com.core.ui.theme.groupInQrBG
import com.core.ui.theme.newTypography
import com.core.ui.theme.qrBackground
import ir.kaaveh.sdpcompose.sdp
import qrscanner.QrScanner

@Composable
fun QrScreen(
    state: QrState,
    snackbarHostState: SnackbarHostState,
    onAction: (QrEvents) -> Unit
) {
    var flashLightOn by remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(qrBackground),
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.surfaceContainerLowest,
                            RoundedCornerShape(10.dp)
                        )
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(5.sdp)
                        .clickable { onAction(QrUiEvents.NavigateUp) }
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.qr_scanner),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = newTypography.titleMedium
                )
                Spacer(Modifier.weight(1f))
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Crossfade(
            state.isLoading
        ) { isLoading ->
            when (isLoading) {
                true -> {
                    TripleLoadingWithDialog()
                }

                false -> {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(top = 10.sdp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.sdp),
                    ) {
                        Spacer(Modifier.height(10.sdp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(10.sdp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(groupInQrBG)
                                .padding(vertical = 10.sdp, horizontal = 8.sdp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.sdp)
                        ) {
                            Text(
                                text = state.group.name,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = newTypography.bodyLarge
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = state.group.level,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = newTypography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    textDirection = if (state.group.level.isArabic()) TextDirection.Rtl else TextDirection.Ltr
                                ),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary)
                                    .padding(5.sdp)
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(250.sdp)
                                .align(Alignment.CenterHorizontally)
                                .clip(shape = RoundedCornerShape(size = 14.sdp))
                                .clipToBounds()
                                .border(
                                    2.sdp,
                                    MaterialTheme.colorScheme.surfaceContainerLowest,
                                    RoundedCornerShape(size = 14.sdp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            QrScanner(
                                modifier = Modifier
                                    .clipToBounds()
                                    .clip(shape = RoundedCornerShape(size = 14.sdp)),
                                flashlightOn = flashLightOn,
                                openImagePicker = false,
                                onCompletion = { onAction(QrEvents.OnScanQr(it)) },
                                imagePickerHandler = {},
                                onFailure = {}
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        OutlinedButton(
                            onClick = {
                                flashLightOn = !flashLightOn
                            },
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.background),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(0.7f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.sdp)
                            ) {
                                Text(
                                    if (flashLightOn) stringResource(R.string.flash_light_off) else stringResource(
                                        R.string.flash_light_on
                                    ),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = newTypography.bodyLarge
                                )
                                Icon(
                                    painter = painterResource(com.centery.ui.R.drawable.light),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                        Spacer(Modifier.height(20.sdp))
                    }
                }
            }
        }
    }
}
package com.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.centery.ui.R
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuarterMonthsDropDown(
    modifier: Modifier = Modifier,
    selectedMonth: Int,
    onSelectMonth: (Int) -> Unit
) {
    var isExpended by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = isExpended,
        onExpandedChange = { isExpended = !isExpended },
        modifier = modifier,
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.background)
                .clickable { isExpended = !isExpended }
                .padding(5.sdp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$selectedMonth ${stringResource(R.string.month)}",
                    color = MaterialTheme.colorScheme.secondary,
                    style = newTypography.bodyMedium
                )
                ExposedDropdownMenu(
                    modifier = modifier,
                    expanded = isExpended,
                    onDismissRequest = { isExpended = false },
                    containerColor = MaterialTheme.colorScheme.background,
                ) {
                    for (i in 3..12 step 3) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "$i ${stringResource(R.string.month)}",
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = newTypography.bodySmall.copy(
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = false
                                        ),
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.CenterHorizontally),
                                    textAlign = TextAlign.Center
                                )
                            },
                            onClick = {
                                onSelectMonth(i)
                                isExpended = !isExpended
                            },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DropDown() {
    CenteryTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            QuarterMonthsDropDown(modifier = Modifier.size(65.sdp), selectedMonth = 3) { }
        }
    }
}
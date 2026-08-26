@file:OptIn(ExperimentalMaterial3Api::class)

package com.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.centery.ui.R
import com.core.core_librarys.domain.util.isArabic
import com.core.ui.theme.CenteryTheme
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    items: List<String>,
    onSelectItem: (String) -> Unit,
    isSelected: Map<String, Boolean>,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .heightIn(min = 250.sdp),
        sheetState = sheetState,
        dragHandle = {
            Image(
                painter = painterResource(R.drawable.rectangle_14),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.sdp)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.sdp),
            horizontalArrangement = Arrangement.spacedBy(5.sdp),
            maxItemsInEachRow = 2
        ) {
            items.forEach { item ->
                ChipComponent(
                    text = item,
                    fontSize = 10.ssp,
                    isSelected = isSelected[item] ?: false,
                    onSelect = {
                        onSelectItem(item)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShowSearchBottomSheet() {
    CenteryTheme {
        val sheetState = rememberModalBottomSheetState()
        var selectedItem by remember {
            mutableStateOf(mapOf("Ali" to true))
        }
        val scope = rememberCoroutineScope()
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Button(onClick = { scope.launch { if (!sheetState.isVisible) sheetState.show() } }) {
                Text("show bottom sheet")
            }
            AnimatedVisibility(sheetState.isVisible) {
                SearchBottomSheet(
                    sheetState = sheetState,
                    items = listOf("Ali", "Ahmed", "Hassan", "mohmed", "sabry", "israa", "alaa"),
                    isSelected = selectedItem,
                    onDismiss = {
                        scope.launch {
                            sheetState.hide()
                        }
                    },
                    onSelectItem = { item ->
                        selectedItem = selectedItem + (item to true)
                    },
                )
            }
        }
    }
}

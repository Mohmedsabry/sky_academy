package com.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.centery.ui.R
import com.core.ui.theme.newTypography

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    text: String,
    showFilterIcon: Boolean = true,
    onSearchDone: () -> Unit,
    onClickFilter: () -> Unit = {},
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Filter,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearchDone() }),
        placeholder = {
            Text(
                text = stringResource(R.string.search_by_student_name_or_id),
                color = MaterialTheme.colorScheme.tertiary,
                style = newTypography.bodyMedium
            )
        },
        trailingIcon = {
            if (showFilterIcon) {
                Icon(
                    painter = painterResource(R.drawable.filter),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.clickable(onClick = onClickFilter)
                )
            }
        },
        shape = RoundedCornerShape(10.dp)
    )
}
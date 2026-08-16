package com.core.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.ui.theme.CenteryTheme
import ir.kaaveh.sdpcompose.ssp

@Composable
fun EmailInput(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
    email: String,
    onNext: KeyboardActionScope.() -> Unit,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = email,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                modifier= Modifier.fillMaxWidth(),
                text = generateHint(),
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 11.ssp,
                    textDirection = TextDirection.Rtl
                ),
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary
            )
        },
        keyboardActions = KeyboardActions(onNext = onNext),
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        ),
        shape = RoundedCornerShape(15.dp)
    )
}

fun generateHint(howMayNumbers: Int = 9): String {
    var res: String = (1..howMayNumbers).map {
        'x'
    }.joinToString("", prefix = "01")
    res += " or example@gmail.com"
    return res
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun EmailPreview() {
    CenteryTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            EmailInput(
                email = "",
                onNext = {},
            ) { }
        }
    }
}
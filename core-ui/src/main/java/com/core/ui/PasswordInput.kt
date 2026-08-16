package com.core.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.centery.ui.R
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography

@Composable
fun PasswordInput(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
    password: String,
    onDone: KeyboardActionScope.() -> Unit,
    onValueChange: (String) -> Unit
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = generatePasswordHint(),
                color = MaterialTheme.colorScheme.tertiary,
                style = newTypography.bodyMedium,
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable { showPassword = showPassword.not() },
                painter = if (!showPassword) painterResource(R.drawable.eye_close) else painterResource(
                    R.drawable.eye
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary
            )
        },
        keyboardActions = KeyboardActions(onDone = onDone),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        ),
        shape = RoundedCornerShape(15.dp),
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}

fun generatePasswordHint(howMayNumbers: Int = 8): String {
    val res: String = (1..howMayNumbers).map {
        '•'
    }.joinToString("")
    return res
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PasswordPreview() {
    CenteryTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            PasswordInput(
                password = "acaa",
                onDone = {},
            ) { }
        }
    }
}
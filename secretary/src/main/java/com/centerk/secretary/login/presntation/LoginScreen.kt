package com.centerk.secretary.login.presntation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.centerk.secretary.R
import com.centerk.secretary.navigation.AuthRoutes
import com.core.ui.BackGround
import com.core.ui.EmailInput
import com.core.ui.PasswordInput
import com.core.ui.TripleLoading
import com.core.ui.rememberImeState
import com.core.ui.theme.CenteryTheme
import com.core.ui.theme.newTypography
import ir.kaaveh.sdpcompose.sdp

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginEvents) -> Unit
) {
    val isKeyboardOpen by rememberImeState()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember {
        FocusRequester()
    }
    val scroll = rememberScrollState()
    LaunchedEffect(isKeyboardOpen) {
        if (isKeyboardOpen) {
            scroll.scrollTo(scroll.maxValue)
        }
    }
    Crossfade(
        state.isLoading,
        modifier = Modifier.fillMaxSize()
    ) { isLoading ->
        when (isLoading) {
            true -> {
                TripleLoading()
            }

            false -> {
                BackGround {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(scroll),
                        verticalArrangement = Arrangement.spacedBy(10.sdp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!isKeyboardOpen) {
                            Spacer(Modifier.weight(1f))
                        }
                        Image(
                            painter = painterResource(R.drawable.educational_academy),
                            contentDescription = stringResource(R.string.app_name),
                            modifier = Modifier
                                .size(100.sdp)
                                .clip(RoundedCornerShape(20.dp))
                        )
                        Spacer(Modifier.height(5.sdp))
                        Text(
                            text = stringResource(R.string.app_name),
                            style = newTypography.titleLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = stringResource(com.centery.ui.R.string.educational_academy),
                            style = newTypography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            stringResource(R.string.login_for_secretary_and_management),
                            style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Text(
                            stringResource(R.string.phone_number_or_email),
                            style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        EmailInput(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            email = state.emailOrPhone,
                            onNext = {
                                focusManager.moveFocus(
                                    FocusDirection.Down
                                )
                            },
                            onValueChange = {
                                onAction(LoginEvents.OnTypingEmail(it))
                            }
                        )
                        Text(
                            stringResource(R.string.password),
                            style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        PasswordInput(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            password = state.password,
                            onDone = { focusManager.clearFocus() },
                            onValueChange = {
                                onAction(LoginEvents.OnTypingPassword(it))
                            }
                        )
                        Spacer(Modifier.height(5.sdp))
                        Text(
                            stringResource(R.string.forget_your_password),
                            style = newTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable {
                                    onAction(
                                        LoginUiEvents.OnNavigation(
                                            AuthRoutes.ForgetYourPassword
                                        )
                                    )
                                },
                            textDecoration = TextDecoration.Underline
                        )
                        if (!isKeyboardOpen)
                            Spacer(Modifier.weight(1f))
                        OutlinedButton(
                            onClick = {
                                onAction(LoginEvents.OnLogin)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.sdp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.background
                            ),
                            border = ButtonDefaults.outlinedButtonBorder(false)
                        ) {
                            Text(
                                stringResource(R.string.login),
                                style = newTypography.bodyLarge
                            )
                        }
                        if (isKeyboardOpen) {
                            Spacer(Modifier.weight(1f))
                            Spacer(Modifier.height(32.sdp.times(5)))
                        }
                        Spacer(Modifier.height(5.sdp))
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Preview(locale = "ar")
@Composable
private fun LoginPreview() {
    CenteryTheme() {
        LoginScreen(LoginState()) { }
    }
}
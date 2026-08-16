package com.core.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.centery.ui.R
import com.core.ui.theme.CenteryTheme

@Composable
fun TripleLoading(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animationDuration = 800 // Total duration for one complete sequence
    val dotsCount = 3

    // Create separate alpha animations for each dot with staggered delays
    val dotAlphas = List(dotsCount) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = animationDuration
                    0f at 0 // Start invisible
                    0f at (animationDuration / dotsCount * index) // Delay before appearing
                    1f at (animationDuration / dotsCount * (index + 1)) // Fade in
                    1f at animationDuration // Stay visible until reset
                },
                repeatMode = RepeatMode.Restart
            ),
            label = "dot_alpha_$index"
        )
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground.copy(.7f))
    ) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo with Icon instead of Text
                Image(
                    painter = painterResource(id = R.drawable.educational_academy),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth(.40f)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Fit,
                )

                // Space between logo and progress dots
                Spacer(modifier = Modifier.height(10.dp))

                // Dot Progress Bar
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .alpha(dotAlphas[index].value)
                                .background(
                                    color = MaterialTheme.colorScheme.primary, shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TriplePrev() {
    CenteryTheme {
        TripleLoading()
    }
}
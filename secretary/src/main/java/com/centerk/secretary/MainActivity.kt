package com.centerk.secretary

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.centerk.secretary.common.presentation.ConfigurationManager
import com.centerk.secretary.navigation.NavigationController
import com.centerk.secretary.util.changeLanguage
import com.core.ui.theme.CenteryTheme
import com.core.ui.util.UiMode
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val configurationManager: ConfigurationManager by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                false
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiMode by configurationManager.uiMode.collectAsStateWithLifecycle()
            val language by configurationManager.language.collectAsStateWithLifecycle()
            val layoutDirection = if (language == "ar") {
                LayoutDirection.Rtl
            } else {
                LayoutDirection.Ltr
            }
            val locale = LocalLocale.current
            CompositionLocalProvider(
                LocalLayoutDirection provides layoutDirection
            ) {
                CenteryTheme(
                    darkTheme = uiMode == UiMode.Dark.mode,
                    selectedLang = language
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        LaunchedEffect(language) {
                            changeLanguage(locale, language)
                        }
                        NavigationController(
                            rememberNavController(),
                            configurationManager,
                            innerPadding
                        )
                    }
                }
            }
        }
    }
}
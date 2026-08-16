package com.centerk.secretary.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLocale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.centerk.secretary.splash.presentation.SplashEvents
import com.centerk.secretary.splash.presentation.SplashScreen
import com.centerk.secretary.splash.presentation.SplashViewModel
import com.centerk.secretary.util.changeLanguage
import ir.kaaveh.sdpcompose.ssp
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationController(
    navHostController: NavHostController,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    val locale = LocalLocale.current
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navHostController,
        startDestination = AuthGraph
    ) {
        navigation<AuthGraph>(
            startDestination = AuthRoutes.SplashDest,
        ) {
            composable<AuthRoutes.SplashDest> {
                val splashViewModel = koinViewModel<SplashViewModel>()
                val canMove by splashViewModel.canMove.collectAsStateWithLifecycle()
                val state by splashViewModel.state.collectAsStateWithLifecycle()
                LaunchedEffect(state.language) {
                    changeLanguage(
                        locale = locale,
                        language = state.language,
                        onFinish = { splashViewModel.onEvent(SplashEvents.SaveLanguage(it)) }
                    )
                }
                LaunchedEffect(canMove) {
                    if (canMove) {
                        if (state.isLoggedIn) {
                            navHostController.navigate(HomeRoutes.Home) {
                                launchSingleTop = true
                                popUpTo(AuthRoutes.SplashDest) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navHostController.navigate(AuthRoutes.LoginDest) {
                                launchSingleTop = true
                                popUpTo(AuthRoutes.SplashDest) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
                SplashScreen()
            }
            composable<AuthRoutes.LoginDest> {
                Box(Modifier.fillMaxSize()) {
                    Text("login", fontSize = 18.ssp)
                }
            }
        }
        navigation<HomeGraph>(
            startDestination = HomeRoutes.Home
        ) {
            composable<HomeRoutes.Home> {
                Box(Modifier.fillMaxSize()) {
                    Text("Home", fontSize = 18.ssp)
                }
            }
        }
    }
}
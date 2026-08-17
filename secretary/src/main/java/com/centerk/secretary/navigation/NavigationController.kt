package com.centerk.secretary.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
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
import com.centerk.secretary.common.presentation.ConfigurationManager
import com.centerk.secretary.home.presentation.HomeScreen
import com.centerk.secretary.home.presentation.HomeUiEvents
import com.centerk.secretary.home.presentation.HomeViewModel
import com.centerk.secretary.login.presntation.LoginScreen
import com.centerk.secretary.login.presntation.LoginUiEvents
import com.centerk.secretary.login.presntation.LoginViewModel
import com.centerk.secretary.splash.presentation.SplashScreen
import com.centerk.secretary.splash.presentation.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationController(
    navHostController: NavHostController,
    configurationManager: ConfigurationManager,
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
                val vm = koinViewModel<LoginViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                val events = vm.uiEvent
                LaunchedEffect(Unit) {
                    events.collectLatest { event ->
                        when (event) {
                            is LoginUiEvents.OnNavigation -> {
                                when (event.dest) {
                                    AuthRoutes.ForgetYourPassword -> {
                                        Toast.makeText(context, "coming soon", Toast.LENGTH_LONG)
                                            .show()
                                    }

                                    HomeRoutes.Home -> {
                                        navHostController.navigate(HomeRoutes.Home) {
                                            launchSingleTop = true
                                        }
                                    }

                                    else -> {}
                                }
                            }
                        }
                    }
                }
                LoginScreen(state = state, onAction = vm::onEvent)
            }
        }
        navigation<HomeGraph>(
            startDestination = HomeRoutes.Home
        ) {
            composable<HomeRoutes.Home> {
                val homeViewModel = koinViewModel<HomeViewModel>()
                val state by homeViewModel.state.collectAsStateWithLifecycle()
                val uiEvent = homeViewModel.uiEvent
                LaunchedEffect(Unit) {
                    uiEvent.collectLatest { event ->
                        when (event) {
                            is HomeUiEvents.Navigation -> {

                            }
                        }
                    }
                }
                HomeScreen(
                    state = state,
                    configurationManager = configurationManager,
                    onAction = homeViewModel::onEvent
                )
            }
        }
    }
}
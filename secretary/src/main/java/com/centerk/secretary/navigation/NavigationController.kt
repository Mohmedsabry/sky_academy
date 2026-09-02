package com.centerk.secretary.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLocale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.centerk.secretary.attendance.presentation.AttendanceScreen
import com.centerk.secretary.attendance.presentation.AttendanceUiEvents
import com.centerk.secretary.attendance.presentation.AttendanceViewModel
import com.centerk.secretary.common.presentation.ConfigurationManager
import com.centerk.secretary.confirm_attendance.presentation.ConfirmAttendanceScreen
import com.centerk.secretary.confirm_attendance.presentation.ConfirmAttendanceUiEvent
import com.centerk.secretary.confirm_attendance.presentation.ConfirmAttendanceViewModel
import com.centerk.secretary.finance.presentation.FinanceScreen
import com.centerk.secretary.finance.presentation.FinanceUiEvents
import com.centerk.secretary.finance.presentation.FinanceViewModel
import com.centerk.secretary.group_details.presntation.GroupDetailsEvents
import com.centerk.secretary.group_details.presntation.GroupDetailsScreen
import com.centerk.secretary.group_details.presntation.GroupDetailsViewModel
import com.centerk.secretary.groups.presentation.GroupEvents
import com.centerk.secretary.groups.presentation.GroupScreen
import com.centerk.secretary.groups.presentation.GroupViewModel
import com.centerk.secretary.home.presentation.HomeScreen
import com.centerk.secretary.home.presentation.HomeUiEvents
import com.centerk.secretary.home.presentation.HomeViewModel
import com.centerk.secretary.login.presntation.LoginScreen
import com.centerk.secretary.login.presntation.LoginUiEvents
import com.centerk.secretary.login.presntation.LoginViewModel
import com.centerk.secretary.payment_details.presentation.PaymentDetailsScreen
import com.centerk.secretary.payment_details.presentation.PaymentDetailsUiEvents
import com.centerk.secretary.payment_details.presentation.PaymentDetailsViewModel
import com.centerk.secretary.qr_scanner.presentation.QrScreen
import com.centerk.secretary.qr_scanner.presentation.QrUiEvents
import com.centerk.secretary.qr_scanner.presentation.QrViewModel
import com.centerk.secretary.recieve_package.presentation.PayBillsScreen
import com.centerk.secretary.recieve_package.presentation.PayBillsUiEvents
import com.centerk.secretary.recieve_package.presentation.PayBillsViewModel
import com.centerk.secretary.splash.presentation.SplashScreen
import com.centerk.secretary.splash.presentation.SplashViewModel
import com.centerk.secretary.student.presentation.StudentEvents
import com.centerk.secretary.student.presentation.StudentScreen
import com.centerk.secretary.student.presentation.StudentUiEvents
import com.centerk.secretary.student.presentation.StudentViewModel
import com.centerk.secretary.util.GetAndWait
import com.core.ui.util.UiMode
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationController(
    navHostController: NavHostController,
    configurationManager: ConfigurationManager,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    val locale = LocalLocale.current
    val coroutineScope = rememberCoroutineScope()
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
                GetAndWait(events) { event ->
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
                GetAndWait(uiEvent) { event ->
                    when (event) {
                        is HomeUiEvents.Navigation -> {
                            when (event.des) {
                                HomeRoutes.Groups -> {
                                    navHostController.navigate(HomeRoutes.Groups) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Home> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Students -> {
                                    navHostController.navigate(HomeRoutes.Students) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Home> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Finance -> {
                                    navHostController.navigate(HomeRoutes.Finance) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Home> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.AttendanceScreen -> {
                                    navHostController.navigate(HomeRoutes.AttendanceScreen) {
                                        launchSingleTop = true
                                    }
                                }

                                HomeRoutes.ReceiveBills -> {
                                    navHostController.navigate(HomeRoutes.ReceiveBills) {
                                        launchSingleTop = true
                                    }
                                }

                                else -> {}
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
            composable<HomeRoutes.Students> {
                val uiMode by configurationManager.uiMode.collectAsStateWithLifecycle()
                val vm = koinViewModel<StudentViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                val uiEvents = vm.uiEvents
                GetAndWait(uiEvents) { event ->
                    when (event) {
                        is StudentUiEvents.OnNavigation -> {
                            when (event.navigationRoutes) {
                                HomeRoutes.Finance -> {
                                    navHostController.navigate(HomeRoutes.Finance) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Students> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Groups -> {
                                    navHostController.navigate(HomeRoutes.Groups) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Students> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Home -> {
                                    navHostController.navigate(HomeRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Students> {
                                            inclusive = true
                                        }
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                }
                LaunchedEffect(uiMode) {
                    vm.onEvent(StudentEvents.OnUiModeChange(if (uiMode == UiMode.Dark.mode) UiMode.Dark else UiMode.Night))
                }
                StudentScreen(state, vm::onEvent)
            }
            composable<HomeRoutes.Groups> {
                val vm = koinViewModel<GroupViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                val uiEvents = vm.uiEvents
                GetAndWait(uiEvents) { events ->
                    when (events) {
                        is GroupEvents.Navigate -> {
                            when (events.navigationRoutes) {
                                HomeRoutes.AddStudent -> {
//                                    navHostController.navigate(HomeRoutes.AddStudent) {
//                                        launchSingleTop = true
//                                        popUpTo<HomeRoutes.Groups> {
//                                            inclusive = true
//                                        }
//                                    }
                                }

                                HomeRoutes.Finance -> {
                                    navHostController.navigate(HomeRoutes.Finance) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Groups> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Home -> {
                                    navHostController.navigate(HomeRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Groups> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Students -> {
                                    navHostController.navigate(HomeRoutes.Students) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Groups> {
                                            inclusive = true
                                        }
                                    }
                                }

                                is HomeRoutes.GroupDetails -> {
                                    navHostController.navigate(HomeRoutes.GroupDetails(events.navigationRoutes.groupId)) {
                                        launchSingleTop = true
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                }
                GroupScreen(state, onAction = vm::onEvent)
            }
            composable<HomeRoutes.GroupDetails> {
                val vm = koinViewModel<GroupDetailsViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                val uiEvents = vm.uiEvents
                GetAndWait(uiEvents) { events ->
                    when (events) {
                        is GroupDetailsEvents.Navigate -> {
                            when (events.navigationRoutes) {
                                HomeRoutes.AddStudent -> {
//                                    navHostController.navigate(HomeRoutes.AddStudent) {
//                                        launchSingleTop = true
//                                        popUpTo<HomeRoutes.GroupDetails> {
//                                            inclusive = true
//                                        }
//                                    }
                                }

                                HomeRoutes.Finance -> {
                                    navHostController.navigate(HomeRoutes.Finance) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Groups> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Home -> {
                                    navHostController.navigate(HomeRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Groups> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Students -> {
                                    navHostController.navigate(HomeRoutes.Students) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Groups> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Groups -> {
                                    navHostController.navigate(HomeRoutes.Groups) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.GroupDetails> {
                                            inclusive = true
                                        }
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                }
                GroupDetailsScreen(state, onAction = vm::onEvent)
            }
            composable<HomeRoutes.Finance> {
                val vm = koinViewModel<FinanceViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                val uiEvents = vm.uiEvents
                GetAndWait(uiEvents) { events ->
                    when (events) {
                        is FinanceUiEvents.Navigate -> {
                            when (events.route) {
                                HomeRoutes.AddStudent -> {
//                                    navHostController.navigate(HomeRoutes.AddStudent) {
//                                        launchSingleTop = true
//                                        popUpTo<HomeRoutes.Finance> {
//                                            inclusive = true
//                                        }
//                                    }
                                }

                                HomeRoutes.Home -> {
                                    navHostController.navigate(HomeRoutes.Home) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Finance> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Students -> {
                                    navHostController.navigate(HomeRoutes.Students) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Finance> {
                                            inclusive = true
                                        }
                                    }
                                }

                                HomeRoutes.Groups -> {
                                    navHostController.navigate(HomeRoutes.Groups) {
                                        launchSingleTop = true
                                        popUpTo<HomeRoutes.Finance> {
                                            inclusive = true
                                        }
                                    }
                                }

                                else -> {}
                            }
                        }

                        else -> {}
                    }
                }
                FinanceScreen(state = state, onAction = vm::onEvent)
            }
            composable<HomeRoutes.AttendanceScreen> {
                val snackBar = remember {
                    SnackbarHostState()
                }
                val attendanceVM = koinViewModel<AttendanceViewModel>()
                val state by attendanceVM.state.collectAsStateWithLifecycle()
                val uiEvents = attendanceVM.events
                GetAndWait(uiEvents) { event ->
                    when (event) {
                        AttendanceUiEvents.NavigateUp -> {
                            navHostController.navigateUp()
                        }

                        is AttendanceUiEvents.ShowToast -> {
                            coroutineScope.launch {
                                snackBar.showSnackbar(event.massage)
                            }
                        }

                        AttendanceUiEvents.NavigateToQrScan -> {
                            navHostController.navigate(HomeRoutes.QrScreen("123")) {
                                launchSingleTop = true
                            }
                        }

                        AttendanceUiEvents.NavigateToConfirmAttendance -> {
                            navHostController.navigate(
                                HomeRoutes.ConfirmAttendance(
                                    studentId = state.selectedStudentId,
                                    groupId = state.selectedGroup
                                )
                            ) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
                AttendanceScreen(
                    state = state,
                    snackbarHostState = snackBar,
                    onAction = attendanceVM::onEvent
                )
            }
            composable<HomeRoutes.QrScreen> {
                val qrViewModel = koinViewModel<QrViewModel>()
                val state by qrViewModel.state.collectAsStateWithLifecycle()
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                val uiEvents = qrViewModel.uiEvents
                GetAndWait(uiEvents) { events ->
                    when (events) {
                        QrUiEvents.NavigateToMarkAttendance -> {
                            navHostController.navigate(
                                HomeRoutes.ConfirmAttendance(
                                    studentId = state.studentId,
                                    groupId = state.group.groupId
                                )
                            ) {
                                launchSingleTop = true
                            }
                        }

                        QrUiEvents.NavigateUp -> {
                            navHostController.navigateUp()
                        }

                        is QrUiEvents.Toast -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(events.massage)
                            }
                        }
                    }
                }
                QrScreen(
                    state = state,
                    snackbarHostState = snackbarHostState,
                    onAction = qrViewModel::onEvent
                )
            }
            composable<HomeRoutes.ConfirmAttendance> {
                val vm = koinViewModel<ConfirmAttendanceViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                val uiEvent = vm.channel
                GetAndWait(uiEvent) { events ->
                    when (events) {
                        ConfirmAttendanceUiEvent.NavigateToHome -> {
                            navHostController.navigate(HomeRoutes.Home) {
                                launchSingleTop = true
                                popUpTo<HomeRoutes.AttendanceScreen> {
                                    inclusive = true
                                }
                            }
                        }

                        ConfirmAttendanceUiEvent.NavigateUp -> {
                            navHostController.navigateUp()
                        }
                    }
                }
                ConfirmAttendanceScreen(state, vm::onEvent)
            }
            composable<HomeRoutes.ReceiveBills> {
                val vm = koinViewModel<PayBillsViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                val events = vm.uiEvents
                GetAndWait(events) { event ->
                    when (event) {
                        is PayBillsUiEvents.NavigateToDetails -> {
                            navHostController.navigate(
                                HomeRoutes.PaymentDetails(
                                    studentId = event.studentId,
                                    amountShouldPaid = state.billsDetails[event.studentId] ?: 0L
                                )
                            ) {
                                launchSingleTop = true
                            }
                        }

                        PayBillsUiEvents.NavigateUp -> {
                            navHostController.navigateUp()
                        }
                    }
                }
                PayBillsScreen(state, vm::onEvent)
            }
            composable<HomeRoutes.PaymentDetails> {
                val vm = koinViewModel<PaymentDetailsViewModel>()
                val state by vm.state.collectAsStateWithLifecycle()
                GetAndWait(vm.uiEvent) { event ->
                    when (event) {
                        is PaymentDetailsUiEvents.Navigate -> {
                            navHostController.navigate(event.navigationRoutes) {
                                launchSingleTop = true
                                popUpTo<HomeRoutes.ReceiveBills> {
                                    inclusive = true
                                }
                            }
                        }

                        PaymentDetailsUiEvents.NavigateUp -> {
                            navHostController.navigateUp()
                        }

                        is PaymentDetailsUiEvents.Toast -> {
                            coroutineScope.launch {
                                state.snackbarHostState.showSnackbar(event.massage)
                            }
                        }
                    }
                }
                PaymentDetailsScreen(state, vm::onEvent)
            }
        }
    }
}
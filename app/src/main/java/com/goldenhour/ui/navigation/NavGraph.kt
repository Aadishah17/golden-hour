package com.goldenhour.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.goldenhour.ui.screens.AlertingScreen
import com.goldenhour.ui.screens.GoodSamaritanScreen
import com.goldenhour.ui.screens.HospitalDashboardScreen
import com.goldenhour.ui.screens.LanguageSelectionScreen
import com.goldenhour.ui.screens.QuickTriageScreen
import com.goldenhour.ui.screens.SOSScreen

@Composable
fun GoldenHourNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Language.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(420)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(420)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(360)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(360)
            )
        }
    ) {
        composable(Screen.Language.route) {
            LanguageSelectionScreen(
                viewModel = hiltViewModel(),
                onContinue = { navController.navigate(Screen.GoodSamaritan.route) }
            )
        }
        composable(Screen.GoodSamaritan.route) {
            GoodSamaritanScreen(
                viewModel = hiltViewModel(),
                onContinue = { navController.navigate(Screen.Sos.route) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Sos.route) {
            SOSScreen(
                viewModel = hiltViewModel(),
                onSos = { navController.navigate(Screen.Triage.route) }
            )
        }
        composable(Screen.Triage.route) {
            QuickTriageScreen(
                viewModel = hiltViewModel(),
                onContinue = { navController.navigate(Screen.Alerting.route) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Alerting.route) {
            AlertingScreen(
                viewModel = hiltViewModel(),
                onFinished = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Alerting.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Dashboard.route) {
            HospitalDashboardScreen(
                viewModel = hiltViewModel(),
                onRestart = {
                    navController.navigate(Screen.Language.route) {
                        popUpTo(Screen.Language.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

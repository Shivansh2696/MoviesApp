package com.compose.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.moviesapp.ui.screens.DashboardScreen
import com.compose.moviesapp.ui.screens.LoginScreen
import com.compose.moviesapp.ui.screens.SignUpScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN
    ) {
        composable(NavRoutes.LOGIN) {
            LoginScreen(
                onNavigateToSignUp = { navController.navigate(NavRoutes.SIGNUP) },
                onLoginSuccess = {
                    navController.navigate(NavRoutes.DASHBOARD){
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.SIGNUP){
            SignUpScreen(
                onBackToLogin = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.DASHBOARD){
            DashboardScreen()
        }
    }
}
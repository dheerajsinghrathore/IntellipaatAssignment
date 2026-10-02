package com.android.intellipaat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.intellipaat.presentation.dashboard.DashboardRoute
import com.android.intellipaat.presentation.dashboard.DashboardViewModel
import com.android.intellipaat.presentation.course.CourseDetailsRoute
import com.android.intellipaat.presentation.login.LoginRoute

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Dashboard : Screen("dashboard/{userName}") {
        fun createRoute(userName: String): String = "dashboard/${userName.ifBlank { "Learner" }}"
    }
    data object CourseDetails : Screen("course/{courseId}") {
        fun createRoute(courseId: Int) = "course/$courseId"
    }
}

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        composable(Screen.Login.route) { _ ->
            LoginRoute(
                onLoginSuccess = { userName ->
                    navController.navigate(Screen.Dashboard.createRoute(userName)) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.Dashboard.route,
            arguments = listOf(
                navArgument("userName") {
                    type = NavType.StringType
                    defaultValue = "Learner"
                }
            )
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Learner"
            val dashboardViewModel: DashboardViewModel = viewModel()
            DashboardRoute(
                userName = userName,
                viewModel = dashboardViewModel,
                onCourseClick = { courseId ->
                    navController.navigate(Screen.CourseDetails.createRoute(courseId))
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Dashboard.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.CourseDetails.route,
            arguments = listOf(navArgument("courseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val dashboardEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Dashboard.route)
            }
            val dashboardViewModel: DashboardViewModel = viewModel(dashboardEntry)
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: return@composable
            CourseDetailsRoute(
                courseId = courseId,
                viewModel = dashboardViewModel,
                onBack = { navController.popBackStack() },
                onLessonCompleted = { lessonId ->
                    dashboardViewModel.markLessonCompleted(courseId, lessonId)
                }
            )
        }
    }
}

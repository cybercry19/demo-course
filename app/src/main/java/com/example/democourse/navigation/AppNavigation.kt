package com.example.democourse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.democourse.data.AppContainer
import com.example.democourse.screens.courses.CourseScreen
import com.example.democourse.screens.detail.CourseDetailsScreen
import com.example.democourse.screens.login.LoginScreen

@Composable
fun AppNavigation(appContainer: AppContainer){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {

        composable("login"){
            LoginScreen (
                onLoginSuccess = {
                    navController.navigate("courses")
                }
            )
        }

        composable("courses"){
            CourseScreen(appContainer.courseRepository, navCourseDetails = { it->
                navController.navigate("detail/${it.id}")
            })
        }

        composable("detail/{courseId}") { backStackEntry ->

            val courseId =
                backStackEntry.arguments
                    ?.getString("courseId")
                    ?.toIntOrNull()

            if (courseId != null) {
                CourseDetailsScreen(courseId,appContainer.courseRepository)
            }
        }

    }
}
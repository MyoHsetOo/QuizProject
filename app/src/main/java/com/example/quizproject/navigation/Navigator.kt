package com.example.quizproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizproject.userScreens.BookListScreen
import com.example.quizproject.userScreens.ChapterScreen
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.HomeScreen
import com.example.quizproject.userScreens.SplashScreen

@Composable
fun Nav() {

    val navController = rememberNavController()

    NavHost(navController,
        startDestination = "SplashScreen") {

        composable(route = "HomeScreen" ) {

            HomeScreen(navController)

        }

        composable(route = "CourseListScreen" ) {

            CourseListScreen(navController)

        }


        composable(route = "BookListScreen" ) {

            BookListScreen(navController)

        }

        composable(route = "ChapterScreen" ) {

            ChapterScreen(navController)

        }

        composable(route = "SplashScreen" ) {

            SplashScreen(navController)

        }




    }

}
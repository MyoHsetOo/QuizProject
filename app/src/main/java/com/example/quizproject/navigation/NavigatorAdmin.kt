package com.example.quizproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizproject.adminScreens.HomeAdminScreen
import com.example.quizproject.adminScreens.SplashScreenAdmin
import com.example.quizproject.userScreens.HomeScreen

@Composable
fun NavAdmin() {

    val navController = rememberNavController()


    NavHost(navController,
        startDestination = "SplashScreenAdmin") {


        composable(route = "SplashScreenAdmin" ) {

            SplashScreenAdmin(navController)

        }

        composable(route = "HomeAdminScreen" ) {

            HomeAdminScreen(navController)

        }









    }

}

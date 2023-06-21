package com.example.quizproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizproject.adminScreens.AdminQuestionList
import com.example.quizproject.adminScreens.AdminQuestionScreen
import com.example.quizproject.adminScreens.BookListAdminScreen
import com.example.quizproject.adminScreens.ChapterAdminScreen
import com.example.quizproject.adminScreens.CourseContentAdminScreen
import com.example.quizproject.adminScreens.CourseListAdminScreen
import com.example.quizproject.adminScreens.HomeAdminScreen
import com.example.quizproject.adminScreens.QuestionEntryForm
import com.example.quizproject.login.LoginPage
import com.example.quizproject.login.RegisterPage
import com.example.quizproject.userScreens.AdminUserConnectedScreen
import com.example.quizproject.userScreens.BookListScreen
import com.example.quizproject.userScreens.ChapterScreen
import com.example.quizproject.userScreens.CourseContent
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.HomeScreen
import com.example.quizproject.userScreens.QuestionList
import com.example.quizproject.userScreens.QuestionScreen
import com.example.quizproject.userScreens.SplashScreen
//
//
//



@Composable
fun Nav() {

    val navController = rememberNavController()


    NavHost(navController,
        startDestination = "RegisterPage") {

        //UserScreen

        //HomeScreen
        /*composable(route = "HomeScreen" ) {

            HomeScreen(navController)

        }*/


        //CourseListScreen
        composable(route = "CourseListScreen" ) {

            CourseListScreen(navController)

        }


        //BookListScreen
        composable(route = "BookListScreen" ) {

            BookListScreen(navController)

        }

        //ChapterScreen
        composable(route = "ChapterScreen" ) {

            ChapterScreen(navController)

        }



        //QuestionList
        composable(route = "QuestionList" ) {

            QuestionList(navController)

        }

        composable(route = "QuestionScreen" ) {

            QuestionScreen(navController)

        }

        //CourseContent
        composable(route = "CourseContent" ) {

            CourseContent(navController)

        }


        //Admin Screen

        //HomeAdminScreen
        composable(route = "HomeAdminScreen") {
            HomeAdminScreen(navController)
        }

        //CourseListAdminScreen
        composable(route = "CourseListAdminScreen") {
            CourseListAdminScreen(navController)
        }

        //CourseContentAdminScreen
        composable(route = "CourseContentAdminScreen") {
            CourseContentAdminScreen(navController)
        }

        //BookListAdminScreen
        composable(route = "BookListAdminScreen") {
            BookListAdminScreen(navController)
        }
        //ChpterAdminScreen
        composable(route = "ChapterAdminScreen") {
            ChapterAdminScreen(navController)
        }

        composable(route = "AdminQuestionList") {
            AdminQuestionList(navController)
        }


        composable(route = "QuestionEntryForm") {
            QuestionEntryForm(navController)
        }


        composable(route = "AdminQuestionScreen") {
            AdminQuestionScreen(navController)
        }

        //login
        composable(route = "LoginPage") {
            LoginPage(navController)
        }

        //register
        composable(route = "RegisterPage") {
            RegisterPage(navController)
        }


    }

}
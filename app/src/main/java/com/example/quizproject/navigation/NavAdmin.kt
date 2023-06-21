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
import com.example.quizproject.userScreens.BookListScreen
import com.example.quizproject.userScreens.ChapterScreen
import com.example.quizproject.userScreens.CourseContent
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.QuestionList
import com.example.quizproject.userScreens.QuestionScreen

@Composable
fun NavAdmin() {

    val navController = rememberNavController()


    NavHost(navController,
        startDestination = "CourseListAdminScreen") {

        //Admin Screen


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



    }

}
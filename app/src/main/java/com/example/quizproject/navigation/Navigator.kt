package com.example.quizproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizproject.adminScreens.AdminQuestionList
import com.example.quizproject.adminScreens.AdminQuestionScreen
import com.example.quizproject.adminScreens.BookListAdminScreen
import com.example.quizproject.adminScreens.ChapterAdminScreen
import com.example.quizproject.adminScreens.CourseContentAdminScreen
import com.example.quizproject.adminScreens.CourseListAdminScreen
//import com.example.quizproject.adminScreens.HomeAdminScreen
import com.example.quizproject.adminScreens.QuestionEntryForm
import com.example.quizproject.login.LoginPage
import com.example.quizproject.login.RegisterPage
import com.example.quizproject.userScreens.AdminUserConnectedScreen
import com.example.quizproject.userScreens.BookListScreen
import com.example.quizproject.userScreens.ChapterScreen
import com.example.quizproject.userScreens.CourseContent
import com.example.quizproject.userScreens.CourseListScreen
//import com.example.quizproject.userScreens.HomeScreen
import com.example.quizproject.userScreens.QuestionList
import com.example.quizproject.userScreens.QuestionScreen
import com.example.quizproject.userScreens.SplashScreen
//
//
//



@Composable
fun Nav( id : String ) {

    val navController = rememberNavController()


    NavHost(navController,
        startDestination = "CourseListScreen") {

        //UserScreen

        //HomeScreen
        /*composable(route = "HomeScreen" ) {

            HomeScreen(navController)

        }*/


        //CourseListScreen
        composable(route = "CourseListScreen" ) {

            CourseListScreen( navController, id = id)

        }


        //BookListScreen
        composable(route = "BookListScreen/{courseId}",
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType }
            )
        ) {backstackEntry ->
            val courseId = backstackEntry.arguments?.getString("courseId")
            BookListScreen( navController, courseId)
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
        composable(route = "CourseContent/{courseId}/{courseName}",
            arguments = listOf(
                navArgument( "courseId" ){ type = NavType.StringType },
                navArgument( "courseName" ){ type = NavType.StringType },

                )
        ) {backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            val courseName = backStackEntry.arguments?.getString("courseName")


            CourseContent(navController, courseId , courseName)
        }




    }

}
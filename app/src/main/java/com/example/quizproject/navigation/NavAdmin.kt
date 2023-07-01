package com.example.quizproject.navigation

import android.content.SharedPreferences
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
import com.example.quizproject.userScreens.BookListScreen
import com.example.quizproject.userScreens.ChapterScreen
import com.example.quizproject.userScreens.CourseContent
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.QuestionList
import com.example.quizproject.userScreens.QuestionScreen

@Composable
fun NavAdmin( id : String ) {

    val navController = rememberNavController()


    NavHost(navController,
        startDestination = "CourseListAdminScreen") {

        //Admin Screen


        //CourseListAdminScreen
        composable(route = "CourseListAdminScreen"
            ) {
          //  val id = backStackEntry.arguments?.getString("id")
            CourseListAdminScreen(navController,id=id)
        }

        //CourseContentAdminScreen
        composable(route = "CourseContentAdminScreen/{courseId}/{courseName}",
            arguments = listOf(
                navArgument( "courseId" ){ type = NavType.StringType },
                navArgument( "courseName" ){ type = NavType.StringType },

            )
            ) {backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            val courseName = backStackEntry.arguments?.getString("courseName")


            CourseContentAdminScreen(navController, courseId , courseName)
        }

        //BookListAdminScreen
        composable(route = "BookListAdminScreen/{courseId}",
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType }
            )
            ) {backstackEntry ->
            val courseId = backstackEntry.arguments?.getString("courseId")
            BookListAdminScreen( navController, courseId)
        }


        //ChpterAdminScreen
        composable(route = "ChapterAdminScreen/{bookId}/{bookName}",
            arguments = listOf(
                navArgument( "bookId" ){ type = NavType.StringType },
                navArgument( "bookName" ){ type = NavType.StringType },

                )
        ) {backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            val bookName = backStackEntry.arguments?.getString("bookName")
            ChapterAdminScreen(navController, bookId , bookName)
        }

        composable(route = "AdminQuestionList/{chapterId}",
            arguments = listOf(
                navArgument( "chapterId" ){ type = NavType.StringType },
            )
            ) { backStackEntry ->
            val chapterId = backStackEntry.arguments?.getString("chapterId")
            AdminQuestionList(navController, chapterId)
        }


        composable(route = "QuestionEntryForm/{chapterId}",
            arguments = listOf(
                navArgument( "chapterId" ){ type = NavType.StringType },
            )
            ) {backStackEntry ->

            val chapterId = backStackEntry.arguments?.getString("chapterId")
            QuestionEntryForm(navController,chapterId)
        }


        composable(route = "AdminQuestionScreen/{questionId}/{questionNo}/{index}"
              ,

            arguments = listOf(

                navArgument("questionId") { type = NavType.StringType },
                navArgument("questionNo") { type = NavType.StringType },
                navArgument("index") { type = androidx.navigation.NavType.IntType }


            )

            ) {backStackEntry ->

            val questionId = backStackEntry.arguments?.getString("questionId")
            val questionNo = backStackEntry.arguments?.getString("questionNo")
            val index = backStackEntry.arguments?.getInt("index")

            AdminQuestionScreen(navController,questionId,questionNo,index)

        }



    }

}
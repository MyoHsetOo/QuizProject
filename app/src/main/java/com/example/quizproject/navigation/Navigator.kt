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
import com.example.quizproject.quizz.QuickChooseScreen
import com.example.quizproject.quizz.RandomQuestionScreen
import com.example.quizproject.userScreens.AdminUserConnectedScreen
import com.example.quizproject.userScreens.BookListScreen
import com.example.quizproject.userScreens.ChapterScreen
import com.example.quizproject.userScreens.CourseContent
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.FavoriteScreen
//import com.example.quizproject.userScreens.HomeScreen
import com.example.quizproject.userScreens.QuestionList
import com.example.quizproject.userScreens.QuestionScreen
import com.example.quizproject.userScreens.QuizScreen
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
        composable(route = "ChapterScreen/{bookId}/{bookName}" ,
            arguments = listOf(
                navArgument( "bookId" ){ type = NavType.StringType },
                navArgument( "bookName" ){ type = NavType.StringType },

                )
            ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            val bookName = backStackEntry.arguments?.getString("bookName")

            ChapterScreen(navController , bookId , bookName )

        }



        //QuestionList
        composable(route = "QuestionList/{chapterId}/{chapterName}" ,
            arguments = listOf(
                navArgument( "chapterId" ){ type = NavType.StringType },
                navArgument( "chapterName" ){ type = NavType.StringType },

                )

            ) { backStackEntry ->
            val chapterId = backStackEntry.arguments?.getString("chapterId")
            val chapterName = backStackEntry.arguments?.getString("chapterName")

            QuestionList(navController , chapterId , chapterName )

        }

        composable(route = "QuestionScreen/{questionId}/{questionNo}/{index}",
            arguments = listOf(
                navArgument( "questionId" ){ type = NavType.StringType },
                navArgument( "questionNo" ){ type = NavType.StringType },
                navArgument( "index" ){ type = NavType.IntType },

            )
            ) { backStackEntry ->
            val questionId = backStackEntry.arguments?.getString("questionId")
            val questionNo = backStackEntry.arguments?.getString("questionNo")
            val index = backStackEntry.arguments?.getInt("index")

            QuestionScreen(navController , questionId , questionNo,index )

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

        composable(route = "FavoriteScreen" ) {

           FavoriteScreen( navController)

        }

        composable(route = "QuizScreen" ) {

            QuizScreen( navController)

        }


        composable(route = "QuickChooseScreen/{courseId}",
            arguments = listOf(
                navArgument( "courseId" ){ type = NavType.StringType },

                )
            ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")

            QuickChooseScreen( navController,courseId)

        }

        composable(route = "RandomQuestionScreen/{count}",
            arguments = listOf(
                navArgument( "count" ){ type = NavType.IntType },

                )

            ) {backStackEntry ->
            val count = backStackEntry.arguments?.getInt("count")

                       RandomQuestionScreen( navController,count)

        }



    }

}
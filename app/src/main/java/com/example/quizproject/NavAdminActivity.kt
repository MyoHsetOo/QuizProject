package com.example.quizproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quizproject.navigation.NavAdmin
//import com.example.quizproject.navigation.NavUser
import com.example.quizproject.ui.theme.QuizProjectThem


class NavAdminActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizProjectThem {
                // A surface container using the 'background' color from the theme

                NavAdmin()
            }
        }
    }
}
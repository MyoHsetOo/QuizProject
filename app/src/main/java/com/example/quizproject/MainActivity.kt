package com.example.quizproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizproject.adminScreens.AdminQuestionList
import com.example.quizproject.adminScreens.AdminQuestionScreen
import com.example.quizproject.adminScreens.BookListAdminScreen
import com.example.quizproject.adminScreens.CourseListAdminScreen
import com.example.quizproject.adminScreens.QuestionEntryForm
import com.example.quizproject.navigation.Nav
import com.example.quizproject.ui.theme.QuizProjectThem
import com.example.quizproject.userScreens.AdminUserConnectedScreen
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.FavoriteScreen
import com.example.quizproject.userScreens.HomeScreen

//
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizProjectThem {
                // A surface container using the 'background' color from the theme
              //  HomeScreen()
                Nav()
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizProjectThem {

    }
}
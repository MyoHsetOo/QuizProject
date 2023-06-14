package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import android.app.Notification.Style
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionList(navController: NavController) {
    val list = listOf(
        Question("Question", 1),
        Question("Question", 2),
        Question("Question", 3)
    )

    Surface(
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column (

        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)

            ) {
                Row {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back",
                            tint = Color.Black)
                    }
                    Box(modifier = Modifier.padding(10.dp)) {
                        Text(text = "Hardware",
                            style = TextStyle(color = Color.Black,
                                fontSize = 18.sp)
                        )
                    }
                }
            }
           // Spacer(modifier = Modifier.height(20.dp))
            Column(modifier=Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn {
                    items(list) { item ->
                        Button(
                            onClick = { navController.navigate("QuestionScreen")},
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                        ) {
                            Text(text = item.question +" - "+ item.number
                                , style = TextStyle(
                                    color = Color.Black
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}


data class Question(

    val question : String,
    val number : Int
){


}
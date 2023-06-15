package com.example.quizproject.adminScreens

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
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizproject.userScreens.Question
//
@Composable
fun AdminQuestionList(navController: NavController){

    val itemList = remember { mutableStateListOf<String>() }
    val textFieldValue = remember { mutableStateOf("") }
    val showAlert = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val list = listOf(
        Question("Question", 1),
        Question("Question", 1),
        Question("Question", 1),


    )

    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary) {
        Column {

                Row (
                    modifier = Modifier.height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                        Text(
                            text = "Hardware",
                            style = TextStyle(
                               color =  MaterialTheme.colorScheme.onPrimary,
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.padding( start = 5.dp )

                        )

                }

            // Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( top = 20.dp)
                    .background(MaterialTheme.colorScheme.secondary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                  Button(
                    onClick = {
                              navController.navigate("QuestionEntryForm")
                    },
                    modifier = Modifier.fillMaxWidth(0.85f),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                    ) {

                    Icon(
                        Icons.Default.Add, contentDescription = "Add",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Enter Question", style = TextStyle(
                           color =  MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyColumn {
                    items(list) { item ->
                        Button(
                            onClick = {
                                 navController.navigate("AdminQuestionScreen")
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                            ) {
                            Text(
                                text = item.question + " - " + item.number, style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
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
)
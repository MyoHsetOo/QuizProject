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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Row {
                    Box(modifier = Modifier.padding(10.dp)
                        .clickable {
                            navController.popBackStack()
                        }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint = Color.Black
                        )
                    }
                    Box(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Hardware",
                            style = TextStyle(color = Color.Black),
                            fontSize = 18.sp
                        )
                    }
                }
            }
            // Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (showAlert.value) {
                    AlertDialog(
                        onDismissRequest = { showAlert.value = false },
                        title = { Text(text = "Enter Book name") },
                        text = {

                            TextField(
                                value = textFieldValue.value,
                                onValueChange = { textFieldValue.value = it }
                            )
                        },
                        confirmButton = {

                            Button(onClick = {
                                showAlert.value = false

                                if (textFieldValue.value.isNotEmpty()) {
                                    itemList.add(textFieldValue.value)
                                }

                                textFieldValue.value = ""

                            }) {
                                Text(text = "OK")
                            }
                        }
                    )
                }

                Button(
                    onClick = {
                              navController.navigate("QuestionEntryForm")
                    },
                    modifier = Modifier,
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                    ) {

                    Icon(
                        Icons.Default.Add, contentDescription = "Add",
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = "Enter Question", style = TextStyle(
                            color = Color.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier=Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

            LazyColumn {
                items(list) { item ->
                    Button(
                        onClick = {
                                  navController.navigate("AdminQuestionScreen")
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(),
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                        ) {
                        Text(
                            text = item.question + " - " + item.number, style = TextStyle(
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
)
package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R
import com.example.quizproject.userScreens.card

//
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListAdminScreen( navController: NavController ) {

    val showAlert = remember { mutableStateOf(false) }
    val textFieldValue = remember { mutableStateOf("") }

    val itemList = remember { mutableStateListOf<String>() }


//////
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                ),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
            ) {

                Row(modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Text(
                        text = "Course List",
                        style = androidx.compose.ui.text.TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }


            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Card(modifier= Modifier
                .fillMaxWidth(0.8f)
                .height(130.dp)
                .padding(top = 10.dp)
                .clickable {
                    navController.navigate("CourseContentAdminScreen")
                },
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                elevation = CardDefaults.cardElevation(5.dp)) {

                Row(
                    modifier = Modifier.fillMaxSize()
                        .fillMaxWidth()
                        .clickable { showAlert.value = true },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.Add, contentDescription = "back",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )


                        }
                    }

                    Column(horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)) {
                        Text(
                            text = "Add Course",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(30.dp))
            for (item in itemList){

                AdminFinalCard(text = item, navController = navController)

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
        if (showAlert.value) {
            AlertDialog(
                onDismissRequest = { showAlert.value = false },
                title = {
                    Text(
                        text = "Enter Course Name",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        )
                    )
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                icon = {
                    Icon(imageVector = Icons.Default.MenuBook,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(30.dp)
                    )
                },
                text = {

                    OutlinedTextField(
                        value = textFieldValue.value,
                        onValueChange = { textFieldValue.value = it },
                        modifier = Modifier
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(10.dp)
                            ),

                        colors = OutlinedTextFieldDefaults.colors(
                            cursorColor = Color.Black,
                            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                            focusedContainerColor = MaterialTheme.colorScheme.secondary,
                            // focusedBorderColor = Color(0xFF4B6DA3),
                            //unfocusedBorderColor = Color(0xFF4B6DA3),
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary

                        ),
                        shape = RoundedCornerShape(10.dp)

                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showAlert.value = false
                            itemList.add(textFieldValue.value)
                            textFieldValue.value = ""
                                  },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Text(text = "Submit", color = MaterialTheme.colorScheme.secondary)
                    }
                }
            )
        }

    }
}

@Composable
fun cardAdmin(text:String , navController: NavController){
    Column(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth(0.75f)


        //   .padding(60.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 10.dp, bottomEnd = 0.dp, bottomStart = 10.dp)
        ) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape( topStart = 0.dp, topEnd = 0.dp, bottomEnd = 0.dp, bottomStart = 0.dp)
            ) {

            }
            Column(verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("CourseContentAdminScreen") },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = text,
                        style = TextStyle(
                            color=MaterialTheme.colorScheme.onPrimary
                        )
                    )


                        IconButton(onClick = {
                            navController.navigate("CourseContentAdminScreen")
                        }) {
                            Icon(imageVector = Icons.Outlined.ArrowCircleRight, contentDescription = "right")

                        }

                }

            }
        }
    }
}


@Composable
fun AdminFinalCard(text:String , navController: NavController){
    Spacer(modifier = Modifier.height(30.dp))
    Card(modifier= Modifier
        .fillMaxWidth(0.8f)
        .height(130.dp)
        .padding(top = 10.dp)
        .clickable {
            navController.navigate("CourseContentAdminScreen")
        },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(5.dp)) {

        Row(
            modifier = Modifier.fillMaxSize(),
        ) {

            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "1",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Course",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Column(horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}



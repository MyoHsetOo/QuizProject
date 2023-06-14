package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListAdminScreen( navController: NavController ) {
//////
    val itemList = remember { mutableStateListOf<String>() }
    val addCourseTextField = remember { mutableStateOf("") }
    var isAddingCourse by remember { mutableStateOf(false) }

    val context = LocalContext.current

    var count = remember {
        mutableStateOf(0)
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.course))

    var isPlaying by remember { mutableStateOf(true) }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )

    LaunchedEffect(key1 = progress){
        if( progress == 0f){
            isPlaying = true
        }
        if ( progress == 1f){
            isPlaying = false
        }
    }

    Scaffold (

        containerColor = MaterialTheme.colorScheme.primary,

        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .clickable {
                                //navController.popBackStack()
                            }

                    ){
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack", tint = Color.Black)
                        }

                    }


                    Box (
                        modifier = Modifier.padding(start = 8.dp , end = 8.dp)
                    ) {
                        Text(text = "ITPEC" , fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black )
                    }
                }
            }
        },

        content =  {

            Column () {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.30f)
                        ,
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End

                ) {

                    Box(
                    ) {

                        LottieAnimation(
                            modifier = Modifier
                                .size(250.dp),
                            iterations = 100,
                            composition = composition
                        )
                    }
                }

                    Text(text = "Course Lists" , style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,

                    ), modifier = Modifier.padding( start = 15.dp, bottom = 20.dp) )

                if ( isAddingCourse ) {

                    AlertDialog(
                        onDismissRequest = { isAddingCourse = false },
                        title = { Text(text = "Enter course name", style = TextStyle(
                            color = Color.Black
                        )
                        ) },
                        containerColor = MaterialTheme.colorScheme.secondary,
                        icon = {
                            Icon(imageVector = Icons.Default.BorderColor, contentDescription = "Heart", tint = MaterialTheme.colorScheme.primary )
                        },
                        text = {

                            OutlinedTextField(
                                value = addCourseTextField.value,
                                onValueChange = { addCourseTextField.value = it },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedTextColor = Color.Black,
                                    focusedTextColor = Color.Black

                                )
                            )
                        },
                        confirmButton = {
                            Button(onClick = { isAddingCourse = false

                                /*if(textFieldValue.value.isNotEmpty()) {
                                    itemList.add(textFieldValue.value)
                                }*/

                                itemList.add (addCourseTextField.value)

                                addCourseTextField.value = ""

                            }) {
                                Text(text = "Submit")
                            }
                        }
                    )
                }

                Card ( modifier = Modifier
                    .fillMaxSize(),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                    ){

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(30.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {

                        for (item in itemList) {


                            Card(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .clickable {
                                               navController.navigate("CourseContentAdminScreen")
                                    },
                                elevation = CardDefaults.cardElevation(2.dp),
                                //shape = RoundedCornerShape( topStart = 20.dp, bottomEnd = 20.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight()
                                            .padding(top = 15.dp, bottom = 15.dp, start = 20.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Description,
                                            contentDescription = "Add",
                                            tint = Color.Black,
                                            modifier = Modifier.padding(end = 20.dp)
                                        )
                                        Text(
                                            text = "$item",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }

                        }

                        Card(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                                .height(80.dp)
                                .clickable { isAddingCourse = !isAddingCourse },

                            elevation = CardDefaults.cardElevation(2.dp),
                            //shape = RoundedCornerShape( topStart = 20.dp, bottomEnd = 20.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),

                                ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(top = 15.dp, bottom = 15.dp, start = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add",
                                        tint = Color.Black,
                                        modifier = Modifier.padding(end = 20.dp)
                                    )
                                    Text(
                                        text = " Add Course  ",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },

    )
}

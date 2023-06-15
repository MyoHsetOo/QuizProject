package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
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
//////
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeAdminScreen ( navController: NavController ) {
/////
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bulb))

    var isPlaying by remember { mutableStateOf(true) }

    var isAddingBatch by remember {
        mutableStateOf(false)
    }

    var addBatchTextField = remember {
        mutableStateOf("")
    }

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

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(rememberScrollState()),

        ){

        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp)
                ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,

        ){
            Box( ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){

                    IconButton(onClick = {
                        navController.popBackStack()
                    }){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = Color.Black)
                    }
                    Text(text = "Quiz", style = TextStyle(
                        fontSize = 16.sp,

                        color = MaterialTheme.colorScheme.onPrimary
                    ))
                }
            }
            Box {
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding( end = 0.dp )
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,

                    ){
                        Text(text = "Add Course" , style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        ), modifier = Modifier.padding( start = 10.dp, end = 0.dp))
                        Icon(imageVector = Icons.Default.Add, contentDescription = "add",
                            modifier = Modifier.padding(end = 0.dp, start = 5.dp))
                    }

                }
            }

        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){

            LottieAnimation(
                modifier = Modifier

                    .fillMaxHeight()
                    .height(200.dp),
                iterations = 100,
                composition = composition
            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        Column (
            modifier = Modifier
                .padding( top = 20.dp)
        ){

            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ){

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(

                                    MaterialTheme.colorScheme.primary,

                                    MaterialTheme.colorScheme.primary,

                                    ),
                                start = Offset.Zero, // Starting point of the gradient
                                end = Offset.Infinite, // Ending point of the gradient
                                tileMode = TileMode.Clamp // Tile mode for extending the gradient
                            )
                        )
                ){
                    Column {

                        Text(text = "Information Technology Professionals Examination Council ( ITPEC )", style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                            lineHeight = 25.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding( top = 30.dp, bottom = 20.dp, start = 20.dp, ))

                        Button(onClick = {
                            navController.navigate("CourseListAdminScreen")

                        },
                            modifier = Modifier.padding( top = 40.dp, bottom = 10.dp, start = 20.dp,),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(text = "View" , )
                        }

                    }

                }

            }

            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ){

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(

                                    MaterialTheme.colorScheme.primary,

                                    MaterialTheme.colorScheme.primary,

                                    ),
                                start = Offset.Zero, // Starting point of the gradient
                                end = Offset.Infinite, // Ending point of the gradient
                                tileMode = TileMode.Clamp // Tile mode for extending the gradient
                            )
                        )
                ){
                    Column {

                        Text(text = "Information Technology Professionals Examination Council ( ITPEC )", style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                            lineHeight = 25.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding( top = 30.dp, bottom = 20.dp, start = 20.dp, ))

                        Button(onClick = {
                            navController.navigate("CourseListScreen")

                        },
                            modifier = Modifier.padding( top = 40.dp, bottom = 10.dp, start = 20.dp,),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(text = "View" , )
                        }

                    }

                }

            }

        }

    }


}
package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCourseContent(){
    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondary,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.secondary),
                title = {
                    Row {
                        Box(modifier = Modifier.padding(10.dp)) {
                            Icon(
                                Icons.Default.ArrowBack, contentDescription = "back",
                                tint = Color.Black)
                        }
                        Box(modifier = Modifier.padding(10.dp)){
                            Text(text = "FE Class",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }
                })
        },
        content = {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.presentation)  )
            Column {
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.4f),
                    verticalArrangement = Arrangement.Bottom,
                    //  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        //  modifier = Modifier.fillMaxSize(),
                        iterations = 1000, composition = composition
                    )
                }
                Column(modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "Choose Your Category",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
                Column(
                    // modifier = Modifier.fillMaxHeight(1f),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {



                   // Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {
                            Card (modifier = Modifier
                                .size(150.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                                onClick = {}
                            ){

                                Box(modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ){ Text(text = "Teach",
                                    style = TextStyle(
                                        color = Color.Black ,
                                        fontSize = 16.sp)
                                )
                                }
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Card (modifier = Modifier
                                .size(150.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                            ){
                                Box(modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ){ Text(text = "Test",
                                    style = TextStyle(
                                        color = Color.Black  ,
                                        fontSize = 16.sp)
                                )
                                }
                            }
                        }
                    }


                }
            }
        }

    )
}
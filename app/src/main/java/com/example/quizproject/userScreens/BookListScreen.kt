@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookListScreen (navController: NavController) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.book))
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

    Scaffold(

        containerColor = MaterialTheme.colorScheme.secondary,

        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(56.dp),
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Box(
                        modifier = Modifier.padding(8.dp)
                            .clickable { navController.popBackStack()}

                    ){
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack", tint = Color.Black)
                    }
                }
            }
        },

        content = {

            Column() {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Box(
                    ) {
                        LottieAnimation(
                            modifier = Modifier
                                .size(350.dp),
                            iterations = 100,
                            composition = composition
                        )
                    }
                }

                Column(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                ) {

                    Text(text = "book List",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {

                        Card(
                            modifier = Modifier
                                .width(250.dp)
                                .height(150.dp)
                                .padding(start = 30.dp,end = 20.dp)
                                .clickable { navController.navigate("ChapterScreen") },
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                        ) {

                            Box(
                                modifier = Modifier.fillMaxSize().padding(30.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(text = "Information Technology - Vol.1" ,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )


                            }
                        }
                        Card(

                            modifier = Modifier
                                .width(250.dp)
                                .height(150.dp)
                                .padding(start = 20.dp,end = 20.dp)
                                .clickable { navController.navigate("ChapterScreen") }
                            ,
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)

                        ) {

                            Box(
                                modifier = Modifier.fillMaxSize().padding(30.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(text = "Information Technology - Vol.1" ,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                    )

                            }
                        }
                    }
                }
            }
        }
    )
}

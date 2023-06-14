package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CourseListScreen (navController: NavController) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.course))
    var isPlaying by remember { mutableStateOf(true) }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )
//
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

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,

            ){

                IconButton(onClick = { navController.popBackStack() }){
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack", tint = Color.Black)
                    }

                Text(text = "ITPEC" , style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                     fontSize = 18.sp,
                ), modifier = Modifier.padding( start = 5.dp ))

            }

    },

        content =  {

         Column () {


             Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End

            ) {
                 Box(
                ) {
                    LottieAnimation(
                        modifier = Modifier
                            .size(200.dp),
                        iterations = 100,
                        composition = composition
                    )
                 }
             }

            Box (
                modifier = Modifier.padding(start = 15.dp )
            ) {
                Text(text = "Course Lists" , fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black )
            }

             Spacer(modifier = Modifier.height(30.dp))

             Card ( modifier = Modifier
                .fillMaxSize(),
                shape = RoundedCornerShape(  topStart = 30.dp , topEnd = 30.dp ),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),

                 ){

                Column(
                    modifier = Modifier
                        .fillMaxSize()

                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                    ) {

                    Card(

                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clickable { navController.navigate("CourseContent") },

                        elevation = CardDefaults.cardElevation(2.dp),
                        border = BorderStroke(1.dp, Color(0xFF3A416D)),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(90.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Row ( modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(top = 20.dp, bottom = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Icon(imageVector = Icons.Default.Description, contentDescription = "Description", tint = Color.Black, modifier = Modifier.padding(end = 20.dp))

                                Text(text = "IT passport Exam (IP)  " , fontSize = 18.sp, fontWeight = FontWeight.SemiBold , color = Color.Black)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(60.dp))

                    Card(

                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clickable { navController.navigate("CourseContent") },

                        elevation = CardDefaults.cardElevation(2.dp),
                        border = BorderStroke(1.dp, Color(0xFF3A416D)),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            Row ( modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(top = 20.dp, bottom = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Icon(imageVector = Icons.Default.Description, contentDescription = "Description", tint = Color.Black, modifier = Modifier.padding(end = 20.dp))

                                Text(text = "Fundamental Engineering Exam (FE)  " , fontSize = 18.sp, fontWeight = FontWeight.SemiBold , color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    } )
}
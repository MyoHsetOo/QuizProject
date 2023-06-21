package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(){
    val list = listOf(
        Question("Question", 1),
        Question("Question", 2),
        Question("Question", 3)
    )
    Scaffold (
        containerColor = MaterialTheme.colorScheme.secondary,

        topBar = {
            Column(modifier = Modifier.height(56.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically


                ) {
                    Box(modifier = Modifier.padding(10.dp)) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint =MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Box(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Your Favorite List",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        },
        content = {
//
            Column(modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(50.dp))
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty1))

            if (list.isEmpty()) {
                Column {
                    Spacer(modifier = Modifier.height(40.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.8f),
                        verticalArrangement = Arrangement.Bottom,
                        //  horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnimation(
                            //  modifier = Modifier.fillMaxSize(),
                            iterations = 1000, composition = composition
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.secondary),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Your Favorite List is Empty",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn {
                        items(list) { item ->
                            Button(
                                onClick = { },
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .fillMaxHeight(),
                                shape = RoundedCornerShape(20.dp),
                                elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                                ) {
                                Text(
                                    text = item.question + " - " + item.number, style = TextStyle(
                                        color =MaterialTheme.colorScheme.onPrimary
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
    )
}
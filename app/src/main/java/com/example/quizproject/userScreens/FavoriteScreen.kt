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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    Scaffold (
        containerColor = MaterialTheme.colorScheme.secondary,

        topBar = {
            Row(modifier=Modifier.fillMaxWidth()
                .height(56.dp),
                verticalAlignment = Alignment.CenterVertically



            ) {
                Box(modifier = Modifier.padding(10.dp)) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = "back",
                        tint = Color.Black
                    )
                }
                Box(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Your Favorite List",
                        style = TextStyle(color = Color.Black,
                            fontWeight = FontWeight.Bold)

                    )
                }
            }
                 },
        content = {

            /*Column(horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center,
               modifier = Modifier.fillMaxWidth()) {
               Spacer(modifier = Modifier.height(50.dp))
               Button(
                   onClick = {},
                   modifier = Modifier
                       .fillMaxWidth(0.7f)
                   ,
                   shape = RoundedCornerShape(20.dp),
                   elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                   colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                   border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.secondary)
               ) {
                  Row(modifier=Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.Start){
                      Text(text = "Question - 1"
                          , style = TextStyle(
                              color = Color.Black
                          )
                      )

                      Row(modifier=Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.End) {
                          Icon(
                              Icons.Default.Delete, contentDescription = "back",
                              tint = Color.Black
                          )
                      }
                  }

               }
           }*/

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty1))
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
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Favorite List is Empty",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        }
    )




}
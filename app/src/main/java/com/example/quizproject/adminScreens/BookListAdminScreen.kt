package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.modifier.modifierLocalMapOf
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
import com.example.quizproject.R@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListAdminScreen (navController: NavController) {

    val itemList = remember { mutableStateListOf<String>() }
    val textFieldValue = remember { mutableStateOf("") }
    val showAlert = remember { mutableStateOf(false) }

    val context = LocalContext.current

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
                            .clickable { navController.popBackStack() }

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
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Box(
                    ) {
                        LottieAnimation(
                            modifier = Modifier
                                .size(330.dp),
                            iterations = 100,
                            composition = composition
                        )

                    }

                }

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

                            Button(onClick = { showAlert.value = false

                                if(textFieldValue.value.isNotEmpty()) {
                                    itemList.add(textFieldValue.value)
                                }

                                textFieldValue.value = ""

                            }) {
                                Text(text = "OK")
                            }
                        }
                    )
                }

                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {

                        Text(text = "book List", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black , modifier =  Modifier.padding(end = 70.dp))

                        Button(
                            onClick = { showAlert.value = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            //border = BorderStroke(1.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                            elevation = ButtonDefaults.buttonElevation(5.dp),
                        ) {

                            Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = Color.Black ,
                                modifier = Modifier.size(40.dp))
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(text = "Add Book", style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            ), color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(60.dp))

                    LazyRow( ) {

                        items(itemList) {item ->

                            Card(
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(150.dp)
                                    .padding(start = 20.dp, end = 20.dp)
                                    .clickable { navController.navigate("ChapterAdminScreen") },
                                elevation = CardDefaults.cardElevation(2.dp),
                                shape = RoundedCornerShape(30.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),

                                ) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                       // .padding(30.dp),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Column(  modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .padding(10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {

                                        Text(text = "$item" , fontSize = 18.sp, fontWeight = FontWeight.SemiBold , color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

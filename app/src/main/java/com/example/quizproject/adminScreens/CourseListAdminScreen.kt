package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListAdminScreen() {


    val itemList = remember { mutableStateListOf<String>() }
    val textFieldValue = remember { mutableStateOf("") }



    val showAlert = remember { mutableStateOf(false) }





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
                        modifier = Modifier.padding(start = 8.dp , end = 8.dp)

                    ){
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack", tint = Color.Black)
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
                        .fillMaxHeight(0.36f),
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

                Box (
                    modifier = Modifier.padding(start = 15.dp )
                ) {
                    Text(text = "Course Lists" , fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black )
                }


                Spacer(modifier = Modifier.height(30.dp))



                if (showAlert.value) {
                    AlertDialog(
                        onDismissRequest = { showAlert.value = false },


                        title = { Text(text = "Enter Course name") },
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



                                // Toast.makeText(context,itemList.size.toString(),Toast.LENGTH_SHORT).show()



                            }) {
                                Text(text = "OK")


                            }




                        }
                    )
                }



                Card ( modifier = Modifier
                    .fillMaxSize(),
                    shape = RoundedCornerShape(topStart = 30.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),

                    ){

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Card(

                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(100.dp)
                                .clickable { showAlert.value = true },

                            elevation = CardDefaults.cardElevation(2.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)


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
                                    horizontalArrangement = Arrangement.Center) {


                                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.Black, modifier = Modifier.padding(end = 20.dp))


                                    Text(text = " Add Course  " , fontSize = 14.sp, fontWeight = FontWeight.SemiBold , color = Color.Black)
                                }

                            }


                        }

                        Spacer(modifier = Modifier.height(50.dp))


                        for(item in itemList) {



                            Card(

                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .height(100.dp),

                                elevation = CardDefaults.cardElevation(2.dp),
                                shape = RoundedCornerShape(30.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)


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
                                        horizontalArrangement = Arrangement.Center) {


                                        Icon(imageVector = Icons.Default.Description, contentDescription = "Description", tint = Color.Black, modifier = Modifier.padding(end = 20.dp))


                                        Text(text = "$item" , fontSize = 14.sp, fontWeight = FontWeight.SemiBold , color = Color.Black)
                                    }

                                }


                            }


                            Spacer(modifier = Modifier.height(50.dp))






                        }




























                    }



                }











            }







        }









    )













}

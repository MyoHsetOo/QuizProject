package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.BorderColor
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
import androidx.compose.ui.modifier.modifierLocalMapOf
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
import com.example.quizproject.R@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListAdminScreen (navController: NavController) {

    val itemList = remember { mutableStateListOf<String>() }
    val textFieldValue = remember { mutableStateOf("") }
    val showAlert = remember { mutableStateOf(false) }

    val context = LocalContext.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.b))



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

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ){

                    IconButton(onClick = { navController.popBackStack() }){
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack", tint = MaterialTheme.colorScheme.onPrimary)
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
                        title = { Text(text = "Enter Book name",
                            style = TextStyle(
                                 color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp
                                )
                        ) },
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
                                    .border(1.dp, MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(10.dp)),

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
                            Button(onClick = { showAlert.value = false
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

                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {

                        Text(text = "Book List",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary ,
                            modifier =  Modifier.padding(end = 70.dp))

                        Button(
                            onClick = { showAlert.value = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            //border = BorderStroke(1.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                            elevation = ButtonDefaults.buttonElevation(5.dp),
                        ) {

                            Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = MaterialTheme.colorScheme.onPrimary ,
                                modifier = Modifier.size(30.dp))
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(text = "Add Book", style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    LazyRow( ) {

                        items(itemList) {item ->

                            BookCard(navController,item )


                        }
                    }
                }
            }
        }
    )

}

@Composable
private fun BookCard(
    navController: NavController,
    bookName : String
) {

    val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.openbook))


    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        modifier = Modifier
            .padding(15.dp)
            .width(150.dp)
            .height(200.dp)
            .clickable { navController.navigate("ChapterAdminScreen") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier.size(80.dp),
                bitmap = ImageBitmap.imageResource(id = R.drawable.openbook),
                contentDescription = "book_card"
            )

            Box(modifier = Modifier.padding( 20.dp)) {
                Column( modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = bookName,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold

                        )
                    )

                }
            }
        }
    }
}

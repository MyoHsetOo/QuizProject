@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.quizproject.userScreens
//
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
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

                    IconButton(onClick = { navController.popBackStack() }){
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


                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {

                        Text(text = "Book List", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black , modifier =  Modifier.padding(end = 70.dp))

                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    LazyRow( ) {

                        item {
                            BookCard(navController = navController, bookName = "Information Technology vol - 1 ")
                            BookCard(navController = navController, bookName = "Strategy and Management vol - 2 ")
                            BookCard(navController = navController, bookName = "Strategy and Management vol - 3 ")
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




    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        modifier = Modifier
            .padding(15.dp)
            .width(150.dp)
            .height(200.dp)
            .clickable { navController.navigate("chapterScreen") }
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


            /* Box(
             ) {
                 LottieAnimation(
                     modifier = Modifier
                         .size(200.dp),
                     iterations = 100,
                     composition = composition2
                 )

             }*/


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
package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
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
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen ( navController: NavController ) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bulb ))
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
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.8f)
                        .background(MaterialTheme.colorScheme.secondary)
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxHeight(0.25f)
                            .padding(start = 20.dp,)
                            .fillMaxWidth()
                            ,
                        verticalArrangement = Arrangement.Center
                    ){
                        Card(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                            ,
                            shape = CircleShape,
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ){
                                Image(painter = painterResource(id = R.drawable.rose1),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),

                                    alignment = Alignment.Center
                                )
                            }
                        }

                        Text(text = "Hello World" , style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary,

                        ), modifier = Modifier.padding( top = 10.dp, bottom = 10.dp))

                        Text(text = "myohsetoo@gmail.com" , style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary,

                            ), modifier = Modifier.padding( ))


                    }

                    Divider( thickness =0.5.dp, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding( horizontal = 20.dp))

                    Column (
                        modifier = Modifier.padding( start = 20.dp , top = 20.dp)

                    ){

                        Row (
                            modifier = Modifier.padding( vertical = 15.dp )
                        ){
                            Icon(imageVector = Icons.Default.Add, contentDescription = "logout" , tint = MaterialTheme.colorScheme.onPrimary )

                            Text(text = "Add Account", style = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            ), modifier = Modifier.padding( start = 10.dp )
                            )

                        }

                        Row (
                            modifier = Modifier.padding( vertical = 15.dp )
                        ){
                            Icon(imageVector = Icons.Default.PersonOutline, contentDescription = "logout", tint = MaterialTheme.colorScheme.onPrimary)

                            Text(text = "Contact", style = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            ), modifier = Modifier.padding( start = 10.dp )
                            )

                        }

                        Row (
                            modifier = Modifier.padding( vertical = 15.dp )
                        ){
                            Icon(imageVector = Icons.Outlined.Email, contentDescription = "logout", tint = MaterialTheme.colorScheme.onPrimary)

                            Text(text = "About", style = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            ), modifier = Modifier.padding( start = 10.dp )
                            )

                        }

                        Row (
                            modifier = Modifier.padding( vertical = 15.dp )
                        ){
                            Icon(imageVector = Icons.Outlined.SaveAlt, contentDescription = "logout", tint = MaterialTheme.colorScheme.onPrimary)

                            Text(text = "Save Message", style = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            ), modifier = Modifier.padding( start = 10.dp )
                            )

                        }

                        Row (
                            modifier = Modifier.padding( vertical = 15.dp ,)
                        ){
                            Icon(imageVector = Icons.Outlined.Settings, contentDescription = "logout", tint = MaterialTheme.colorScheme.onPrimary)

                            Text(text = "Setting", style = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            ), modifier = Modifier.padding( start = 10.dp )
                            )

                        }
                        
                       // Spacer(modifier = Modifier.height(80.dp))
                    }
                    Divider( thickness =0.6.dp, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding( horizontal = 20.dp , vertical = 15.dp))

                    Column (
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxHeight(),


                    ){

                        Row (
                            modifier = Modifier.padding( vertical = 15.dp ,)
                                .clickable {
                                    navController.navigate("LoginPage")
                                }
                        ){
                            Icon(imageVector = Icons.Outlined.Logout, contentDescription = "logout", tint = MaterialTheme.colorScheme.onPrimary)

                            Text(text = "Logout", style = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            ), modifier = Modifier.padding( start = 10.dp )
                            )

                        }

                    }
                }
            }
        },
    ) {
        Scaffold(

        ) { contentPadding ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .background(MaterialTheme.colorScheme.secondary)
                    .verticalScroll(rememberScrollState()),

                ){

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {
                        //navController.popBackStack()

                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }){
                        Icon(imageVector = Icons.Default.Menu,
                            contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onPrimary)
                    }
                    Text(text = "Quiz", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    ))
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

                Spacer(modifier = Modifier.height(40.dp))

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
                                    navController.navigate("CourseListScreen")

                                },
                                    modifier = Modifier.padding( top = 40.dp, bottom = 10.dp, start = 20.dp,),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary
                                    )
                                ) {
                                    Text(text = "View" , style = TextStyle(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 14.sp,
                                    ))
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
                                    //navController.navigate("CourseListScreen")

                                },
                                    modifier = Modifier.padding( top = 40.dp, bottom = 10.dp, start = 20.dp,),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary
                                    )
                                ) {
                                    Text(text = "View" , color = MaterialTheme.colorScheme.onPrimary )
                                }

                            }

                        }

                    }

                }

            }
        }
    }
}


/*
Scaffold (
containerColor = MaterialTheme.colorScheme.secondary,
topBar = {

    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = {
            navController.popBackStack()
        }){
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.Black)
        }
        Text(text = "Quiz", style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ))
    }

},
content = {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

        ){
        Column(
            modifier = Modifier.fillMaxHeight(1f),
            verticalArrangement = Arrangement.Bottom,

            ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                shape = RoundedCornerShape( topStart = 60.dp, topEnd = 60.dp ),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)

            ){
                Column(

                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            //.background(Color.LightGray)
                            //.padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f),
                    ){
                        Box(
                            modifier = Modifier
                                //.background(Color.Yellow)
                                .padding(horizontal = 10.dp, vertical = 30.dp)
                                .fillMaxWidth(0.3f)
                                .height(300.dp),
                            contentAlignment = Alignment.BottomStart

                        ){
                            Text(text = "Choose your Category", style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            ), modifier = Modifier.padding( start = 15.dp))
                        }
                        Box(
                            modifier = Modifier.height(700.dp),
                            contentAlignment = Alignment.BottomEnd

                        ){
                            Box(
                                contentAlignment = Alignment.BottomEnd
                            ){
                                LottieAnimation(
                                    modifier = Modifier
                                        .size(600.dp),
                                    iterations = 100,
                                    composition = composition
                                )

                            }

                        }
                    }

                    Column(
                        modifier = Modifier.padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Button(
                            onClick = {
                                navController.navigate("CourseListScreen")
                            },
                            modifier = Modifier
                                .padding(
                                    horizontal = 20.dp,
                                    vertical = 20.dp
                                )
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            elevation = ButtonDefaults.buttonElevation(10.dp),

                            ) {

                            Row (
                                modifier = Modifier.padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ){

                                Text(text = "ITPEC" , style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black
                                ), modifier = Modifier.padding(end = 5.dp)
                                )
                            }
                        }
                        // //Spacer( modifier = Modifier.height(3.dp))
                        Button(
                            onClick = {
                                navController.navigate("CourseListScreen")
                            },
                            modifier = Modifier
                                .padding(
                                    horizontal = 20.dp,
                                    vertical = 20.dp
                                )
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            elevation = ButtonDefaults.buttonElevation(10.dp),

                            ) {

                            Row (
                                modifier = Modifier.padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ){

                                Text(text = "Japanese" , style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black
                                ), modifier = Modifier.padding(end = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
)*/

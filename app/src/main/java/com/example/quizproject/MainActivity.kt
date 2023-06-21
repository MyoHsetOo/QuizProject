package com.example.quizproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.layout.ContentScale
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
import com.example.quizproject.adminScreens.AdminQuestionList
import com.example.quizproject.adminScreens.AdminQuestionScreen
import com.example.quizproject.adminScreens.BookListAdminScreen
import com.example.quizproject.adminScreens.CourseListAdminScreen
import com.example.quizproject.adminScreens.QuestionEntryForm
import com.example.quizproject.navigation.Nav
import com.example.quizproject.ui.theme.QuizProjectThem
import com.example.quizproject.userScreens.AdminUserConnectedScreen
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.FavoriteScreen
import com.example.quizproject.userScreens.HomeScreen
import kotlinx.coroutines.launch


///


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizProjectThem {
                // A surface container using the 'background' color from the theme
               Nav( )

                /*Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondary)
                        ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                    Card (
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                            .height(70.dp),
                        
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape( 15.dp),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ){

                        Card(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .fillMaxWidth()
                                .height(70.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            shape = RoundedCornerShape( 15.dp)

                        ) {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Fundamental Engineering ( FE )" , style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                )
                            }
                        }
                    }
                }*/
            }
        }
    }
}





@Composable
fun DrawerStateDemo() {
    // [START android_compose_layout_material_modal_drawer_programmatic]
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
                        .background(Color.LightGray)
                        .fillMaxHeight()
                        .fillMaxWidth(0.8f)
                ){
                    Text(text = "Hello WOrld")
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
                                    //navController.navigate("CourseListScreen")

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
    // [END android_compose_layout_material_modal_drawer_programmatic]
}

//Card
/*Column(

modifier = Modifier.fillMaxSize(),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(30.dp)
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Brush.linearGradient(
                        colors = listOf(

//                                            MaterialTheme.colorScheme.primary,
//
//                                            Color(0xFFE2EAF7),
                            Color(0xFFC3D0E6),
                            //  MaterialTheme.colorScheme.primary,


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
                ), modifier = Modifier.padding( top = 40.dp, bottom = 40.dp, start = 20.dp, ))

                Button(onClick = {

                },
                    modifier = Modifier.padding( top = 20.dp, bottom = 10.dp, start = 20.dp,),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(text = "View" , )
                }

            }

        }

    }


}*/



/*@Preview
@Composable
fun getCustomGradient(): Brush {
    return Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary),
        start = Offset.Zero, // Starting point of the gradient
        end = Offset.Infinite, // Ending point of the gradient
        tileMode = TileMode.Clamp // Tile mode for extending the gradient
    )
}*/
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizProjectThem {

    }
}
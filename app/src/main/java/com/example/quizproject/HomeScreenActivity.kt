package com.example.quizproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.dataRepository.MongoRepositoryImpl
//import com.example.quizproject.dataRepository.RealmSyncRepository
//import com.example.quizproject.dataRepository.SyncRepository


//import com.example.quizproject.navigation.NavActivity
import com.example.quizproject.ui.theme.QuizProjectThem
import com.example.quizproject.viewModel.HomeViewModel
import com.example.quizproject.viewModel.ToolbarEvent
import com.example.quizproject.viewModel.ToolbarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenActivity : ComponentActivity() {





    /*private val toolbarViewModel: ToolbarViewModel by viewModels {
        ToolbarViewModel.factory(repository, this)
    }*/

    var viewModelTab = ToolbarViewModel( )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*lifecycleScope.launch {
            toolbarViewModel.toolbarEvent
                .collect { toolbarEvent ->
                    when (toolbarEvent) {
                        ToolbarEvent.LogOut -> {
                            startActivity(Intent(this@HomeScreenActivity, ComposeLoginActivity::class.java))
                            finish()
                        }
                        is ToolbarEvent.Info ->
                            Log.e(TAG(), toolbarEvent.message)
                        is ToolbarEvent.Error ->
                            Log.e(TAG(), "${toolbarEvent.message}: ${toolbarEvent.throwable.message}")
                    }
                }
        }*/

        setContent {
            QuizProjectThem {

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bulb ))
                var isPlaying by remember { mutableStateOf(true) }


                var repository = MongoRepositoryImpl()

                var viewModel : HomeViewModel = HomeViewModel( repository)



                var categoryData by viewModel._categoryData

                var navController = NavController

                var isExpanded by remember {
                    mutableStateOf(false)
                }

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
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 15.dp,)
                                            .clickable {

                                                // Log.d(">>>CLick", "${viewModelTab.logOut()}")

                                                Log.d("CurrentUser<<<<<", "${app.currentUser}")


                                                CoroutineScope(Dispatchers.IO).launch {
                                                    runCatching {
                                                        app.currentUser?.logOut()
                                                        startActivity(
                                                            Intent(
                                                                this@HomeScreenActivity,
                                                                ComposeLoginActivity::class.java
                                                            )
                                                        )
                                                    }
                                                        .onSuccess {
                                                            viewModelTab.logOut()
                                                            startActivity(
                                                                Intent(
                                                                    this@HomeScreenActivity,
                                                                    ComposeLoginActivity::class.java
                                                                )
                                                            )
                                                        }
                                                        .onFailure {
                                                            viewModelTab.error(
                                                                ToolbarEvent.Error(
                                                                    "Log out failed",
                                                                    it
                                                                )
                                                            )
                                                        }
                                                }


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
                        LazyColumn (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(contentPadding)
                                .background(MaterialTheme.colorScheme.secondary),

                            ){

                            item {

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
                                    )
                                    )
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
                            }



                            items ( categoryData ){item ->

                                Card (
                                    modifier = Modifier
                                        .fillMaxWidth()

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

                                            Text(text = "${item.categoryName}", style = TextStyle(
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                fontSize = 16.sp,
                                                lineHeight = 25.sp,
                                                fontWeight = FontWeight.Bold
                                            ), modifier = Modifier.padding( top = 30.dp, bottom = 10.dp, start = 20.dp, ))

                                            Text(text = "${item.categoryDescription}",
                                                style = TextStyle(
                                                    fontSize = 12.sp,
                                                    lineHeight = 20.sp,
                                                    color = Color(0xFF6B7C97),

                                                    ), maxLines = if (isExpanded) 4 else 2 , overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                                                modifier = Modifier
                                                    .padding(
                                                        top = 5.dp,
                                                        bottom = 10.dp,
                                                        start = 30.dp,
                                                        end = 30.dp
                                                    )
                                                    .clickable {
                                                        isExpanded = !isExpanded
                                                    })

                                            Row (
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(end = 20.dp),
                                                horizontalArrangement = Arrangement.End
                                            ){

                                                TextButton(onClick = {

                                                    var  data = item._id.toHexString()

                                                   val intent = Intent( this@HomeScreenActivity , MainActivity::class.java )

                                                    intent.putExtra("id",data)

                                                    startActivity(intent)
                                                },
                                                    modifier = Modifier.padding( top = 10.dp, bottom = 10.dp, start = 20.dp,),
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = MaterialTheme.colorScheme.secondary
                                                    )
                                                ) {
                                                    Text(text = "Start Learning" , color = MaterialTheme.colorScheme.onPrimary,
                                                        style = TextStyle(
                                                            fontSize = 12.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            color = MaterialTheme.colorScheme.onPrimary
                                                        )
                                                    )
                                                }

                                            }



                                        }

                                    }

                                }

                            }









                        }
                    }
                }


            }
        }
    }
}
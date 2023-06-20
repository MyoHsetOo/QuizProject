package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R
import com.example.quizproject.dataModel.Answer
import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.example.quizproject.viewModel.HomeViewModel
import kotlinx.coroutines.launch

//////
/////
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeAdminScreen ( navController: NavController )  {
/////
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bulb))

    var isPlaying by remember { mutableStateOf(true) }

    val categoryTextField = remember { mutableStateOf("") }

    val descriptionTextField = remember { mutableStateOf("") }

    val isshowAlertDialog = remember { mutableStateOf(false) }

    val itemList = remember { mutableStateListOf<String>() }

    var isAddingBatch by remember {
        mutableStateOf(false)
    }

    var addBatchTextField = remember {
        mutableStateOf("")
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

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    var categorylist = remember {
        mutableStateListOf<Category>()
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    var dropdownItems = mutableListOf<String>()

    var repository = MongoRepositoryImpl()

    var viewModel : HomeViewModel = HomeViewModel( repository)

    var categoryData by viewModel._categoryData

    var categoryName by viewModel._categoryName

    var categoryDescription by viewModel._categoryDescription


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
                                .padding(vertical = 15.dp,)
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
                    .background(MaterialTheme.colorScheme.secondary)
                    ,

                ){

                Row (
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(5.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ){
                    Box( ){
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                        ){

                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }){
                                Icon(imageVector = Icons.Default.Menu,
                                    contentDescription = "menu",
                                    tint = MaterialTheme.colorScheme.onPrimary)
                            }
                            Text(text = "Quiz", style = TextStyle(
                                fontSize = 16.sp,

                                color = MaterialTheme.colorScheme.onPrimary
                            ))
                        }
                    }
                    Box {
                        Button(onClick = {
                            isshowAlertDialog.value = !isshowAlertDialog.value
                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.padding( end = 0.dp )
                        ) {
                            Row (
                                verticalAlignment = Alignment.CenterVertically,

                                ){
                                Icon(imageVector = Icons.Default.Add, contentDescription = "add",
                                    modifier = Modifier.padding(end = 0.dp, start = 5.dp))

                                Text(text = "Add Category" , style = TextStyle(
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                ), modifier = Modifier.padding( start = 10.dp, end = 0.dp))
                            }

                        }
                    }

                }

                if (isshowAlertDialog.value) {

                    AlertDialog(
                        modifier = Modifier.fillMaxHeight(0.7f),
                        onDismissRequest = { isshowAlertDialog.value = false },
                        title = { Text(text = "Enter Category and Description",
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

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()

                                    .padding(5.dp),
                                verticalArrangement = Arrangement.Center

                            ) {

                                OutlinedTextField(
                                    value = categoryName,
                                    onValueChange = { categoryName = it },
                                    modifier = Modifier
                                        //.border(1.dp, MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(10.dp))
                                        .padding(5.dp),

                                    placeholder = {
                                                  Text(text = "Enter category name.....", style = TextStyle(
                                                      fontSize = 12.sp,
                                                  ))
                                    },

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


                                OutlinedTextField(
                                    value = categoryDescription,
                                    onValueChange = { categoryDescription = it },
                                    modifier = Modifier

                                        //.border(1.dp, MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(10.dp))
                                        .padding(5.dp),

                                    placeholder = {
                                        Text(text = "Description...", style = TextStyle(
                                            fontSize = 12.sp,

                                            ))
                                    },

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

                            }


                        },
                        confirmButton = {
                            Button(onClick = {
                                isshowAlertDialog.value = false

                                viewModel.insertCategory()

                                /*var cateogry = Category(
                                    categoryName = categoryTextField.value,
                                    categoryDescription = descriptionTextField.value
                                )*/

                                /*cateName = categoryTextField
                                 cateDescripiton = descriptionTextField

                                Log.d("CateName>>>","$cateName")

                                Log.d("CateName>>>","$cateDescripiton")*/

                               // categorylist.add( cateogry )

                                categoryTextField.value = ""
                                descriptionTextField.value = ""


                            },
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary)
                            ) {
                                Text(text = "Submit", color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    )


                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
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

                Spacer(modifier = Modifier.height(30.dp))

                Column (
                    modifier = Modifier
                        .padding( top = 20.dp)
                ){
                    Log.d (">>>","${itemList.size}")

                    LazyColumn{

                        items( categoryData ) {item ->

                            Log.d(">>>>>","${categoryData.size}")

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
                                        .indication(interactionSource, LocalIndication.current)
                                        .pointerInput(true) {
                                            detectTapGestures(
                                                onLongPress = {
                                                    isContextMenuVisible = true
                                                    pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                                                },
                                                onPress = {
                                                    val press = PressInteraction.Press(it)
                                                    interactionSource.emit(press)
                                                    tryAwaitRelease()
                                                    interactionSource.emit(PressInteraction.Release(press))
                                                }
                                            )
                                        }
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
                                        ), modifier = Modifier.padding( top = 30.dp, bottom = 10.dp, start = 20.dp, end = 20.dp ))

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
                                                navController.navigate("CourseListAdminScreen")

                                            },
                                                modifier = Modifier.padding( top = 10.dp, bottom = 10.dp, start = 20.dp,),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.secondary
                                                )
                                            ) {
                                                Text(text = "Start Learning" , color = MaterialTheme.colorScheme.onPrimary,
                                                    style = TextStyle(
                                                        fontSize = 12.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                )
                                            }

                                        }



                                    }

                                }

                            }

                        }
                    }
                    /* LazyColumn{
                         items( itemList ) {item ->

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

                                         Text(text = "decfsderf", style = TextStyle(
                                             color = MaterialTheme.colorScheme.onPrimary,
                                             fontSize = 16.sp,
                                             lineHeight = 25.sp,
                                             fontWeight = FontWeight.Bold
                                         ), modifier = Modifier.padding( top = 30.dp, bottom = 20.dp, start = 20.dp, ))

                                         Button(onClick = {
                                             navController.navigate("CourseListAdminScreen")

                                         },
                                             modifier = Modifier.padding( top = 40.dp, bottom = 10.dp, start = 20.dp,),
                                             colors = ButtonDefaults.buttonColors(
                                                 containerColor = MaterialTheme.colorScheme.secondary
                                             )
                                         ) {
                                             Text(text = "View" , )
                                         }

                                     }

                                 }

                             }

                         }
                     }*/



                    /* Card (
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
                                     Text(text = "View" , )
                                 }

                             }

                         }

                     }*/

                }

            }
        }
    }




}


package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import android.util.Log
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.OptionMenu
import com.example.quizproject.R
import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.example.quizproject.viewModel.CourseViewModel
import com.example.quizproject.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId



//
//
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListAdminScreen(navController: NavController, id : String? ) {

    var count = remember {
        mutableStateOf(0)
    }

    val showAlert = remember { mutableStateOf(false) }

    val textFieldValue = remember { mutableStateOf("") }

    var courseList = remember { mutableStateListOf<CourseModel>() }





    var repository = MongoRepositoryImpl()

    var viewModel : CourseViewModel = CourseViewModel( repository )

    var viewModelHome : HomeViewModel = HomeViewModel(repository )


    var categoryData = viewModelHome._categoryData

    var courseData by viewModel._courseData

    var courseName by viewModel._courseName

    var showMenu by remember { mutableStateOf(false) }

    var courseId by remember {
        mutableStateOf("")
    }

    var courseCount =  remember {
        mutableStateOf(0)
    }




    var obj : BsonObjectId? = id?.let { ObjectId(it) }


LaunchedEffect(key1 = Unit, block = {
})

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                ),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
            ) {

                Row(modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Text(
                        text = "Course List",
                        style = androidx.compose.ui.text.TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )

                }


            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            item{

                Card(
                    modifier= Modifier
                        .fillMaxWidth(0.8f)
                        .height(130.dp)
                        .padding(top = 10.dp)
                        .clickable {
                            navController.navigate("CourseContentAdminScreen")
                        },
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                    elevation = CardDefaults.cardElevation(5.dp))
                {

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                            .clickable {
                                showAlert.value = true
                                if (showAlert.equals(true)) {

                                }
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {

                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(100.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    Icons.Default.Add, contentDescription = "back",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )

                            }
                        }

                        Column(horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)) {
                            Text(
                                text = "Add Course",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    lineHeight = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

                if (showAlert.value) {
                    AlertDialog(
                        onDismissRequest = { showAlert.value = false },
                        title = {
                            Text(
                                text = "Enter Course Name",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.sp
                                )
                            )
                        },
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
                                value = courseName,
                                onValueChange = { courseName = it },
                                modifier = Modifier
                                    .border(
                                        1.dp,
                                        MaterialTheme.colorScheme.onPrimary,
                                        shape = RoundedCornerShape(10.dp)
                                    ),

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
                            Button(
                                onClick = {

                                    Log.d("Size>>>>","${courseData.size}")
                                    Log.d("TextField>>>>","${courseName}")

                                   //viewModel.insertCourse()


                                    Log.d("OBJ>>>>","$obj")
                                    Log.d("OBJ>>>>","$courseName")


                                    viewModelHome.updateCategory( obj!!  , courseName  )

                                    courseName = ""

                                    /*itemList.add(textFieldValue.value)
                                    textFieldValue.value = ""*/



                                    showAlert.value = false
                                },
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary)
                            ) {
                                Text(text = "Submit", color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    )
                }
            }



            for ( itemCourse in categoryData.value){

                if ( itemCourse._id.equals(obj )){

                    items(itemCourse.courses.size){index: Int ->

                        var item = itemCourse.courses[index]

                        AdminFinalCard( count= (index+1).toString(),text = item.courseName,id= item._id, navController = navController, )

                        Log.d("ShowMenu>>>","$showMenu")

                    }

                }

            }


        }


    }
}





@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdminFinalCard( count:String,text:String, id: ObjectId , navController: NavController,  ) {

//    var countCount = remember {
//        mutableStateOf(0)
//    }

    var showMenu by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val density = LocalDensity.current

    Spacer(modifier = Modifier.height(30.dp))


    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier

            .fillMaxWidth(0.8f)
            .height(130.dp)
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            }
            .padding(top = 10.dp)
            .indication(interactionSource, LocalIndication.current)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        coroutineScope.launch {
                            // Perform actions on long press
                            // For example, show a context menu or start a drag gesture

                            Toast
                                .makeText(context, "long press", Toast.LENGTH_SHORT)
                                .show()
                            showMenu = !showMenu

                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())

                        }
                    }
                )
            },

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {

                    detectTapGestures(
                        onLongPress = {
                            showMenu = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())


                        },
                        onPress = {

                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))

                            // navController.navigate("CourseContentAdminScreen/${id.toHexString()}/${text}")


                        }
                    )


                }


        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                ) {


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Log.d("CCC>>>>",count)

                        Box( ) {
                            Text(
                                text = count,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )

                        }



                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Course",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {

                        Box (

                            modifier = Modifier


                                .indication(interactionSource, LocalIndication.current)
                                .pointerInput(true) {

                                    detectTapGestures(
                                        onLongPress = {
                                            showMenu = true
                                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())


                                        },
                                        onPress = {

                                            val press = PressInteraction.Press(it)
                                            interactionSource.emit(press)
                                            tryAwaitRelease()
                                            interactionSource.emit(PressInteraction.Release(press))

                                            // navController.navigate("CourseContentAdminScreen/${id.toHexString()}/${text}")


                                        }
                                    )


                                }

                        ){

                            Text(
                                text = text,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    lineHeight = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            )

                        }



                        IconButton(onClick = {
                            navController.navigate("CourseContentAdminScreen/${id.toHexString()}/${text}")

                        }) {
                            Icon(imageVector = Icons.Outlined.ArrowCircleRight, contentDescription = "Next")

                        }

                    }


                }
            }
        }
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            ),
             modifier = Modifier
                 .background(Color.White)
                 .width(IntrinsicSize.Min)
                 .wrapContentHeight()

        ) {

            Row(
                modifier = Modifier.width(140.dp)
            ){

                DropdownMenuItem(

                    text = {
                        Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {
                            Icon(imageVector = Icons.Default.Edit,
                                contentDescription = "edit",
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)

                            )
                            text("Edit") }
                    }
                    ,
                    modifier= Modifier
                        .weight(1f)
                    ,
                    onClick = { Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show() },
                )

                DropdownMenuItem(
                    text = {

                        Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {
                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = "delete",
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)

                            )
                            text("Delete") }

                    },
                    modifier= Modifier.weight(1f),
                    onClick = { Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show() })

            }

        }
    }




        /*Card(
            modifier= Modifier
                .fillMaxWidth(0.8f)
                .height(130.dp)
                .onSizeChanged {
                    itemHeight = with(density) { it.height.toDp() }
                }
                .padding(top = 10.dp)
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            coroutineScope.launch {
                                // Perform actions on long press
                                // For example, show a context menu or start a drag gesture

                                Toast
                                    .makeText(context, "long press", Toast.LENGTH_SHORT)
                                    .show()
                                showMenu = !showMenu

                                pressOffset = DpOffset(it.x.toDp(), it.y.toDp())

                            }
                        }
                    )
                },

            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
            elevation = CardDefaults.cardElevation(5.dp))
        {


            Row(
                modifier = Modifier.fillMaxSize(),
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                ) {


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Log.d("CCC>>>>",count)

                        Text(
                            text = count,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Course",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)) {
                    Text(
                        text = text,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )

                }
            }




        }*/





   /* if(showMenu) {
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            ),
            modifier = Modifier
                .background(Color.White)
                .width(IntrinsicSize.Min)
                .wrapContentHeight()


            //  .border(0.dp, color = Color.White, shape = RoundedCornerShape(50.dp)),



        ) {


            Row(modifier = Modifier.width(140.dp)
                // .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)),

            ) {


                DropdownMenuItem(

                    text = {
                        Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {
                            Icon(imageVector = Icons.Default.Edit,
                                contentDescription = "edit",
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)
                            )
                            text("Edit") }
                    }
                    ,
                    modifier= Modifier
                        .weight(1f)
                    ,
                    onClick = { Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show() },
                )

                DropdownMenuItem(
                    text = {

                        Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {
                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = "delete",
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)
                            )
                            text("Delete") }

                    },
                    modifier= Modifier.weight(1f),
                    onClick = { Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show() })
            }

        }
    }
*/




}



@Composable
fun text (text : String) {
    Text(text = text, color = Color.Black)

}



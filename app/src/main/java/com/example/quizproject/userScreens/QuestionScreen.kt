package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.example.quizproject.viewModel.QuestionScreenViewModel
import com.example.quizproject.viewModel.QuestionSetViewModel

import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId

//
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//////
fun QuestionScreen(navController: NavController , id : String? , number : String? , ind : Int?) {

    val scaffoldState = rememberBottomSheetScaffoldState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var isNextQuestion by remember {
        mutableStateOf(false)
    }

    var isPreviousQuestion by remember {
        mutableStateOf(false)
    }

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

    var repository = MongoRepositoryImpl()

    var viewModelQuestionScreen : QuestionScreenViewModel = QuestionScreenViewModel(repository)

    var questionScreenData = viewModelQuestionScreen._questionScreenData

    var viewModel : QuestionSetViewModel = QuestionSetViewModel( repository )

    var obj : BsonObjectId? = id?.let { BsonObjectId(it) }


    BottomSheetScaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.secondary),
        containerColor = MaterialTheme.colorScheme.secondary,
        sheetContainerColor = MaterialTheme.colorScheme.primary,
        sheetContentColor = Color.Black,
        sheetShadowElevation = 10.dp,
        sheetShape = RoundedCornerShape(40.dp),
        sheetContent = {

            for (item in questionScreenData.value) {

                if (item._id == obj) {

                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.primary),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Column() {
                            Text(text = "Solution",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                ))

                            Box(modifier = Modifier.width(50.dp)){
                                Text(text = "${item.solutionText}",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    ))
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 80.dp,
        topBar = {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
            ) {

                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint =MaterialTheme.colorScheme.onPrimary)
                    }
                    Text(text = "$number",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { },
                    horizontalArrangement = Arrangement.End,
                ) {

                    /*IconButton(onClick = {  }) {

                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "back", tint = MaterialTheme.colorScheme.onPrimary)
                    }*/

                    FavoriteButton()

                }
            }
                 },
    ) { innerPadding ->

        val pages = questionScreenData.value
        var currentPage by remember { mutableStateOf(ind) }


        for ( item  in questionScreenData.value ){

            if ( item._id == obj ){

                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .background(MaterialTheme.colorScheme.secondary)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Display current page
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(16.dp),
                        //contentAlignment = Alignment.Center
                    ) {
                        //Text(text = "${questionScreenData.value[currentPage].questionText}")


                        Column(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())

                        ) {

                            Text(
                                text = questionScreenData.value[currentPage!!].questionText,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 15.sp,
                                    lineHeight = 25.sp
                                ),
                                modifier = Modifier.padding(5.dp),

                                )

                            val uri = questionScreenData.value[currentPage!!].questionImage.toUri()

                            OutlinedCard(
                                shape = RoundedCornerShape(20.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                modifier = Modifier
                                    .padding(vertical = 10.dp)


                            ) {

                                Image(
                                    painter = rememberAsyncImagePainter(uri),
                                    contentDescription = "Picture",
                                )

                                Log.d("IMSAGE QUESTION>><<<", "${uri}")
                            }


                            val checkedItems = remember { mutableStateListOf<String>() }

                            val (selectedOption, onOptionSelected) = remember {
                                mutableStateOf("")
                            }

                            // Log.d("SIZE <>" , "${checkedItems.size}")



                                questionScreenData.value[currentPage!!].answers.forEach { text ->
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .selectable(
                                                selected = (text.answerText == selectedOption),
                                                onClick = {
                                                    onOptionSelected(text.answerText)
                                                    Log.d("Text<>", "$selectedOption")
                                                }
                                            )
                                            .padding(horizontal = 8.dp, vertical = 15.dp),
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {
                                        RadioButton(
                                            selected = (text.answerText == selectedOption),
                                            modifier = Modifier.size(20.dp),
                                            onClick = {
                                                onOptionSelected(text.answerText)

                                                Log.d("CHECKITEMS :::::", "${checkedItems.size}")
                                                Log.d("Text<>", "$selectedOption")

                                            },
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = MaterialTheme.colorScheme.onPrimary,
                                                unselectedColor = Color.LightGray
                                            )

                                        )
                                        Text(
                                            text = text.answerText,
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                lineHeight = 25.sp,
                                                color = MaterialTheme.colorScheme.onPrimary

                                            ),
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                                }
                                // Button navigation
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 15.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(
                                        onClick = {
                                            if (currentPage!! > 0) {
                                                currentPage = currentPage!! - 1
                                            }
                                        },
                                        enabled = currentPage!!> 0
                                    ) {
                                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Previous")

                                    }

                                    Button(
                                        onClick = {

                                            /*if ( randomElements[pageIndex].correctAnswerType == selectedOption){
                                                correctCount++
                                            }

                                            if(pageIndex == (randomElements.size-1)){

                                                percent = ((correctCount.toFloat()/randomElements.size.toFloat())*100)

                                                showDialog.value = true

                                                Toast.makeText(context,"$percent%",Toast.LENGTH_SHORT).show()
                                            }*/



                                            /*if ( randomElements[pageIndex].correctAnswerType == selectedOption){
                                                Toast.makeText(context,"true", Toast.LENGTH_LONG ).show() }
                                            else{
                                                Toast.makeText(context,"false", Toast.LENGTH_LONG ).show()

                                            }*/

                                        },
                                    ) {
                                        Text(
                                            text = "Submit",
                                            color = MaterialTheme.colorScheme.onPrimary,
                                        )
                                    }

                                    //Spacer(modifier = Modifier.width(16.dp))

                                    IconButton(
                                        onClick = {
                                            if (currentPage!! < pages.size - 1) {
                                                currentPage = currentPage!! + 1
                                            }
                                        },
                                        enabled = currentPage!! < pages.size - 1
                                    ) {
                                        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Forward" )
                                    }
                                }

                        }
                    }


                }

            }
        }





       /* Column(

            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()

        ) {


            //if (viewModel.questionSet.answers.isNotEmpty())


            *//*LazyColumn() {

                items(questionScreenData.value.size) {index ->

                    //val index = questionScreenData.value.indexOf(item)

                    //var item = questionScreenData.value[index]

                    Log.d("Normal item<<>>>","${index}")
                    
                    Text(text = "${questionScreenData.value[index+3].questionText}")
                    
                    Text(text = "$index+3")

                    Text(text = "$index")

                   *//**//* if (item._id == obj) {

                        if( isNextQuestion ){

                            isNextQuestion = false

                            var nextCount = index + 1

                            var itemNext = questionScreenData.value[nextCount]

                            Log.d("ADD Index<<>>>","$nextCount")

                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {

                                Text(
                                    text = itemNext.questionText,
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 15.sp,
                                        lineHeight = 25.sp
                                    ),
                                    modifier = Modifier.padding(5.dp),

                                    )

                                val uri = itemNext.questionImage.toUri()

                                OutlinedCard(
                                    shape = RoundedCornerShape(20.dp),
                                    border = BorderStroke(1.dp, Color.Black),
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .fillMaxSize()

                                ) {

                                    Text(
                                                text = "${uri}", style = TextStyle(
                                                    fontSize = 16.sp,
                                                    color = MaterialTheme.colorScheme.onPrimary
                                                )
                                            )
                                    Image(
                                        painter = rememberAsyncImagePainter(uri),
                                        contentDescription = "Picture",
                                    )

                                    Log.d("IMSAGE QUESTION>><<<", "${uri}")
                                }


                                //for (answerItem in item.answers) {


                                //val radioOptions : List<AnswerModel> = item.answers

                                val checkedItems = remember { mutableStateListOf<String>() }

                                 for ( item in item.answers ){
                                        //checkedItems.add(item.answerText)



                                // Log.d("SIZE <>" , "${checkedItems.size}")


                                Column {
                                    itemNext.answers.forEach { text ->
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .selectable(
                                                    selected = (text.answerText == selectedOption),
                                                    onClick = {
                                                        onOptionSelected(text.answerText)
                                                        Log.d("Text<>", "$selectedOption")
                                                    }
                                                )
                                                .padding(horizontal = 8.dp, vertical = 15.dp),
                                            verticalAlignment = Alignment.CenterVertically

                                        ) {
                                            RadioButton(
                                                selected = (text.answerText == selectedOption),
                                                modifier = Modifier.size(20.dp),
                                                onClick = {
                                                    onOptionSelected(text.answerText)

                                                    Log.d("CHECKITEMS :::::", "${checkedItems.size}")
                                                    Log.d("Text<>", "$selectedOption")

                                                },
                                                colors = RadioButtonDefaults.colors(
                                                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                                                    unselectedColor = Color.LightGray
                                                )

                                            )
                                            Text(
                                                text = text.answerText,
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    lineHeight = 25.sp,
                                                    color = MaterialTheme.colorScheme.onPrimary

                                                ),
                                                modifier = Modifier.padding(start = 10.dp)
                                            )
                                        }
                                    }

                                }
                            }

                        } else if ( isPreviousQuestion ){

                            isPreviousQuestion = false

                            var previousItem = index - 1

                            var itemPrevious = questionScreenData.value[previousItem]

                            Log.d("ADD Index<<>>>","$previousItem")

                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {

                                Text(
                                    text = itemPrevious.questionText,
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 15.sp,
                                        lineHeight = 25.sp
                                    ),
                                    modifier = Modifier.padding(5.dp),

                                    )

                                val uri = itemPrevious.questionImage.toUri()

                                OutlinedCard(
                                    shape = RoundedCornerShape(20.dp),
                                    border = BorderStroke(1.dp, Color.Black),
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .fillMaxSize()

                                ) {

                                    Text(
                                                text = "${uri}", style = TextStyle(
                                                    fontSize = 16.sp,
                                                    color = MaterialTheme.colorScheme.onPrimary
                                                )
                                            )
                                    Image(
                                        painter = rememberAsyncImagePainter(uri),
                                        contentDescription = "Picture",
                                    )

                                    Log.d("IMSAGE QUESTION>><<<", "${uri}")
                                }


                                //for (answerItem in item.answers) {


                                //val radioOptions : List<AnswerModel> = item.answers

                                val checkedItems = remember { mutableStateListOf<String>() }

                                 for ( item in item.answers ){
                                        //checkedItems.add(item.answerText)



                                // Log.d("SIZE <>" , "${checkedItems.size}")


                                Column {
                                    itemPrevious.answers.forEach { text ->
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .selectable(
                                                    selected = (text.answerText == selectedOption),
                                                    onClick = {
                                                        onOptionSelected(text.answerText)
                                                        Log.d("Text<>", "$selectedOption")
                                                    }
                                                )
                                                .padding(horizontal = 8.dp, vertical = 15.dp),
                                            verticalAlignment = Alignment.CenterVertically

                                        ) {
                                            RadioButton(
                                                selected = (text.answerText == selectedOption),
                                                modifier = Modifier.size(20.dp),
                                                onClick = {
                                                    onOptionSelected(text.answerText)

                                                    Log.d("CHECKITEMS :::::", "${checkedItems.size}")
                                                    Log.d("Text<>", "$selectedOption")

                                                },
                                                colors = RadioButtonDefaults.colors(
                                                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                                                    unselectedColor = Color.LightGray
                                                )

                                            )
                                            Text(
                                                text = text.answerText,
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    lineHeight = 25.sp,
                                                    color = MaterialTheme.colorScheme.onPrimary

                                                ),
                                                modifier = Modifier.padding(start = 10.dp)
                                            )
                                        }
                                    }

                                }
                            }

                        }else{

                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {

                                Text(
                                    text = item.questionText,
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 15.sp,
                                        lineHeight = 25.sp
                                    ),
                                    modifier = Modifier.padding(5.dp),

                                    )

                                val uri = item.questionImage.toUri()

                                OutlinedCard(
                                    shape = RoundedCornerShape(20.dp),
                                    border = BorderStroke(1.dp, Color.Black),
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .fillMaxSize()

                                ) {

                                    Text(
                                                text = "${uri}", style = TextStyle(
                                                    fontSize = 16.sp,
                                                    color = MaterialTheme.colorScheme.onPrimary
                                                )
                                            )
                                    Image(
                                        painter = rememberAsyncImagePainter(uri),
                                        contentDescription = "Picture",
                                    )

                                    Log.d("IMSAGE QUESTION>><<<", "${uri}")
                                }


                                //for (answerItem in item.answers) {


                                //val radioOptions : List<AnswerModel> = item.answers

                                val checkedItems = remember { mutableStateListOf<String>() }

                                 for ( item in item.answers ){
                                        //checkedItems.add(item.answerText)



                                // Log.d("SIZE <>" , "${checkedItems.size}")


                                Column {
                                    item.answers.forEach { text ->
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .selectable(
                                                    selected = (text.answerText == selectedOption),
                                                    onClick = {
                                                        onOptionSelected(text.answerText)
                                                        Log.d("Text<>", "$selectedOption")
                                                    }
                                                )
                                                .padding(horizontal = 8.dp, vertical = 15.dp),
                                            verticalAlignment = Alignment.CenterVertically

                                        ) {
                                            RadioButton(
                                                selected = (text.answerText == selectedOption),
                                                modifier = Modifier.size(20.dp),
                                                onClick = {
                                                    onOptionSelected(text.answerText)

                                                    Log.d("CHECKITEMS :::::", "${checkedItems.size}")
                                                    Log.d("Text<>", "$selectedOption")

                                                },
                                                colors = RadioButtonDefaults.colors(
                                                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                                                    unselectedColor = Color.LightGray
                                                )

                                            )
                                            Text(
                                                text = text.answerText,
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    lineHeight = 25.sp,
                                                    color = MaterialTheme.colorScheme.onPrimary

                                                ),
                                                modifier = Modifier.padding(start = 10.dp)
                                            )
                                        }
                                    }

                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding( vertical = 20.dp ),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            IconButton(onClick = {
                                isPreviousQuestion = true
                                Log.d("PREVIOUS<<>>", "$isPreviousQuestion")

                            }) {

                                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Previous")

                            }

                            Button(
                                //modifier = Modifier.padding(top = 30.dp),
                                onClick = {

                                    if ( item.correctAnswerType == selectedOption){
                                        Toast.makeText(context,"true", Toast.LENGTH_LONG ).show() }
                                    else{
                                        Toast.makeText(context,"false", Toast.LENGTH_LONG ).show()

                                    }



                                    Log.d("<<<<<>>>>>", "${questionScreenData.value.size}")
                                },
                            ) {
                                Text(
                                    text = "Submit",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }

                            IconButton(onClick = {
                                isNextQuestion = true
                                index++
                                Log.d("NEXT<<>>", "$isNextQuestion")

                            }) {
                                Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Forward")
                            }


                        }
                    }*//**//*
                }
            }*//*
        }*/
    }
}



@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier
                .graphicsLayer {
                    scaleX = 1.3f
                    scaleY = 1.3f
                }
                .size(18.dp),
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}


















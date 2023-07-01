package com.example.quizproject.quizz




import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.mongodb.kbson.ObjectId
import kotlin.random.Random

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun RandomQuestionScreen (navController: NavController, count: Int?) {

    var randomList = mutableListOf<QuestionSet>()

    var repository = MongoRepositoryImpl()


    var viewModelRandomScreen : QuizChooseViewModel = QuizChooseViewModel( repository )

    var quickSetData = viewModelRandomScreen._questionSetData

    //Book

    var bookData = viewModelRandomScreen._bookData



    //Chapter

    var chapterData = viewModelRandomScreen._chapterData

    var chapterListId = getList()

    //QuestionSet

    var context = LocalContext.current



    val showDialog = remember { mutableStateOf(false) }

    var correctCount = 0

    var percent = 0f

    val progress = remember { mutableStateOf( percent ) }


    Surface(
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(MaterialTheme.colorScheme.primary)

                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Default.ArrowBack, contentDescription = "back",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        Text(
                            text = "QuizScreen",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                    }
                }
            }


            for (question in quickSetData.value) {

                for(chapterId in chapterListId) {



                    if(question.questionChapterId.equals("BsonObjectId($chapterId)")) {

                        randomList.add(question)

                    }

                }

            }

            val randomElements = randomList.asSequence().shuffled().take(count!!).toList()

            if (showDialog.value) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    AlertDialog(
                        modifier = Modifier.size(300.dp),
                        onDismissRequest = { },
                        title = {
                            Text(
                                text = "Your Score",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.sp
                                )
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.secondary,
                        text = {

                            Row(
                                modifier = Modifier.size(250.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                ActionCircle(progress = percent/100)

                                Spacer(modifier = Modifier.width(20.dp))

                                Column {
                                    Button(modifier = Modifier
                                        .background(MaterialTheme.colorScheme.primary,
                                            shape = RoundedCornerShape(20.dp)

                                        )
                                        .width(110.dp),
                                        onClick = { }) {
                                        Text(text = "Review")
                                    }

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Button(modifier = Modifier.background(MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                        .width(110.dp),
                                        onClick = {

                                            navController.popBackStack()

                                        }) {
                                        Text(text = "Restart")
                                    }

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Button(
                                        modifier = Modifier.background(MaterialTheme.colorScheme.primary,
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                            .width(110.dp),
                                        onClick = {
                                            navController.navigate("CourseContent")
                                        }) {
                                        Text(text = "Home")
                                    }
                                }
                            }
                        },
                        confirmButton = {

                        }
                    )
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {

                val items = (1..randomElements.size).toList()

                HorizontalPager(count = items.size
                    , modifier = Modifier.fillMaxSize()
                ) {
                        pageIndex ->


                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())

                    ) {

                        Text(
                            text = randomElements[pageIndex].questionText,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 15.sp,
                                lineHeight = 25.sp
                            ),
                            modifier = Modifier.padding(5.dp),

                            )

                        val uri = randomElements[pageIndex].questionImage.toUri()

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


                        Column {
                            randomElements[pageIndex].answers.forEach { text ->
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Button(
                                    modifier = Modifier.padding(top = 30.dp),
                                    onClick = {

                                        if ( randomElements[pageIndex].correctAnswerType == selectedOption){
                                            correctCount++
                                        }

                                        if(pageIndex == (randomElements.size-1)){

                                            percent = ((correctCount.toFloat()/randomElements.size.toFloat())*100)

                                            showDialog.value = true

                                            Toast.makeText(context,"$percent%",Toast.LENGTH_SHORT).show()
                                        }



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
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PagerWithButtonNavigation() {
    val pages = listOf("Page 1", "Page 2", "Page 3")
    var currentPage by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize()) {
        // Display current page
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = pages[currentPage])
        }

        // Button navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (currentPage > 0) {
                        currentPage--
                    }
                },
                enabled = currentPage > 0
            ) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (currentPage < pages.size - 1) {
                        currentPage++
                    }
                },
                enabled = currentPage < pages.size - 1
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ActionCircle(progress: Float) {
    Box( contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp) )
    {
        CircularProgressIndicator( progress = progress, modifier = Modifier
            .size(200.dp)
            .padding(4.dp) )
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.bodyMedium ,
            color = MaterialTheme.colorScheme.onPrimary)
    }
}
package com.example.quizproject.quizz

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import org.mongodb.kbson.ObjectId

@Composable
fun RandomQuestionScreen (navController: NavController) {

    var repository = MongoRepositoryImpl()


    var viewModelRandomScreen : QuizChooseViewModel = QuizChooseViewModel( repository )


    //Book

    var bookData = viewModelRandomScreen._bookData



    //Chapter

    var chapterData = viewModelRandomScreen._chapterData



    //QuestionSet

    var quickSetData = viewModelRandomScreen._questionSetData

    var context = LocalContext.current
    
    



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

            Column(modifier = Modifier.fillMaxSize()) {

                var chapterListId = getList()

                var chapterName = getListName()

                Text(text = "Real Loop", color = Color.Black)

                for (question in quickSetData.value) {

                    for(chapterId in chapterListId) {

                        if(question.questionChapterId.equals("BsonObjectId($chapterId)")) {

                            Column(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())

                            ) {

                                Text(
                                    text = question.questionText,
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontSize = 15.sp,
                                        lineHeight = 25.sp
                                    ),
                                    modifier = Modifier.padding(5.dp),

                                    )

                                val uri = question.questionImage.toUri()

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
                                    question.answers.forEach { text ->
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

                                                if ( question.correctAnswerType == selectedOption){
                                                    Toast.makeText(context,"true", Toast.LENGTH_LONG ).show() }
                                                else{
                                                    Toast.makeText(context,"false", Toast.LENGTH_LONG ).show()

                                                }

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
    }




}
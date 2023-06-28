package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import android.app.Notification.Style
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.example.quizproject.viewModel.BookViewModel
import com.example.quizproject.viewModel.ChapterViewModel
import com.example.quizproject.viewModel.QuestionSetViewModel
import org.mongodb.kbson.BsonObjectId

//
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionList(navController: NavController , id : String? , name : String? ) {
    val list = listOf(
        Question("Question", 1),
        Question("Question", 2),
        Question("Question", 3)
    )

    var repository = MongoRepositoryImpl()

    var viewModelQuestionSet : QuestionSetViewModel = QuestionSetViewModel(repository)

    var questionSetData = viewModelQuestionSet._questionSetData

    var obj : BsonObjectId? = id?.let { BsonObjectId(it) }

    var viewModelChapter : ChapterViewModel = ChapterViewModel( repository )

    var chapterData = viewModelChapter._chapterData


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column (modifier=Modifier.background(MaterialTheme.colorScheme.secondary))
        {
            Row (
                modifier = Modifier.height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = "back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Text(
                    text = "$name",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.padding( start = 5.dp )

                )

            }

            Column(modifier=Modifier
                .fillMaxWidth()
                .padding( top = 10.dp )
                .background(MaterialTheme.colorScheme.secondary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for( item in chapterData.value) {

                    if (item._id == obj) {

                        LazyColumn {
                            items(item.questions) { item ->
                                Button(
                                    onClick = { navController.navigate("QuestionScreen/${item._id.toHexString()}/${item.questionNo}")},
                                    modifier = Modifier
                                        .fillMaxWidth(0.95f)
                                        .height(80.dp)
                                        .padding(15.dp),
                                    shape = RoundedCornerShape(20.dp),
                                    elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                                    ) {
                                    Text(text = item.questionNo
                                        , style = TextStyle(
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    )
                                }
                                // Spacer(modifier = Modifier.height(20.dp))
                            }
                        }

                    }
                }


            }
        }
    }
}


data class Question(

    val question : String,
    val number : Int
){


}
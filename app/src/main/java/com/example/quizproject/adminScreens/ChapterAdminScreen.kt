package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizproject.dataModel.Chapter
//
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterAdminScreen( navController: NavController ) {
/////
    var addChapterField = remember {
        mutableStateOf("")
    }

    var chapterList = remember {
        mutableStateListOf<Chapter>()

    }

    var isAddChapter by remember {
        mutableStateOf(false)
    }

    var count = remember {
        mutableStateOf(0)
    }

    Surface (
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.fillMaxSize()
    ){
        Column {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,

                ){
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = MaterialTheme.colorScheme.onPrimary)
                }

                Text(text = "Information Technology", modifier = Modifier.padding( start = 5.dp), style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp
                ) )

            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()

            ){

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .fillMaxWidth()

                ) {

                    Card(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .clickable {

                                isAddChapter = !isAddChapter
                                count.value++
                                Log.d(">>>>>", "$isAddChapter")

                                var chapter = Chapter(
                                    chapterNo = count.value,
                                    chapterName = addChapterField.value
                                )
                                chapterList.add(chapter)
                                Log.d("Size>>>", "${chapterList.size}")
                            },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(15.dp)
                            ,

                            ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "menu",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                //modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth()
                            ,
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                    ) {
                        TextField(
                            value = addChapterField.value,
                            enabled = true,
                            onValueChange = { addChapterField.value = it },
                            modifier = Modifier,

                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                //textColor = Color.Black
                            ),
                            placeholder = {
                                Text(text = "Add chapter")
                            }
                        )
                    }
                }


                LazyColumn() {
                    items(chapterList) { item ->

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)

                        ) {

                            Card(
                                modifier = Modifier
                                    .fillMaxHeight(0.1f)
                                    .fillMaxWidth(0.18f)
                                    .padding(start = 10.dp),
                                shape = RoundedCornerShape(20.dp),
                                elevation = CardDefaults.cardElevation(5.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),

                                    ) {
                                    Text(
                                        text = "${item.chapterNo}",
                                        modifier = Modifier.padding(15.dp),
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.1f),
                                onClick = {
                                    navController.navigate("AdminQuestionList")
                                },
                                shape = RoundedCornerShape(20.dp),
                                elevation = CardDefaults.cardElevation(5.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                            ) {
                                Text(
                                    text = "${item.chapterName}",
                                    modifier = Modifier.padding(15.dp),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }

            }

        }
    }

}


/*Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 20.dp,),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 10.dp)

                        ) {

                            Card(
                                modifier = Modifier
                                    .fillMaxHeight(0.1f)
                                    .fillMaxWidth(0.18f)

                                    .clickable {

                                        isAddChapter = !isAddChapter
                                        count.value++
                                        Log.d(">>>>>", "$isAddChapter")

                                        var chapter = Chapter(
                                            chapterNo = count.value,
                                            chapterName = addChapterField.value
                                        )
                                        chapterList.add(chapter)
                                        Log.d("Size>>>", "${chapterList.size}")

                                    },
                                shape = RoundedCornerShape(20.dp),
                                elevation = CardDefaults.cardElevation(20.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),

                                    ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "menu",
                                        tint = Color.Black,
                                        //modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.1f),
                                shape = RoundedCornerShape(20.dp),
                                elevation = CardDefaults.cardElevation(20.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                            ) {
                                TextField(
                                    value = addChapterField.value,
                                    enabled = true,
                                    onValueChange = { addChapterField.value = it },
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .height(100.dp),
                                    maxLines = 1,
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                        focusedTextColor = Color.Black,
                                        unfocusedTextColor = Color.Black,
                                        //textColor = Color.Black
                                    )
                                )
                            }
                        }

                        LazyColumn() {
                            items(chapterList) { item ->

                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 7.dp)

                                ) {

                                    Card(
                                        modifier = Modifier
                                            .fillMaxHeight(0.1f)
                                            .fillMaxWidth(0.18f)
                                            .padding(start = 10.dp),
                                        shape = RoundedCornerShape(20.dp),
                                        elevation = CardDefaults.cardElevation(20.dp),
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(),

                                            ) {
                                            Text(
                                                text = "${item.chapterNo}",
                                                modifier = Modifier.padding(15.dp),
                                                color = Color.Black
                                            )
                                        }
                                    }
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.1f),
                                        onClick = {
                                            //navController.navigate("AdminQuestionList")
                                        },
                                        shape = RoundedCornerShape(20.dp),
                                        elevation = CardDefaults.cardElevation(20.dp),
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                                    ) {
                                        Text(
                                            text = "${item.chapterName}",
                                            modifier = Modifier.padding(15.dp),
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }




                }*/



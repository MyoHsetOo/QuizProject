package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterAdminScreen( navController: NavController ) {

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

    Scaffold (
        containerColor = MaterialTheme.colorScheme.secondary,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(
                        modifier = Modifier.padding(8.dp)
                            .clickable { navController.popBackStack() }

                    ){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = Color.Black
                        )
                    }
                    Text(text = "Old Question", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    )
                }
            }
        },
        content = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                ){
                    Card (
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .fillMaxHeight(0.8f)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(50.dp),
                    ){
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp, horizontal = 20.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ){

                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 15.dp)

                                    ){

                                    Card(
                                        modifier = Modifier
                                            .fillMaxHeight(0.1f)
                                            .fillMaxWidth(0.18f)
                                            .padding(start = 10.dp)
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
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(),

                                        ){
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "menu",
                                                tint = Color.Black,
                                                modifier = Modifier.size(25.dp)
                                            )
                                        }
                                    }
                                    Card (
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.1f),
                                        shape = RoundedCornerShape(20.dp),
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                                    ){
                                        TextField(
                                            value = addChapterField.value,
                                            enabled = true,
                                            onValueChange = { addChapterField.value = it },
                                            modifier = Modifier.height(100.dp),
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

                            LazyColumn(){
                                items( chapterList ) {item ->

                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(vertical = 7.dp )

                                        ){

                                        Card(
                                            modifier = Modifier
                                                .fillMaxHeight(0.1f)
                                                .fillMaxWidth(0.18f)
                                                .padding(start = 10.dp)
                                                ,
                                            shape = RoundedCornerShape(20.dp),
                                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                                        ) {
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .fillMaxHeight(),

                                                ){
                                                Text(text = "${item.chapterNo}",
                                                    modifier = Modifier.padding(15.dp),
                                                    color = Color.Black
                                                )
                                            }
                                        }
                                        Card (
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.1f),
                                            onClick = {
                                                navController.navigate("AdminQuestionList")
                                                      },
                                            shape = RoundedCornerShape(20.dp),
                                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                                        ){
                                            Text(text = "${item.chapterName}",
                                                modifier = Modifier.padding(15.dp),
                                                color = Color.Black)
                                        }
                                    }



                                }
                            }

                        }

                    }

                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextArea() {
    var text by rememberSaveable {
        mutableStateOf(
            ""
        )
    }
    Column {
        TextField(
            value = text,
            enabled = false,
            onValueChange = { text = it },
            modifier = Modifier.height(100.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        )

    }

}
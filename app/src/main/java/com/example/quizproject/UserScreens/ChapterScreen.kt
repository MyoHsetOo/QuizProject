package com.example.quizproject.UserScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterScreen( ) {

    var addChapterField = remember {
        mutableStateOf("")
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

                    ){
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                    Text(text = "Old Question", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
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
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ){

                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,

                                    ){

                                    Card(
                                        modifier = Modifier
                                            .fillMaxHeight(0.1f)
                                            .fillMaxWidth(0.18f)
                                            .padding(start = 10.dp),
                                        shape = CircleShape,
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight()
                                        ){
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "menu",

                                                modifier = Modifier.size(25.dp)
                                            )
                                        }
                                    }
                                    Card (
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.1f),
                                        shape = RoundedCornerShape(80.dp),
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)

                                    ){
                                        TextField(
                                            value = addChapterField.value,
                                            enabled = true,
                                            onValueChange = { addChapterField.value = it },
                                            modifier = Modifier.height(100.dp),
                                            maxLines = 1,
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = MaterialTheme.colorScheme.secondary,
                                                textColor = Color.Black
                                            )
                                        )
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
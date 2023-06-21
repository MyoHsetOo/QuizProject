package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//
//
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminQuestionScreen(navController: NavController) {

    val scaffoldState = rememberBottomSheetScaffoldState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var isOnClick by remember {
        mutableStateOf(false)
    }


    BottomSheetScaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.secondary),
        containerColor = MaterialTheme.colorScheme.secondary,
        sheetContainerColor = MaterialTheme.colorScheme.primary,
        sheetContentColor = MaterialTheme.colorScheme.onPrimary,
        sheetShadowElevation = 10.dp,
        sheetShape = RoundedCornerShape(40.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Solution", style = TextStyle(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                )
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 80.dp,
        topBar = {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(MaterialTheme.colorScheme.secondary)
                ) {

                    Row(
                        modifier = Modifier.padding(10.dp)
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Default.ArrowBack, contentDescription = "back",
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "2023 April ",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            Icons.Default.Edit, contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            Icons.Default.Delete, contentDescription = "back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        },
    ) { innerPadding ->

        Column(

            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
        ) {

            Text(
                text = "Q. Which of the following is the system configuration that has the highest availability? Here, when systems are connected in parallel, the systems are considered to be operational if at least one (1) of them is operating.",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                   lineHeight = 25.sp
                ),
                modifier = Modifier.padding(5.dp),

            )
            Card(

                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                onClick = {
                    isOnClick = !isOnClick
                    Log.d("CardColor>>>>", "$isOnClick")
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (isOnClick) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )

            ) {
                Row {

                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }


                    Box(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onPrimary,
                                lineHeight = 25.sp)
                        )

                    }

                }


            }

            Card(
                modifier = Modifier.padding(15.dp),
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Box(
                    modifier = Modifier.padding(15.dp)
                ) {

                    Text(
                        text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            lineHeight = 25.sp)
                    )

                }
            }

            Card(

                modifier = Modifier.padding(10.dp),

                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Box(
                    modifier = Modifier.padding(15.dp)
                ) {

                    Text(
                        text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary ,
                            lineHeight = 25.sp
                    )
                    )

                }
            }

            Card(

                modifier = Modifier.padding(15.dp),
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )

            ) {
                Box(
                    modifier = Modifier.padding(15.dp)
                ) {

                    Text(
                        text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            lineHeight = 25.sp)
                    )

                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    modifier = Modifier.padding(top = 30.dp), onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = "Submit",
                        color = MaterialTheme.colorScheme.onPrimary ,
                    )
                }

            }
        }
    }
}


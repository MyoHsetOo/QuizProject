package com.example.quizproject.adminScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun AdminQuestionScreen(){
    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondary,
        topBar = {
            Column {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {


                    Row(modifier = Modifier.padding(10.dp)) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint = Color.Black)

                        Text(text = "FE Class",
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                    }
                    Row(modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            Icons.Default.Edit, contentDescription = "back",
                            tint = Color.Black)
                        Icon(
                            Icons.Default.Delete, contentDescription = "back",
                            tint = Color.Black)
                    }



                }
            }
        },
        content = {
            Column(modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "Q. Which of the following is the system configuration that has the highest availability? Here, when systems are connected in parallel, the systems are considered to be operational if at least one (1) of them is operating.")



                //Spacer(modifier = Modifier.height(230.dp))
                Card(modifier = Modifier.padding(top = 30.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                    onClick = {} ) {
                    Text(text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                        style = TextStyle(color = Color.Black)
                    )
                }
                Card(modifier = Modifier.padding(top = 30.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                    onClick = {} ) {
                    Text(text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                        style = TextStyle(color = Color.Black)
                    )
                }
                Card(modifier = Modifier.padding(top = 30.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                    onClick = {} ) {
                    Text(text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                        style = TextStyle(color = Color.Black)
                    )
                }
                Card(modifier = Modifier.padding(top = 30.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                    onClick = {} ) {
                    Text(text = "(a) Three (3) identical systems, each with an availability of 80%, are connected in parallel.",
                        style = TextStyle(color = Color.Black)
                    )
                }

                Button(modifier = Modifier.padding(top=30.dp),onClick = { /*TODO*/ },
                ) {
                    Text(text = "Submit")
                }


            }

            Column {

            }



        }

    )
}
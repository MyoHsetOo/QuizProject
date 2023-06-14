package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminUserConnectedScreen (navController: NavController) {

    Scaffold(

        containerColor = MaterialTheme.colorScheme.primary,

        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(5.dp),
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                   /* Box(
                        modifier = Modifier.padding(8.dp)

                    ){
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }*/


                }
            }
        },

        content = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = "Choose your role", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                    , color = Color.Black
                ))


                Spacer(modifier = Modifier.height(20.dp))


                Button(
                    onClick = {  navController.navigate("HomeAdminScreen")},
                    modifier = Modifier
                        .padding(

                            horizontal = 20.dp,
                            vertical = 20.dp
                        )
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                ) {

                    Text(
                        text = "Admin Role", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold, color = Color.Black
                        )
                    )
                    


                }

                    Button(
                        onClick = { navController.navigate("HomeScreen")},
                        modifier = Modifier
                            .padding(
                                horizontal = 20.dp,
                                vertical = 20.dp
                            )
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        elevation = ButtonDefaults.buttonElevation(5.dp),
                    ) {
                        Text(
                            text = "User Role", style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                                , color = Color.Black
                            )
                        )

                    }


            }
        }





                )



}
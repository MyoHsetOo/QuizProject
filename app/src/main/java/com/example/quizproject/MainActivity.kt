package com.example.quizproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizproject.adminScreens.AdminQuestionList
import com.example.quizproject.adminScreens.AdminQuestionScreen
import com.example.quizproject.adminScreens.BookListAdminScreen
import com.example.quizproject.adminScreens.CourseListAdminScreen
import com.example.quizproject.adminScreens.QuestionEntryForm
import com.example.quizproject.navigation.Nav
import com.example.quizproject.ui.theme.QuizProjectThem
import com.example.quizproject.userScreens.AdminUserConnectedScreen
import com.example.quizproject.userScreens.CourseListScreen
import com.example.quizproject.userScreens.FavoriteScreen
import com.example.quizproject.userScreens.HomeScreen


//
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizProjectThem {
                // A surface container using the 'background' color from the theme
               Nav()
            }
        }
    }
}

//Card
/*Column(

modifier = Modifier.fillMaxSize(),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(30.dp)
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Brush.linearGradient(
                        colors = listOf(

//                                            MaterialTheme.colorScheme.primary,
//
//                                            Color(0xFFE2EAF7),
                            Color(0xFFC3D0E6),
                            //  MaterialTheme.colorScheme.primary,


                            MaterialTheme.colorScheme.primary,


                            ),
                        start = Offset.Zero, // Starting point of the gradient
                        end = Offset.Infinite, // Ending point of the gradient
                        tileMode = TileMode.Clamp // Tile mode for extending the gradient
                    )
                )
        ){
            Column {

                Text(text = "Information Technology Professionals Examination Council ( ITPEC )", style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding( top = 40.dp, bottom = 40.dp, start = 20.dp, ))

                Button(onClick = {

                },
                    modifier = Modifier.padding( top = 20.dp, bottom = 10.dp, start = 20.dp,),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(text = "View" , )
                }

            }

        }

    }


}*/



/*@Preview
@Composable
fun getCustomGradient(): Brush {
    return Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary),
        start = Offset.Zero, // Starting point of the gradient
        end = Offset.Infinite, // Ending point of the gradient
        tileMode = TileMode.Clamp // Tile mode for extending the gradient
    )
}*/
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizProjectThem {

    }
}
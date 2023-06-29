package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.example.quizproject.viewModel.QuestionScreenViewModel
import com.example.quizproject.viewModel.QuestionSetViewModel

import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId

//
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//////
fun QuestionScreen(navController: NavController , id : String? , number : String?) {

    val scaffoldState = rememberBottomSheetScaffoldState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var isOnClick by remember {
        mutableStateOf(false)
    }

    var repository = MongoRepositoryImpl()

    var viewModelQuestionScreen : QuestionScreenViewModel = QuestionScreenViewModel(repository)

    var questionScreenData = viewModelQuestionScreen._questionScreenData

    var viewModel : QuestionSetViewModel = QuestionSetViewModel( repository )

    var obj : BsonObjectId? = id?.let { BsonObjectId(it) }


    BottomSheetScaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.secondary),
        containerColor = MaterialTheme.colorScheme.secondary,
        sheetContainerColor = MaterialTheme.colorScheme.primary,
        sheetContentColor = Color.Black,
        sheetShadowElevation = 10.dp,
        sheetShape = RoundedCornerShape(40.dp),
        sheetContent = {

            for (item in questionScreenData.value) {

                if (item._id == obj) {

                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.primary),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Column() {
                            Text(text = "Solution",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                ))

                            Box(modifier = Modifier.width(50.dp)){
                                Text(text = "Solution",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    ))
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 80.dp,
        topBar = {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
            ) {

                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "back",
                            tint =MaterialTheme.colorScheme.onPrimary)
                    }
                    Text(text = "$number",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { },
                    horizontalArrangement = Arrangement.End,
                ) {

                    /*IconButton(onClick = {  }) {

                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "back", tint = MaterialTheme.colorScheme.onPrimary)
                    }*/

                    FavoriteButton()

                }
            }
                 },
    ) { innerPadding ->

        Column (

            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            ) {

            for (item in questionScreenData.value) {

                if (item._id == obj){

                    Text(
                        text = item.questionText,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            lineHeight = 25.sp
                        ),
                        modifier = Modifier.padding(5.dp),

                        )


                    for (answerItem in item.answers){

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
                            Row (
                                modifier = Modifier.padding( start = 10.dp )
                            ){

                                Box (
                                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 10.dp)

                                ){
                                    Text(
                                        text = answerItem.answerOption.toString(),
                                        style = TextStyle(
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            lineHeight = 25.sp)
                                    )
                                }



                                Box(
                                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                                ) {
                                    Text(
                                        text = answerItem.answerText,
                                        style = TextStyle(
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            lineHeight = 25.sp)
                                    )

                                }

                            }


                        }

                    }



                }



            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){

                Button(modifier = Modifier.padding(top=30.dp),onClick = { /*TODO*/ },
                ) {
                    Text(text = "Submit",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }
        }
    }
}



@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}


















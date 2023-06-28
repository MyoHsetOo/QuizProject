package com.example.quizproject.adminScreens

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.example.quizproject.viewModel.AnswerViewModel
import com.example.quizproject.viewModel.ChapterViewModel
import com.example.quizproject.viewModel.QuestionSetViewModel
import org.mongodb.kbson.BsonObjectId

//
//
//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionEntryForm ( navController: NavController, id : String? ) {

    var imageUriQuestion by remember {
        mutableStateOf<Uri?>(null)
    }

    var imageUriAnswer by remember {
        mutableStateOf<Uri?>(null)
    }

    var imageUriSolution by remember {
        mutableStateOf<Uri?>(null)
    }

    var context = LocalContext.current


    val bitmapQuestion = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val bitmapAnswer = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val bitmapSolution = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcherQuestion =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUriQuestion = uri
        }

    val launcherAnswer =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUriAnswer = uri

        }

    val launcherSolution =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUriSolution = uri
        }


    var dateTextField = remember {
        mutableStateOf("")
    }

    var questionField = remember {
        mutableStateOf("")
    }

    var answerTextField = remember {
        mutableStateOf("")
    }

    var solutionTextField = remember {
        mutableStateOf("")
    }

    var isShowImage = remember {
        mutableStateOf(false)
    }

    var addingCount by remember {
        mutableStateOf(0)
    }

    var alphabet by remember {
        mutableStateOf('A')
    }

    var isAddingAnswer by remember {
        mutableStateOf(false)
    }

    var isAttachedQuestionClickable by remember {
        mutableStateOf(false)
    }







    val optionQuestion = listOf("Text Only", "Text and Image", "Image Only",)

    val optionsAnswer = listOf("Text Only", "Text and Image", "Image Only",)

    val optionsSolution = listOf("Text Only", "Text and Image", "Image Only")

    var expandedQuestion by remember { mutableStateOf(false) }

    var expandedAnswer by remember { mutableStateOf(false) }

    var expandedSolution by remember { mutableStateOf(false) }

    var selectedQuestionText by remember { mutableStateOf(optionQuestion[0]) }

    var selectedAnswerText by remember { mutableStateOf(optionsAnswer[0]) }

    var selectedSolutionText by remember { mutableStateOf(optionsSolution[0]) }

    var repository = MongoRepositoryImpl()

    var obj : BsonObjectId? = id?.let { BsonObjectId(it) }


    var viewModelAnswer : AnswerViewModel = AnswerViewModel( repository )

    var answerType = viewModelAnswer._answerType

    var answerText = viewModelAnswer._answerText

    var answerImage = viewModelAnswer._answerImage

    var answerOption = viewModelAnswer._answerOption

    var answerData = viewModelAnswer._answerData

    var viewModelChapter : ChapterViewModel = ChapterViewModel( repository )




    var viewModelQuestionSet : QuestionSetViewModel = QuestionSetViewModel( repository )

    var questionSetData = viewModelQuestionSet._questionSetData

    var questionNo = viewModelQuestionSet._questionNo

    var questionSetType = viewModelQuestionSet._questionType

    var questionSetText = viewModelQuestionSet._questionText

    var questionSetImage = viewModelQuestionSet._questionImage

    var correctQuestionType = viewModelQuestionSet._correctAnswer

    var solutionType = viewModelQuestionSet._solutionType

    var solutionText = viewModelQuestionSet._solutionText

    var solutionImage = viewModelQuestionSet._solutionImage

    var uriQuestion = viewModelQuestionSet._uriQuestion

    var uriAnswer = viewModelAnswer._uriAnswer

    var uriSolution = viewModelQuestionSet._uriSolution




    var answerListData = remember {
        mutableStateListOf<AnswerModel>()
    }

    val questionPhotoPick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriQuestion.value = it }
    )



    val answerPhotoPick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriAnswer.value = it }
    )



    val solutionPhotoPick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriSolution.value = it }
    )



    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ){

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .fillMaxHeight()

        ) {
            item {

                Column {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 10.dp)


                    ){
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back" ,
                                tint = MaterialTheme.colorScheme.onPrimary,)

                        }

                        Text(text = "Data Entry Form", style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,

                            ), modifier = Modifier.padding(start = 8.dp))
                    }

                    Text(
                        text = "Question No.", style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        ), modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    OutlinedTextField(
                        value = questionNo.value,
                        enabled = true,
                        onValueChange = { questionNo.value = it },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.secondary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            cursorColor = MaterialTheme.colorScheme.onPrimary,
                        )


                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Question Type", style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary,

                            ), modifier = Modifier.padding(horizontal = 15.dp)
                    )


                    ExposedDropdownMenuBox(
                        expanded = expandedQuestion,
                        onExpandedChange = { expandedQuestion = !expandedQuestion },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .background(MaterialTheme.colorScheme.secondary)

                    ) {
                        OutlinedTextField(
                            // The `menuAnchor` modifier must be passed to the text field for correctness.
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            value = questionSetType.value,
                            onValueChange = {},
                            shape = RoundedCornerShape(20.dp),
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),


                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedQuestion) },
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                selectionColors = TextSelectionColors(
                                    handleColor = MaterialTheme.colorScheme.onPrimary,
                                    backgroundColor = MaterialTheme.colorScheme.secondary
                                )

                            ),
                        )

                        ExposedDropdownMenu(
                            expanded = expandedQuestion,
                            onDismissRequest = { expandedQuestion = false },
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
                        ) {
                            optionQuestion.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        questionSetType.value = selectionOption
                                        expandedQuestion = false
                                    },
                                    modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    colors = MenuDefaults.itemColors(
                                        textColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                )
                            }
                        }
                    }

                    if (questionSetType.equals("Text Only")) {

                        OutlinedTextField(
                            value = questionSetText.value,
                            enabled = true,
                            onValueChange = { questionSetText.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            shape = RoundedCornerShape(20.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                cursorColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )

                    } else {
                        OutlinedTextField(
                            value = questionSetText.value,
                            enabled = true,
                            onValueChange = { questionSetText.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            shape = RoundedCornerShape(20.dp),
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.AttachFile,
                                    contentDescription = "Attached",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.clickable {

                                        isAttachedQuestionClickable = true
                                        questionPhotoPick.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                        //launcherQuestion.launch("image/*")
                                    }
                                )
                            },

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                cursorColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    }

                    //QuestiohnImage


                    OutlinedCard (
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier.padding(vertical = 10.dp)

                    ){
                        AsyncImage(
                            model = uriQuestion.value,
                            contentDescription = null,
                        )
                    }



                    //Answer Type
                    Text(
                        text = "Answer Type", style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        ), modifier = Modifier.padding(horizontal = 15.dp)
                    )
/////

                    ExposedDropdownMenuBox(
                        expanded = expandedAnswer,
                        onExpandedChange = { expandedAnswer = !expandedAnswer },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .clickable {

                            }
                    ) {
                        OutlinedTextField(
                            // The `menuAnchor` modifier must be passed to the text field for correctness.
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            value = answerType.value,
                            onValueChange = {},
                            shape = RoundedCornerShape(20.dp),
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedAnswer) },
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                selectionColors = TextSelectionColors(
                                    handleColor = MaterialTheme.colorScheme.onPrimary,
                                    backgroundColor = MaterialTheme.colorScheme.secondary
                                )

                            ),
                        )
                        ExposedDropdownMenu(
                            expanded = expandedAnswer,
                            onDismissRequest = { expandedAnswer = false },
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
                        ) {
                            optionsAnswer.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        answerType.value = selectionOption
                                        expandedAnswer = false
                                    },
                                    modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    colors = MenuDefaults.itemColors(
                                        textColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                )
                            }
                        }
                    }

                    // A Answer Card  jjjjjjj

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Text(text = "${alphabet}")


                        if (answerType.equals("Text Only")) {

                            OutlinedTextField(
                                value = answerText.value,
                                enabled = true,
                                onValueChange = { answerText.value = it },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(20.dp),

                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                                )
                            )

                        } else {

                            OutlinedTextField(
                                value = answerText.value,
                                enabled = true,
                                onValueChange = { answerText.value = it },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(20.dp),
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.AttachFile,
                                        contentDescription = "Attached",
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.clickable {
                                            answerPhotoPick.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                            //launcherAnswer.launch("image/*")
                                        }
                                    )
                                },


                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                                )
                            )

                        }
                    }

                    OutlinedCard (
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier.padding(vertical = 10.dp)

                    ){
                        AsyncImage(
                            model = uriAnswer,
                            contentDescription = null,
                        )
                    }


                    //Add Answer Button
                    Button(
                        onClick = {

                           answerOption.value++

                            viewModelAnswer.insertAnswer()


                            var ans = AnswerModel(
                                answerOption = answerOption.value,
                                answerType = answerType.value,
                                answerText = answerText.value,
                                answerImage = uriAnswer.value.toString(),
                            )

                            answerListData.add(ans)



                            alphabet++





                           /* var ans = Answer(
                                answerType = alphabet,
                                answerText = answerTextField.value,
                                answerImage = uriAnswer.toString(),
                            )
                            answerlist.add(ans)

                            isAddingAnswer != isAddingAnswer
                            addingCount++
                            alphabet++

                            Log.d("Count>>>", "${addingCount}")
                            Log.d("Alphabet>>>", "$alphabet")*/
                            //answerText.value = ""
                          //  uriAnswer = null
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                            .height(60.dp),
                        //border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Add Answer", style = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        )

                    }

                }

            }
            items( answerListData ) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Box() {
                            Text(
                                text = "${item.answerOption}", style = TextStyle(
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Column(

                        ) {
                            Text(
                                text = "${item.answerText}", style = TextStyle(
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            )

                            var img = item.answerImage.toUri()
                            OutlinedCard (
                                shape = RoundedCornerShape(20.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                modifier = Modifier.padding(vertical = 10.dp)

                            ){
                                AsyncImage(
                                    model = img,
                                    contentDescription = null,
                                )
                            }
                        }
                    }

                }

            }
            item {

                Column {

                    Text(
                        text = "Enter Correct Answer", style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        ), modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    OutlinedTextField(
                        value = correctQuestionType.value,
                        enabled = true,
                        onValueChange = { correctQuestionType.value = it },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.secondary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            cursorColor = MaterialTheme.colorScheme.onPrimary,
                        )


                    )
                    Spacer(modifier = Modifier.height(5.dp))


                    Text(
                        text = "Solution Type", style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        ), modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    ExposedDropdownMenuBox(
                        expanded = expandedSolution,
                        onExpandedChange = { expandedSolution = !expandedSolution },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)

                    ) {
                        OutlinedTextField(
                            // The `menuAnchor` modifier must be passed to the text field for correctness.
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            value = solutionType.value,
                            onValueChange = {},
                            shape = RoundedCornerShape(20.dp),
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSolution) },
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor =MaterialTheme.colorScheme.onPrimary,
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                selectionColors = TextSelectionColors(
                                    handleColor = MaterialTheme.colorScheme.onPrimary,
                                    backgroundColor = MaterialTheme.colorScheme.secondary
                                )

                            ),
                        )

                        ExposedDropdownMenu(
                            expanded = expandedSolution,
                            onDismissRequest = { expandedSolution = false },
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
                        ) {
                            optionsSolution.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption ) },
                                    onClick = {
                                        solutionType.value = selectionOption
                                        expandedSolution = false
                                    },
                                    modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    colors = MenuDefaults.itemColors(
                                        textColor = MaterialTheme.colorScheme.onPrimary,
                                    )

                                )
                            }
                        }
                    }

                    if (solutionType.equals("Text Only")) {

                        OutlinedTextField(
                            value = solutionText.value,
                            enabled = true,
                            onValueChange = { solutionText.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            shape = RoundedCornerShape(20.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                cursorColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )

                    } else {
                        OutlinedTextField(
                            value = solutionText.value,
                            enabled = true,
                            onValueChange = { solutionText.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            shape = RoundedCornerShape(20.dp),
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.AttachFile,
                                    contentDescription = "Attached",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.clickable {
                                        solutionPhotoPick.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                        //launcherSolution.launch("image/*")
                                    }
                                )
                            },

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                cursorColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    }

                    // Solution Image
                    OutlinedCard (
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier.padding(vertical = 10.dp)

                    ){
                        AsyncImage(
                            model = uriSolution.value,
                            contentDescription = null,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,

                        ) {


                        Button(
                            onClick = {


                               // viewModelQuestionSet.insertQuestionSet( answerListData )


                                viewModelChapter.updateChapter(
                                    obj!! ,
                                    questionNo.value,
                                    questionSetType.value,
                                    questionSetText.value ,
                                    uriQuestion.value.toString(),
                                    answerListData ,
                                    correctQuestionType.value,
                                    solutionType.value,
                                    solutionText.value,
                                    uriSolution.value.toString() )



                                Log.d("QuestionSetList>>>>>>","${questionSetData.value.size}")
                                navController.navigate("AdminQuestionList/${id}")
                            },
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "Save", style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ), modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                            )
                        }
                    }

                }
            }
        }

    }

}







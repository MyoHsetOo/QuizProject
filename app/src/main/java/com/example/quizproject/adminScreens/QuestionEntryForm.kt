package com.example.quizproject.adminScreens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.quizproject.dataModel.Answer
import java.util.function.IntConsumer
//
//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionEntryForm ( navController: NavController ) {

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

    var uriQuestion by remember {
        mutableStateOf<Uri?>(null)
    }

    val questionPhotoPick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriQuestion = it }
    )

    var uriAnswer by remember {
        mutableStateOf<Uri?>(null)
    }

    val answerPhotoPick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriAnswer = it }
    )

    var uriSolution by remember {
        mutableStateOf<Uri?>(null)
    }

    val solutionPhotoPick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriSolution = it }
    )

    val optionQuestion = listOf("Text Only", "Text and Image", "Image Only",)

    val optionsAnswer = listOf("Text Only", "Text and Image", "Image Only",)

    val optionsSolution = listOf("Text Only", "Text and Image", "Image Only")

    var expandedQuestion by remember { mutableStateOf(false) }

    var expandedAnswer by remember { mutableStateOf(false) }

    var expandedSolution by remember { mutableStateOf(false) }

    var selectedQuestionText by remember { mutableStateOf(optionQuestion[0]) }

    var selectedAnswerText by remember { mutableStateOf(optionsAnswer[0]) }

    var selectedSolutionText by remember { mutableStateOf(optionsSolution[0]) }

    var answerlist = remember {
        mutableStateListOf<Answer>()
    }

    Column (
        modifier = Modifier.fillMaxSize()
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
                        value = dateTextField.value,
                        enabled = true,
                        onValueChange = { dateTextField.value = it },
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
                            value = selectedQuestionText,
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
                                        selectedQuestionText = selectionOption
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

                    if (selectedQuestionText.equals("Text Only")) {

                        OutlinedTextField(
                            value = questionField.value,
                            enabled = true,
                            onValueChange = { questionField.value = it },
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
                            value = questionField.value,
                            enabled = true,
                            onValueChange = { questionField.value = it },
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
                            model = uriQuestion,
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
                    ) {
                        OutlinedTextField(
                            // The `menuAnchor` modifier must be passed to the text field for correctness.
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            value = selectedAnswerText,
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
                                        selectedAnswerText = selectionOption
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
                        Text(text = "$alphabet")

                        if (selectedAnswerText.equals("Text Only")) {

                            OutlinedTextField(
                                value = answerTextField.value,
                                enabled = true,
                                onValueChange = { answerTextField.value = it },
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
                                value = answerTextField.value,
                                enabled = true,
                                onValueChange = { answerTextField.value = it },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(20.dp),
                                trailingIcon = {
                                    Icon(imageVector = Icons.Default.AttachFile,
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

                            var ans = Answer(
                                answerType = alphabet,
                                answerText = answerTextField.value,
                                answerImage = uriAnswer.toString(),
                            )
                            answerlist.add(ans)

                            isAddingAnswer != isAddingAnswer
                            addingCount++
                            alphabet++

                            Log.d("Count>>>", "${addingCount}")
                            Log.d("Alphabet>>>", "$alphabet")
                            answerTextField.value = ""
                            uriAnswer = null
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                            .height(60.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
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
            items(answerlist) { item ->

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
                                text = "${item.answerType}", style = TextStyle(
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
                            value = selectedSolutionText,
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
                                        selectedSolutionText = selectionOption
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

                    if (selectedSolutionText.equals("Text Only")) {

                        OutlinedTextField(
                            value = solutionTextField.value,
                            enabled = true,
                            onValueChange = { solutionTextField.value = it },
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
                            value = solutionTextField.value,
                            enabled = true,
                            onValueChange = { solutionTextField.value = it },
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
                            model = uriSolution,
                            contentDescription = null,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,

                        ) {


                        Button(
                            onClick = {  },
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






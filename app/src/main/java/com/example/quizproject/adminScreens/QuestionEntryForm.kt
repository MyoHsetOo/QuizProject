package com.example.quizproject.adminScreens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Architecture
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.function.IntConsumer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionEntryForm () {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }


    var context = LocalContext.current


    val bitmap  = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){ uri: Uri? ->
        imageUri  = uri
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

    var isAddingAnswerTextField = remember {
        mutableStateOf(false)
    }

    var addingCount = 0;

    val optionQuestion = listOf("Text Only", "Text and Image", "Image Only",)
    val optionsAnswer = listOf("Text Only", "Text and Image", "Image Only",)
    var expandedQuestion by remember { mutableStateOf(false) }
    var expandedAnswer by remember { mutableStateOf(false) }
    var selectedQuestionText by remember { mutableStateOf(optionQuestion[0]) }
    var selectedAnswerText by remember { mutableStateOf(optionsAnswer[0]) }



        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())

        ) {
            Text(
                text = "Question No.", style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Black
                ), modifier = Modifier.padding(horizontal = 15.dp)
            )
            OutlinedTextField(
                value = dateTextField.value,
                enabled = true,
                onValueChange = { dateTextField.value = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Questhuihyu8yhion Type", style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Black
                ), modifier = Modifier.padding(horizontal = 15.dp)
            )


            ExposedDropdownMenuBox(
                expanded = expandedQuestion,
                onExpandedChange = { expandedQuestion = !expandedQuestion },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
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


                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedQuestion) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black
                    ),
                )

                ExposedDropdownMenu(
                    expanded = expandedQuestion,
                    onDismissRequest = { expandedQuestion = false },
                ) {
                    optionQuestion.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedQuestionText = selectionOption
                                expandedQuestion = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
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
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        textColor = Color.Black
                    )
                )

            } else {
                OutlinedTextField(
                    value = questionField.value,
                    enabled = true,
                    onValueChange = { questionField.value = it },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.AttachFile,
                            contentDescription = "Attached",
                            modifier = Modifier.clickable {
                                launcher.launch("image/*")
                            }
                        )
                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        textColor = Color.Black
                    )
                )

            }

            Box(modifier = Modifier
///////

            ){
                imageUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value = MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }


                    bitmap.value?.let { btn ->

                        Image(
                            bitmap = btn.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()

                                .padding(20.dp)
                                .background(Color.Gray)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Answer Type", style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Black
                ), modifier = Modifier.padding(horizontal = 15.dp)
            )


            ExposedDropdownMenuBox(
                expanded = expandedAnswer,
                onExpandedChange = { expandedAnswer = !expandedAnswer },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
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


                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedAnswer) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black
                    ),
                )
                ExposedDropdownMenu(
                    expanded = expandedAnswer,
                    onDismissRequest = { expandedAnswer = false },
                ) {
                    optionsAnswer.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedAnswerText = selectionOption
                                expandedAnswer = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

            Text(
                text = "Answer", style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Black
                ), modifier = Modifier.padding(horizontal = 15.dp)
            )

            Box {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(text = "A")
                    Column(

                    ) {


                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            OutlinedTextField(
                                value = answerTextField.value,
                                enabled = true,
                                onValueChange = { answerTextField.value = it },
                                modifier = Modifier
                                    .padding(8.dp)

                                    .fillMaxWidth(0.85f),
                                shape = RoundedCornerShape(20.dp),

                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = Color.Black,
                                    focusedBorderColor = Color.Black,
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    textColor = Color.Black
                                )
                            )

                            OutlinedCard(
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(start = 10.dp)
                                    .clickable {

                                               isAddingAnswerTextField.value = true

                                    },
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.outlinedCardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary

                                )
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "menu",

                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                if ( isAddingAnswerTextField.value ){

                    isAddingAnswerTextField.value = false



                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text(text = "A")
                        Column(

                        ) {


                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                OutlinedTextField(
                                    value = answerTextField.value,
                                    enabled = true,
                                    onValueChange = { answerTextField.value = it },
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(0.85f),
                                    shape = RoundedCornerShape(20.dp),

                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        unfocusedBorderColor = Color.Black,
                                        focusedBorderColor = Color.Black,
                                        containerColor = MaterialTheme.colorScheme.secondary,
                                        textColor = Color.Black
                                    )
                                )

                                OutlinedCard(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(start = 10.dp)
                                        .clickable {

                                            //isAddingAnswerTextField.value = true

                                        },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = CardDefaults.outlinedCardColors(
                                        containerColor = MaterialTheme.colorScheme.secondary

                                    )
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight()
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "menu",

                                            modifier = Modifier.size(25.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                }



            }










        }

   // }


}


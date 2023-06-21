package com.example.quizproject.userScreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizproject.R
import com.example.quizproject.Visibility
import com.example.quizproject.VisibilityOff
//import com.example.quizproject.login.GradientButton
import com.example.quizproject.login.SimpleOutlinedPasswordTextField
import com.example.quizproject.login.SimpleOutlinedTextFieldSample
import com.example.quizproject.viewModel.LoginAction
import com.example.quizproject.viewModel.LoginViewModel
//
private const val USABLE_WIDTH = 0.8F

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun LoginScaffold(loginViewModel: LoginViewModel) {
    Scaffold(
        content = {

            var  text = remember {
                mutableStateOf("")
            }

            var psw = remember {
                mutableStateOf("")
            }

            val keyboardController = LocalSoftwareKeyboardController.current


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                    )
            ) {


                Box(
                    modifier = Modifier
                        /*.background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                        )*/
                        .align(Alignment.BottomCenter),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.gear),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),

                        )
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                        ,

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var context = LocalContext.current

                        //.........................Spacer
                        Spacer(modifier = Modifier.height(80.dp))

                        //.........................Text: title
                        Text(
                            text = "Sign In",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 130.dp, bottom = 20.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        //SimpleOutlinedTextFieldSample()

                        OutlinedTextField(

                            enabled = loginViewModel.state.value.enabled,
                            value = loginViewModel.state.value.email,
                            onValueChange = { loginViewModel.setEmail(it)  },
                            shape = RoundedCornerShape(topEnd =12.dp, bottomStart =12.dp),
                            label = {
                                Text(
                                    stringResource(R.string.prompt_email),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.labelMedium,
                                ) },
                            //placeholder = { Text(text = "Name or Email Address") },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Email
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                cursorColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                    // do something here
                                }
                            )

                        )

                        Spacer(modifier = Modifier.padding(3.dp))
                        //SimpleOutlinedPasswordTextField()

                        var passwordHidden by rememberSaveable { mutableStateOf(true) }
                        OutlinedTextField(

                            enabled = loginViewModel.state.value.enabled,
                            value = loginViewModel.state.value.password,
                            onValueChange = { loginViewModel.setPassword( it ) },
                            shape = RoundedCornerShape(topEnd =12.dp, bottomStart =12.dp),
                            label = {
                                Text(
                                    stringResource( R.string.prompt_password ),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.labelMedium,
                                ) },
                            visualTransformation =
                            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                            //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,

                                cursorColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary),
                            trailingIcon = {
                                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                                    val visibilityIcon =
                                        if (passwordHidden) Visibility else VisibilityOff
                                    // Please provide localized description for accessibility services
                                    val description = if (passwordHidden) "Show password" else "Hide password"
                                    Icon(imageVector = visibilityIcon, contentDescription = description)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(0.8f),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                    // do something here
                                }
                            )
                        )



                        val gradientColor = listOf( MaterialTheme.colorScheme.primary,MaterialTheme.colorScheme.primary)
                        val cornerRadius = 16.dp


                        Spacer(modifier = Modifier.padding(10.dp))


                        androidx.compose.material3.Button(

                            enabled = loginViewModel.state.value.enabled,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, end = 32.dp),
                            onClick = {

                                val state = loginViewModel.state.value
                                when (state.action) {
                                    LoginAction.LOGIN -> loginViewModel.login(
                                        state.email,
                                        state.password
                                    )
                                    LoginAction.CREATE_ACCOUNT -> loginViewModel.createAccount(
                                        state.email,
                                        state.password
                                    )
                                }

                            },

                            contentPadding = PaddingValues(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {

                            val actionText = when (loginViewModel.state.value.action) {
                                LoginAction.CREATE_ACCOUNT -> stringResource(R.string.create_account)
                                LoginAction.LOGIN -> stringResource(R.string.log_in)
                            }


                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                MaterialTheme.colorScheme.primary,
                                                MaterialTheme.colorScheme.primary
                                            )
                                        ),
                                        shape = RoundedCornerShape(
                                            topStart = 30.dp,
                                            bottomEnd = 30.dp
                                        )
                                    )
                                    .clip(RoundedCornerShape(topStart = 20.dp, bottomEnd = 30.dp))
                                    /*.background(
                                        brush = Brush.linearGradient(colors = gradientColors),
                                        shape = RoundedCornerShape(cornerRadius)
                                    )*/
                                    .padding(horizontal = 16.dp, vertical = 18.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$actionText",
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }



                        Spacer(modifier = Modifier.padding(10.dp))


                        TextButton(onClick = {

                            val state = loginViewModel.state.value
                            when (state.action) {
                                LoginAction.LOGIN -> loginViewModel.switchToAction(LoginAction.CREATE_ACCOUNT)
                                LoginAction.CREATE_ACCOUNT -> loginViewModel.switchToAction(LoginAction.LOGIN)
                            }

                        }) {
                            val actionText = when (loginViewModel.state.value.action) {
                                LoginAction.CREATE_ACCOUNT -> stringResource(R.string.already_have_account)
                                LoginAction.LOGIN -> stringResource(R.string.does_not_have_account)
                            }
                            Text(
                                text = actionText,
                                modifier = Modifier.fillMaxWidth(USABLE_WIDTH),
                                textAlign = TextAlign.Center,
                                color = Blue
                            )
                        }


                        Spacer(modifier = Modifier.padding(5.dp))

                    }


                }

            }


        }
    )
}

/*
Column {
    // Title
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight(0.25f)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }

    // Email, password, login/create account button and switch action
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxHeight(0.75f)
            .fillMaxWidth()
    ) {
        Column {
            // Email field
            TextField(
                enabled = loginViewModel.state.value.enabled,
                modifier = Modifier.fillMaxWidth(USABLE_WIDTH),
                value = loginViewModel.state.value.email,
                maxLines = 2,
                onValueChange = {
                    loginViewModel.setEmail(it)
                },
                label = { Text(stringResource(R.string.prompt_email)) }
            )

            // Password field
            TextField(
                enabled = loginViewModel.state.value.enabled,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(USABLE_WIDTH),
                value = loginViewModel.state.value.password,
                maxLines = 2,
                onValueChange = {
                    loginViewModel.setPassword(it)
                },
                label = { Text(stringResource(R.string.prompt_password)) })

            Spacer(modifier = Modifier.height(40.dp))

            // Login/create account button
            Button(
                enabled = loginViewModel.state.value.enabled,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth(USABLE_WIDTH),
                onClick = {
                    val state = loginViewModel.state.value
                    when (state.action) {
                        LoginAction.LOGIN -> loginViewModel.login(
                            state.email,
                            state.password
                        )
                        LoginAction.CREATE_ACCOUNT -> loginViewModel.createAccount(
                            state.email,
                            state.password
                        )
                    }
                }) {
                val actionText = when (loginViewModel.state.value.action) {
                    LoginAction.CREATE_ACCOUNT -> stringResource(R.string.create_account)
                    LoginAction.LOGIN -> stringResource(R.string.log_in)
                }
                Text(actionText)
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Switch between login and create user
            TextButton(
                onClick = {
                    val state = loginViewModel.state.value
                    when (state.action) {
                        LoginAction.LOGIN -> loginViewModel.switchToAction(LoginAction.CREATE_ACCOUNT)
                        LoginAction.CREATE_ACCOUNT -> loginViewModel.switchToAction(LoginAction.LOGIN)
                    }
                }
            ) {
                val actionText = when (loginViewModel.state.value.action) {
                    LoginAction.CREATE_ACCOUNT -> stringResource(R.string.already_have_account)
                    LoginAction.LOGIN -> stringResource(R.string.does_not_have_account)
                }
                Text(
                    text = actionText,
                    modifier = Modifier.fillMaxWidth(USABLE_WIDTH),
                    textAlign = TextAlign.Center,
                    color = Blue
                )
            }

            // Text with clarification on Atlas Cloud account vs Device Sync account
            Text(
                text = stringResource(R.string.account_clarification),
                modifier = Modifier.fillMaxWidth(USABLE_WIDTH),
                textAlign = TextAlign.Center
            )
        }
    }
}*/

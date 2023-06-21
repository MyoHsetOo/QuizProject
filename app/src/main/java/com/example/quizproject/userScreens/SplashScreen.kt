package com.example.quizproject.userScreens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.R
import kotlinx.coroutines.delay



//




@Composable
fun SplashScreen(navController: NavController) {
//
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 7000
        ), label = ""
    )
    Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

        LaunchedEffect(key1 = true) {
            startAnimation = true
            delay(7000)
            navController.navigate("RegisterPage")
        }
            Splash(alpha = alphaAnim.value)
                Text(text = "Quiz",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontStyle = FontStyle.Italic
                    )
                )

        }

    }
    }

@Composable
fun Splash(alpha: Float) {
    Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.quizz)  )
            LottieAnimation(
                  modifier = Modifier.fillMaxSize(1f),
                iterations = 1000, composition = composition
            )

        }
    }

}
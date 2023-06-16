package com.example.quizproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizproject.navigation.Nav
import com.example.quizproject.ui.theme.QuizProjectThem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SplashWaitTime: Long = 2000

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizProjectThem {
                loadUi()

            }




        }
    }


    @Composable
    fun loadUi() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LaunchedEffect(key1 = true) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(SplashWaitTime)
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            SplashScreen()
        }
    }

    @Composable
    fun SplashScreen() {
//
        var startAnimation by remember { mutableStateOf(false) }
        val alphaAnim = animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(
                durationMillis = 7000
            ), label = ""
        )
        Surface(modifier = Modifier.background(color = Color.White)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                LaunchedEffect(key1 = true) {
                    startAnimation = true
                    delay(7000)

                }
                Splash(alpha = alphaAnim.value)

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

                Column(modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        modifier = Modifier.size(500.dp),
                        iterations = 1000, composition = composition
                    )



                }




            }
        }

    }
}
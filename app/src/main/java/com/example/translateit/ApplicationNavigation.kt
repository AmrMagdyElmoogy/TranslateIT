package com.example.translateit

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.translateit.Presentation.Home
import com.example.translateit.Presentation.HomeViewModel
import com.example.translateit.Presentation.OnBoardingScreenGreetings
import com.example.translateit.Presentation.VoiceSystem
import java.util.Locale

@Composable
fun ApplicationRoot(context: Context) {
    val navController = rememberNavController()
    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    val homeViewModel: HomeViewModel = viewModel()
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    val lifeCycleOwner = LocalLifecycleOwner.current


    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                speechRecognizer.destroy()
            }
        }
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    })
    val stt = TextToSpeech(
        context
    ) {

    }.also {
        it.language = Locale.forLanguageTag("ar-AR")
        it.language = Locale.forLanguageTag("en")
        it.language = Locale.forLanguageTag("fr")
    }

    intent.putExtras()
    NavHost(navController = navController, startDestination = onBoardingScreenRoute) {
        composable(onBoardingScreenRoute) {
            OnBoardingScreenGreetings() {
                navController.navigate(homeRoute)
            }
        }
        composable(homeRoute) {
            Home(
                VoiceSystem(speechRecognizer, intent, stt),
                homeViewModel
            )
        }
    }
}
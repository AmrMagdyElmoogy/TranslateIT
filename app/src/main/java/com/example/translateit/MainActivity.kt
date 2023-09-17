package com.example.translateit

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.translateit.Presentation.Home
import com.example.translateit.Presentation.HomeViewModel
import com.example.translateit.Presentation.OnBoardingScreenGreetings

import com.example.translateit.Presentation.VoiceSystem
import com.example.translateit.ui.theme.TranslateItTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestAudioPermission(this)

        setContent {
            TranslateItTheme {
                ApplicationRoot(LocalContext.current)
            }
        }
    }

}


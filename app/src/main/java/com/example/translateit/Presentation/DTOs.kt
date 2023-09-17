package com.example.translateit.Presentation

import android.content.Intent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech

data class VoiceSystem(
    val speechEngine: SpeechRecognizer,
    val speechEngineIntet: Intent,
    val stt : TextToSpeech,
)

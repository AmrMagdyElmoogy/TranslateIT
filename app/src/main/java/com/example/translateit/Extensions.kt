package com.example.translateit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

fun Context.requestAudioPermission(activity: Activity) {
    if (ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.RECORD_AUDIO
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    android.Manifest.permission.RECORD_AUDIO
                ),
                0
            )
        }
    }
}

fun Intent.putExtras(): Intent {


    this.putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    );
    this.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
    this.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar");

    return this
}

fun ClipboardManager.saveToClipboard(string: AnnotatedString){
    this.setText(annotatedString = string)
}

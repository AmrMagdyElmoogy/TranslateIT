@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.translateit.Presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.translateit.R
import com.example.translateit.ui.theme.TranslateItTheme
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(voiceSystem: VoiceSystem, homeViewModel: HomeViewModel) {
    val sheetState = rememberModalBottomSheetState()
    var micState by remember {
        mutableStateOf(false)
    }
    TranslateItTheme {
        Surface {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = { TopBar() },
                bottomBar = { BottomBar() },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        modifier = Modifier.offset(y = 30.dp),
                        onClick = {
                            micState = !micState
                            startSpeechToText(voiceSystem, homeViewModel, micState)
                        }) {
                        Icon(
                            ImageVector.vectorResource(id = if (micState) R.drawable.mic_off else R.drawable.voice_recorder),
                            modifier = Modifier.size(40.dp),
                            contentDescription = null
                        )
                    }
                }
            ) { paddingValues ->

                if (homeViewModel.sheetBottomSheetVisible) {
                    BottomSheet(sheetState = sheetState, homeViewModel = homeViewModel)
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ChooseLanguagesCard(pd = paddingValues, homeViewModel = homeViewModel)
                    ChooseTypeOfTranslation(homeViewModel = homeViewModel, stt = voiceSystem.stt)
                }
            }
        }
    }

}

fun startSpeechToText(stt: VoiceSystem, homeViewModel: HomeViewModel, micState: Boolean) {
    if (micState) {
        stt.speechEngine.startListening(stt.speechEngineIntet)
    } else {
        stt.speechEngine.stopListening()
    }

    stt.speechEngine.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(bundle: Bundle) {}
        override fun onBeginningOfSpeech() {
        }

        override fun onSegmentResults(segmentResults: Bundle) {

        }

        override fun onRmsChanged(p0: Float) {
            Log.d("OnRmsChanged", p0.toString())
        }

        override fun onBufferReceived(p0: ByteArray?) {

        }

        override fun onEndOfSpeech() {
        }

        override fun onError(p0: Int) {

        }

        override fun onResults(bundle: Bundle) {
            val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            homeViewModel.convertSpeechToTextField(data)
            homeViewModel.translate()
        }

        override fun onPartialResults(p0: Bundle?) {

        }

        override fun onEvent(p0: Int, p1: Bundle?) {
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
    )
}

@Composable
fun BottomBar() {
    val isSelected by remember {
        mutableStateOf(-1)
    }
    NavigationBar {
        NavigationBarItem(
            selected = isSelected == 0,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Rounded.FavoriteBorder, contentDescription = null)
            },
        )

        NavigationBarItem(
            selected = isSelected == 1,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Rounded.Settings, contentDescription = null)
            },
        )
    }
}


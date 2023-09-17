package com.example.translateit.Presentation

import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.example.translateit.R
import com.example.translateit.saveToClipboard
import java.util.Locale

@Composable
fun ChooseTypeOfTranslation(homeViewModel: HomeViewModel, stt: TextToSpeech) {
    val clipboardManager = LocalClipboardManager.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 4,
                value = homeViewModel.speechToTextField,
                onValueChange = { newValue ->
                    homeViewModel.speechToTextField = newValue
                },
                textStyle = MaterialTheme.typography.titleLarge,

                placeholder = {
                    Text("Enter Text", style = MaterialTheme.typography.headlineSmall)
                },
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.copy),
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {

                                clipboardManager.saveToClipboard(AnnotatedString(homeViewModel.speechToTextField))
                            },
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    imeAction = ImeAction.Send,
                ),

                keyboardActions = KeyboardActions(
                    onSend = {
                        homeViewModel.translate()
                    }
                )
            )
            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(Color.Gray),

                thickness = 1.dp
            )

            TextField(
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),

                modifier = Modifier.fillMaxWidth(),
                value = homeViewModel.translatedText,
                onValueChange = { newValue ->
                    homeViewModel.translatedText = newValue
                },
                readOnly = true,
                textStyle = MaterialTheme.typography.titleLarge,
                placeholder = {
                    Text("Translated Result..", style = MaterialTheme.typography.titleLarge)
                },
                trailingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.play),
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                stt.language = Locale.forLanguageTag("ar-AR")
                                val result = stt.speak(
                                    homeViewModel.translatedText,
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                )
                                Log.d("Result of speaking", result.toString())
                            },
                        contentDescription = null
                    )
                },
            )

            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(Color.Gray),

                thickness = 1.dp
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                OurSelection(
                    R.drawable.text,
                    "Text"
                )
                OurSelection(
                    R.drawable.handwriting,
                    "Handwriting"
                )
            }
        }


    }
}


@Composable
private fun OurSelection(@DrawableRes icon: Int, typeOfTranslation: String) {
    Card(
        modifier = Modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )

    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                ImageVector.vectorResource(icon),
                modifier = Modifier.size(40.dp),
                contentDescription = null
            )
            Text(typeOfTranslation, style = MaterialTheme.typography.bodyLarge)
        }
    }
}


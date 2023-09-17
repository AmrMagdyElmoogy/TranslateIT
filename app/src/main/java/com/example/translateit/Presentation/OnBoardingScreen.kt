package com.example.translateit.Presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.translateit.R
import com.example.translateit.dp16
import com.example.translateit.ui.theme.TranslateItTheme


@Composable
fun OnBoardingScreenGreetings(goToHomeScreen: () -> Unit) {

    val composition by
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_lmgqmqln))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    TranslateItTheme {
        Surface(tonalElevation = 15.dp) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(
                            top = dimensionResource(id = R.dimen.dp_20),
                        ),
                    composition = composition,
                    progress = { progress },
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    stringResource(id = R.string.OnBoardingGreetings),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = dp16)
                )



                ElevatedButton(
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    onClick = {
                        goToHomeScreen()
                    }) {
                    Text("Skip", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}




package com.example.translateit.Presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.translateit.R
import com.example.translateit.dp16


val countryFlagsItems = listOf<Country>(
    Country(R.drawable.saudi_arabia, "Arabic","ar"),
    Country(R.drawable.united_kingdom, "English","en"),
    Country(R.drawable.brazil, "Brazil","br"),
    Country(R.drawable.italy, "Italy","it"),
    Country(R.drawable.china, "China","zh-CN"),
    Country(R.drawable.france, "France","fr"),
    Country(R.drawable.germany, "Germany","de"),
)

@Composable
fun ChooseLanguagesCard(pd: PaddingValues, homeViewModel: HomeViewModel) {

    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(horizontal = dp16)
            .clip(RoundedCornerShape(12.dp))
            .padding(pd)


    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CountryFlag(
                flag = homeViewModel.motherLanguage.res,
                country = homeViewModel.motherLanguage.countryName
            ) {
                homeViewModel.toogleBottomSheet()
                homeViewModel.choosenSide = 0
            }
            ElevatedButton(
                modifier = Modifier.clip(CircleShape),
                onClick = {
                    homeViewModel.exchange()
                }) {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.exchange),
                    modifier = Modifier.size(40.dp),
                    contentDescription = null
                )
            }
            CountryFlag(
                flag = homeViewModel.otherLanguage.res,
                country = homeViewModel.otherLanguage.countryName
            ) {
                homeViewModel.toogleBottomSheet()
                homeViewModel.choosenSide = 1
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    sheetState: SheetState,
    homeViewModel: HomeViewModel,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            homeViewModel.toogleBottomSheet()
        },
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(6.dp)
        ) {
            items(countryFlagsItems) {
                CountryFlag(flag = it.res, country = it.countryName) {
                    homeViewModel.updateLanguageFlag(it.res, it.countryName,it.countryCode)
                    homeViewModel.toogleBottomSheet()
                }
            }
        }
    }
}

@Composable
private fun CountryFlag(
    @DrawableRes flag: Int,
    country: String,
    onChooseDifferentLanguage: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = flag),
            modifier = Modifier
                .size(70.dp)
                .clip(
                    CircleShape
                )
                .clickable {
                    onChooseDifferentLanguage()
                },
            contentDescription = null,
        )
        Text(country, style = MaterialTheme.typography.titleLarge)
    }
}

data class Country(
    @DrawableRes val res: Int,
    val countryName: String,
    val countryCode : String
)
package com.example.translateit.Presentation

import android.util.Log
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translateit.Data.Result
import com.example.translateit.Data.RetrofitService
import com.example.translateit.Data.TranslatedResultData
import com.example.translateit.Domain.TranslationRepository
import com.example.translateit.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TranslationRepository,
    private val api: RetrofitService
) : ViewModel() {

    private var _motherLanguage by mutableStateOf(
        Country(R.drawable.saudi_arabia, "Arabic", "ar")
    )


    val motherLanguage: Country
        get() = _motherLanguage

    private var _otherLanguage by mutableStateOf(
        Country(R.drawable.united_kingdom, "English", "en")
    )

    val otherLanguage: Country
        get() = _otherLanguage

    var choosenSide by mutableStateOf(0)

    var speechToTextField by mutableStateOf("")

    var translatedText by mutableStateOf("Translated Result..")

    var sheetBottomSheetVisible by mutableStateOf(false)

    fun convertSpeechToTextField(data: ArrayList<String>?) {
        data?.get(0)?.let {
            speechToTextField = it
        }
    }


    fun updateLanguageFlag(res: Int, countryName: String, countryCode: String) {
        when (choosenSide) {
            0 -> {
                _motherLanguage = _motherLanguage.copy(
                    res = res,
                    countryName = countryName,
                    countryCode = countryCode,
                )
            }

            1 -> {
                _otherLanguage = _otherLanguage.copy(
                    res = res,
                    countryName = countryName,
                    countryCode = countryCode,
                )
            }
        }
    }


    fun toogleBottomSheet() {
        sheetBottomSheetVisible = !sheetBottomSheetVisible
    }

    fun translate() {
        viewModelScope.launch {
            when (val translatedApi = repo.getNewTranslate(
                speechToTextField,
                _motherLanguage.countryCode,
                _otherLanguage.countryCode
            )) {
                is Result.Failure -> Log.d("Errorrrrrrrrrrrrrrrr", translatedApi.message)
                is Result.Success -> {
                    translatedText = translatedApi.data.translatedText.translatedText
                    Log.d("Resulttttttttt", translatedText)
                }
            }
        }
    }

    fun exchange() {
        var temp = _motherLanguage
        _motherLanguage = _otherLanguage
        _otherLanguage = temp
    }

}
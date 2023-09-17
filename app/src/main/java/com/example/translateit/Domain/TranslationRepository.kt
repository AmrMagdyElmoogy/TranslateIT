package com.example.translateit.Domain


import android.util.Log
import com.example.translateit.Data.Result
import com.example.translateit.Data.RetrofitService
import com.example.translateit.Data.TranslatedResultData
import javax.inject.Inject

class TranslationRepository @Inject constructor(
    private val api: RetrofitService
) {
    suspend fun getNewTranslate(
        word: String,
        sourceLanguage: String,
        targetLanguage: String
    ): Result<TranslatedResultData> = try {
        val data = api.translate(sourceLanguage, targetLanguage, word)
        println(data)
        Result.Success(data)
    } catch (e: Exception) {
        Log.d("Exception is :", e.stackTrace.toString())
        Result.Failure("Error is $e")
    }
}
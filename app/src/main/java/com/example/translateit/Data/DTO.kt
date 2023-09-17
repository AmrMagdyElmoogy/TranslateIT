package com.example.translateit.Data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslatedResultData(
    @Json(name = "data") val translatedText: TranslatedData,
)


@JsonClass(generateAdapter = true)
data class TranslatedData(
    @Json(name = "translatedText") val translatedText: String,
)

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val message: String) : Result<T>()
}


package com.example.translateit.Data

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


interface RetrofitService {

    @FormUrlEncoded
    @POST("/translate")
    @Headers(
        "content-type: application/x-www-form-urlencoded",
        "X-RapidAPI-Key: 8824ac07ecmshd8ee27b3bc8b203p1435acjsne85d35fbe18b",
        "X-RapidAPI-Host: text-translator2.p.rapidapi.com"
    )


    suspend fun translate(
        @Field("source_language") sourceLanguage: String,
        @Field("target_language") targetLanguage: String,
        @Field("text") word: String
    ): TranslatedResultData
}
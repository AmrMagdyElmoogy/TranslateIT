package com.example.translateit.Data

import com.example.translateit.baseUrl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiProvider {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        val moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}


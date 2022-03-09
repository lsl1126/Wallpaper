package com.hd.wallpaper.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitUtils {

    fun getApiUrl(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pexels.com")
            .client(okHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    private val loggingInterceptor: HttpLoggingInterceptor
        get() {
            val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i("onResponse:", message)
                }
            })
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

}
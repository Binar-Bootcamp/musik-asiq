package com.binaracademy.musikasiq.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {

    private var _retrofit: Retrofit? = null

    val instance: Retrofit by lazy { getRetrofitInstance()!! }

    fun getCustomUrlInstance(baseUrl: String) = getRetrofitInstance(baseUrl)!!

    private val baseUrl = "https://soundcloud-scraper.p.rapidapi.com/"

    private val logging: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val headerInterceptor: Interceptor
        get() {
            return Interceptor { chain ->
                val request = chain.request()
                val headerInterceptedRequest = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("X-RapidAPI-Key", "1e9e41835fmsh833db31d1e8abefp106cc2jsna9e09403ea70")
                    .header("X-RapidAPI-Host", "soundcloud-scraper.p.rapidapi.com")
                    .method(request.method(), request.body())
                    .build()
                chain.proceed(headerInterceptedRequest)
            }
        }

    private val client = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }
        .addInterceptor(logging)
        .addInterceptor(headerInterceptor)
        .build()

    private fun getRetrofitInstance(baseUrl: String = this.baseUrl): Retrofit? {
        if (_retrofit == null) {
            _retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        if (_retrofit?.baseUrl().toString() != baseUrl) {
            _retrofit = _retrofit?.newBuilder()
                ?.baseUrl(baseUrl)
                ?.build()
        }

        return _retrofit
    }
}
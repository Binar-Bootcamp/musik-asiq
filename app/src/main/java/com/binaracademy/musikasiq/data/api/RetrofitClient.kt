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

    private val baseUrl = "https://soundcloud.com"

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

        return _retrofit
    }
}
package com.example.todolist

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val httpLogginInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    val client = OkHttpClient.Builder().addInterceptor(httpLogginInterceptor).build()

    fun getInstance(): Retrofit {
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://todo.paydali.uz").client(client).build()
        return retrofit
    }
}
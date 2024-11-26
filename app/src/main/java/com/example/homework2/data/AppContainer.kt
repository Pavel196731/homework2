package com.example.homework2.data

import BookService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BaseAppContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }
}



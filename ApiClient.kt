package com.example.nngtr

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Replace with your FastAPI server URL (e.g., from Swagger /docs)
    private const val BASE_URL = "https://question-seeker-furthermore-relief.trycloudflare.com/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
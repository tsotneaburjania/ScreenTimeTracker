package com.example.screentimetracker.data

import retrofit2.Call
import retrofit2.http.GET


interface RandomWordAPI {
    @GET("word")
    fun getRandomWord(): Call<List<String>>
}
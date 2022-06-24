package com.example.screentimetracker.data

import com.example.screentimetracker.data.models.WordResponseModel
import retrofit2.Call
import retrofit2.http.*

interface DictionaryAPI {
    @GET("v2/entries/en/{word}")
    fun getWordOfTheDay(@Path("word") word: String?): Call<List<WordResponseModel>>
}
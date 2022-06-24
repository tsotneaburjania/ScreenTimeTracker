package com.example.screentimetracker.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.screentimetracker.R
import com.example.screentimetracker.data.models.DefinitionModel
import com.example.screentimetracker.data.DictionaryAPI
import com.example.screentimetracker.data.RandomWordAPI
import com.example.screentimetracker.data.models.MeaningModel
import com.example.screentimetracker.data.models.WordResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FragmentTwo : Fragment(R.layout.fragment_two)  {
    companion object {
        var randomWord: String = ""
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWordOfTheDay(view)
    }

    private fun getRandomWord(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://random-word-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val randomWordAPI: RandomWordAPI = retrofit.create(RandomWordAPI::class.java)

        val callRandomWord: Call<List<String>> = randomWordAPI.getRandomWord()
        callRandomWord.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (!response.isSuccessful) {
                    println(response.code())
                    return
                }
                randomWord = response.body()?.get(0).toString()
                println("RANDOMWORD $randomWord")
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("ERROR OCCURRED WHILE FETCHING RANDOM WORD.")
                println(t.localizedMessage)
            }

        })
    }

    private fun getWordOfTheDay(view: View){
        val wordTv: TextView = view.findViewById(R.id.wordTv)
        val meaningsTv: TextView = view.findViewById(R.id.meaningsTv)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val dictionaryAPI: DictionaryAPI = retrofit.create(DictionaryAPI::class.java)

        val call: Call<List<WordResponseModel>> = dictionaryAPI.getWordOfTheDay(randomWord)
        call.enqueue(object : Callback<List<WordResponseModel>> {
            override fun onFailure(call: Call<List<WordResponseModel>>, t: Throwable) {
                println("ERROR OCCURRED WHILE FETCHING DATA.")
                println(t.localizedMessage)
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<WordResponseModel>>, response: Response<List<WordResponseModel>>) {
                if (response.code() == 404) {
                    println("ERROR: RETRYING")
                    getRandomWord()
                    getWordOfTheDay(view)
                    return
                }
                println(response.body())
                val words: List<WordResponseModel>? = response.body()
                val word = words?.get(0)
                if (word != null) {
                    wordTv.text = "Word: " + word.word
                }

                var meaningsText = "Meanings: "
                if (word != null) {
                    for (meaning: MeaningModel in word.meanings){
                        meaningsText += "\n1) " + meaning.partOfSpeech
                        for (definition: DefinitionModel in meaning.definitions){
                            meaningsText += "\n•Definition: " + definition.definition + "\n•Example: " + definition.example
                        }
                    }
                }

                meaningsTv.text = meaningsText

            }

        })
    }
}
package com.example.screentimetracker.data.models

data class WordResponseModel(
    val word: String,
    val phonetic: String,
    val phonetics: List<PhoneticsModel>,
    val origin: String,
    val meanings: List<MeaningModel>
)

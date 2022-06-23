package com.example.screentimetracker.util

import java.util.concurrent.TimeUnit

class HelperFunctions {
    companion object {
        fun millisToMinutesAndSeconds(millis: Long) : HashMap<String, Long> {
            println("INSIDE TO MINUTES FUNC: $millis")
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)

            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)

            return hashMapOf("minutes" to minutes, "seconds" to seconds)
        }
    }
}
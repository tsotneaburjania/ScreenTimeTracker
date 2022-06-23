package com.example.screentimetracker.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.screentimetracker.MainActivity
import com.example.screentimetracker.data.ScreenTimeRecord
import com.example.screentimetracker.util.HelperFunctions
import java.util.concurrent.Executors


class OnOffReceiver : BroadcastReceiver() {

    private var startTimer: Long = 0
    private var endTimer: Long = 0
    private var screenOnTimeSingle: Long = 0
    private var screenOnTime: Long = 0
    private val TIME_ERROR: Long = 1000

    override fun onReceive(context: Context?, intent: Intent) {
        println("ScreenTimeService onReceive")
        if (intent.action == Intent.ACTION_SCREEN_ON) {
            startTimer = System.currentTimeMillis()
//            println("START: $startTimer")
        } else if (intent.action == Intent.ACTION_SCREEN_OFF) {
//            println("START ON FIRST: $startTimer")
            if (startTimer == 0L){
                startTimer = MainActivity.onCreateMillis
            }
            endTimer = System.currentTimeMillis()
//            println("END: $endTimer")
            screenOnTimeSingle = endTimer - startTimer
            val formattedScreenOnHMap = HelperFunctions.millisToMinutesAndSeconds(screenOnTimeSingle)
            println(
                formattedScreenOnHMap["minutes"].toString() + " Minutes & " + formattedScreenOnHMap["seconds"].toString() + " Seconds"
            )

            val executor = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())
            val db = MainActivity.appDatabase
            val record = ScreenTimeRecord(
                0,
                formattedScreenOnHMap["minutes"],
                formattedScreenOnHMap["seconds"]
            )
            executor.execute {
                db?.recordDao()?.insertAll(record)
                handler.post {
                }
            }
            if (screenOnTimeSingle < TIME_ERROR) {
                screenOnTime += screenOnTime
            }

        }
    }


}
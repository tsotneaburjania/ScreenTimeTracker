package com.example.screentimetracker.broadcastReceivers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.core.app.NotificationCompat
import com.example.screentimetracker.MainActivity
import com.example.screentimetracker.R
import com.example.screentimetracker.activities.NotificationActivity
import com.example.screentimetracker.data.ScreenTimeRecord
import com.example.screentimetracker.util.HelperFunctions
import java.util.concurrent.Executors


class OnOffReceiver : BroadcastReceiver() {

    private var startTimer: Long = 0
    private var endTimer: Long = 0
    private var screenOnTimeSingle: Long = 0
    private var screenOnTime: Long = 0
    private val TIME_ERROR: Long = 1000

    override fun onReceive(context: Context, intent: Intent) {
        println("ScreenTimeService onReceive")
        if (intent.action == Intent.ACTION_SCREEN_ON) {
            startTimer = System.currentTimeMillis()
//            println("START: $startTimer")
        } else if (intent.action == Intent.ACTION_SCREEN_OFF) {
//            println("START ON FIRST: $startTimer")
            if (startTimer == 0L) {
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

            spawnNotification(context, formattedScreenOnHMap["minutes"].toString() + " Minutes & " + formattedScreenOnHMap["seconds"].toString() + " Seconds")

            if (screenOnTimeSingle < TIME_ERROR) {
                screenOnTime += screenOnTime
            }

        }
    }

    private fun spawnNotification(context: Context, message: String) {
        createNotificationChannel(context)
        val channelId = "all_notifications" // Use same Channel ID
        val intent = Intent(context, NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle("Screen time recorded")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_MAX)
        builder.setContentIntent(pendingIntent).setAutoCancel(true)
        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        with(mNotificationManager) {
            notify(123, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "all_notifications"
            val mChannel = NotificationChannel(
                channelId,
                "General Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            mChannel.description = "This is default channel used for all other notifications"

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}
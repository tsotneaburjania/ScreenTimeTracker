package com.example.screentimetracker.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.screentimetracker.R


class NotificationActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        textView = findViewById<View>(R.id.textView) as TextView
        val message = intent.getStringExtra("message")
        textView.text = message
    }
}
package com.example.screentimetracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScreenTimeRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): ScreenTimeRecordDAO
}
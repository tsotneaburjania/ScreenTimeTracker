package com.example.screentimetracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScreenTimeRecordDAO {
    @Query("SELECT * FROM screentimerecord")
    fun getAll(): List<ScreenTimeRecord>

    @Insert
    fun insertAll(vararg record: ScreenTimeRecord)
}
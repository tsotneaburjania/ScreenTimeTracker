package com.example.screentimetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScreenTimeRecord (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "minutes") val minutes: Long?,
    @ColumnInfo(name = "seconds")val seconds: Long?,
//    val timeStampStart: String?,
//    val timeStampEnd: String?
)
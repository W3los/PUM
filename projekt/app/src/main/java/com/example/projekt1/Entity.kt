package com.example.projekt1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gym_records")
data class GymRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exerciseName: String,
    val weight: Float,
    val reps: Int,
    val date: Long = System.currentTimeMillis(),
    val timestamp: Long
)
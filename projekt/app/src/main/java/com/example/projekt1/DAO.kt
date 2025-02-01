package com.example.projekt1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GymRecordDao {
    @Query("SELECT * FROM gym_records")
    fun getAllRecords(): Flow<List<GymRecord>>

    @Query("SELECT * FROM gym_records WHERE id = :id")
    fun getRecordById(id: Int): Flow<GymRecord?>

    @Insert
    suspend fun insert(record: GymRecord)

    @Update
    suspend fun update(record: GymRecord)

    @Delete
    suspend fun delete(record: GymRecord)
}
package com.example.projekt1

import kotlinx.coroutines.flow.Flow

class GymRecordRepository(private val dao: GymRecordDao) {
    val allRecords: Flow<List<GymRecord>> = dao.getAllRecords()

    fun getRecordById(id: Int): Flow<GymRecord?> = dao.getRecordById(id)

    suspend fun addRecord(record: GymRecord) = dao.insert(record)
    suspend fun updateRecord(record: GymRecord) = dao.update(record)
    suspend fun deleteRecord(record: GymRecord) = dao.delete(record)
}
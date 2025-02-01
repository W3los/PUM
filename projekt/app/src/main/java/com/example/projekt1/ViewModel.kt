package com.example.projekt1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

enum class SortOrder { ASC, DESC }

class GymRecordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GymRecordRepository
    private val _sortOrder = MutableStateFlow(SortOrder.DESC)
    private val _currentRecord = MutableStateFlow<GymRecord?>(null)
    private val _minRepsFilter = MutableStateFlow<Int?>(null)

    val currentRecord: StateFlow<GymRecord?> = _currentRecord.asStateFlow()
    val allRecords: Flow<List<GymRecord>>
    val sortOrder: StateFlow<SortOrder> = _sortOrder.asStateFlow()

    init {
        val dao = AppDatabase.getDatabase(application).gymRecordDao()
        repository = GymRecordRepository(dao)

        allRecords = combine(
            repository.allRecords,
            _sortOrder,
            _minRepsFilter
        ) { records, order, minReps ->
            var processedRecords = records

            minReps?.let {
                processedRecords = processedRecords.filter { it.reps >= minReps }
            }


            when (order) {
                SortOrder.ASC -> processedRecords.sortedBy { it.timestamp }
                SortOrder.DESC -> processedRecords.sortedByDescending { it.timestamp }
            }
        }
    }

    fun setMinRepsFilter(minReps: Int?) {
        _minRepsFilter.value = minReps
    }

    fun getRecordById(id: Int): Flow<GymRecord?> {
        return repository.getRecordById(id)
            .onEach { record ->
                _currentRecord.value = record
            }
    }

    fun addRecord(record: GymRecord) = viewModelScope.launch {
        repository.addRecord(record)
    }

    fun updateRecord(record: GymRecord) = viewModelScope.launch {
        repository.updateRecord(record)
    }

    fun deleteRecord(record: GymRecord) = viewModelScope.launch {
        repository.deleteRecord(record)
    }

    fun toggleSortOrder() {
        _sortOrder.value = when (_sortOrder.value) {
            SortOrder.ASC -> SortOrder.DESC
            SortOrder.DESC -> SortOrder.ASC
        }
    }
}
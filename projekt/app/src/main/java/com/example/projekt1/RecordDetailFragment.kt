package com.example.projekt1

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projekt1.databinding.FragmentRecordDetailBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RecordDetailFragment : Fragment() {
    private var _binding: FragmentRecordDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GymRecordViewModel by activityViewModels()
    private var recordId: Int? = null
    private var selectedTimestamp: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recordId = arguments?.getInt("recordId", -1)?.takeIf { it != -1 }

        setupDatePicker()
        setupUI()
        observeCurrentRecord()
    }

    private fun setupUI() {
        binding.btnSave.setOnClickListener {
            saveRecord()
            findNavController().navigateUp()
        }

        binding.btnDelete.setOnClickListener {
            deleteRecord()
            findNavController().navigateUp()
        }

        if (recordId == null) {
            binding.btnDelete.visibility = View.GONE
        }
    }

    private fun observeCurrentRecord() {
        recordId?.let { id ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getRecordById(id).collect { record ->
                    record?.let { populateFields(it) }
                }
            }
        }
    }
    private fun setupDatePicker() {
        binding.dateContainer.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        selectedTimestamp?.let { calendar.timeInMillis = it }

        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val selectedCalendar = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                selectedTimestamp = selectedCalendar.timeInMillis
                binding.tvSelectedDate.text = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    .format(selectedCalendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun populateFields(record: GymRecord) {
        binding.apply {
            exerciseEditText.setText(record.exerciseName)
            weightEditText.setText(record.weight.toString())
            repsEditText.setText(record.reps.toString())
            selectedTimestamp = record.timestamp
            tvSelectedDate.text = if (record.timestamp > 0) {
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(record.timestamp))
            } else {
                getString(R.string.select_date)
            }
        }
    }

    private fun saveRecord() {
        val exercise = binding.exerciseEditText.text.toString()
        val weight = binding.weightEditText.text.toString().toFloatOrNull() ?: 0f
        val reps = binding.repsEditText.text.toString().toIntOrNull() ?: 0
        val timestamp = selectedTimestamp ?: run {
            Toast.makeText(context, "Wybierz datę", Toast.LENGTH_SHORT).show()
            return
        }

        val record = GymRecord(
            id = recordId ?: 0,
            exerciseName = exercise,
            weight = weight,
            reps = reps,
            timestamp = selectedTimestamp ?: System.currentTimeMillis()
        )

        when (recordId) {
            null -> viewModel.addRecord(record)
            else -> viewModel.updateRecord(record)
        }
    }

    private fun deleteRecord() {
        viewModel.currentRecord.value?.let {
            viewModel.deleteRecord(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
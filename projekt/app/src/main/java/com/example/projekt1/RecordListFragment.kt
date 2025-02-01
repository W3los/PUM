package com.example.projekt1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt1.databinding.FragmentRecordListBinding
import com.example.projekt1.databinding.ItemGymRecordBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecordListFragment : Fragment() {
    private var _binding: FragmentRecordListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GymRecordViewModel by activityViewModels()
    private lateinit var adapter: GymRecordAdapter

    private fun setupFilter() {
        binding.etMinReps.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val minReps = s?.toString()?.toIntOrNull()
                viewModel.setMinRepsFilter(minReps)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        setupFilter()
    }

    private fun setupRecyclerView() {
        adapter = GymRecordAdapter { record ->
            val action = RecordListFragmentDirections
                .actionRecordsListFragmentToRecordDetailFragment(record.id)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@RecordListFragment.adapter
        }
    }


    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.allRecords.collect { records ->
                    adapter.submitList(records)
                }


                viewModel.sortOrder.collect { order ->
                    val icon = when (order) {
                        SortOrder.ASC -> R.drawable.ic_sort_asc
                        SortOrder.DESC -> R.drawable.ic_sort_desc
                    }
                    binding.fabSort.setImageResource(icon)
                }
            }
        }
    }
    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_recordsListFragment_to_recordDetailFragment)
        }

        binding.fabSort.setOnClickListener {
            viewModel.toggleSortOrder()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class GymRecordAdapter(
    private val onClick: (GymRecord) -> Unit
) : ListAdapter<GymRecord, GymRecordAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemGymRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: GymRecord) {
            binding.apply {
                exerciseName.text = record.exerciseName
                weightReps.text = "${record.weight} kg x ${record.reps} powtórzeń"

                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                tvDate.text = dateFormat.format(Date(record.timestamp))

                root.setOnClickListener { onClick(record) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGymRecordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<GymRecord>() {
        override fun areItemsTheSame(oldItem: GymRecord, newItem: GymRecord): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GymRecord, newItem: GymRecord): Boolean =
            oldItem == newItem
    }
}
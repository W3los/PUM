package com.example.myapplication.GradeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.TaskRepository
import com.example.myapplication.R

class GradeListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GradeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_grade_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_grades)

        // Przypisanie layout managera
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Ustawienie adaptera z danymi z TaskRepository
        val exerciseLists = TaskRepository.exerciseLists
        adapter = GradeListAdapter(exerciseLists)
        recyclerView.adapter = adapter

        return view
    }
}








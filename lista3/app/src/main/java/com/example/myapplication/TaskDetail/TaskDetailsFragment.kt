package com.example.myapplication.TaskDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.ExerciseList
import com.example.myapplication.R

class TaskDetailsFragment : Fragment() {

    companion object {
        private const val ARG_TASK_LIST = "task_list"

        fun newInstance(exerciseList: ExerciseList): TaskDetailsFragment {
            val fragment = TaskDetailsFragment()
            val args = Bundle()
            args.putSerializable(ARG_TASK_LIST, exerciseList)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvSubjectName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_details, container, false)
        tvSubjectName = view.findViewById(R.id.tv_subject_name)
        recyclerView = view.findViewById(R.id.recycler_view)

        val exerciseList = arguments?.getSerializable(ARG_TASK_LIST) as ExerciseList
        tvSubjectName.text = exerciseList.subject.name
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TaskDetailsAdapter(exerciseList.exercises)

        return view
    }
}


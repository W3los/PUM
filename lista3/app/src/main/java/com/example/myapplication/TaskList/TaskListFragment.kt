package com.example.myapplication.TaskList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.TaskRepository
import com.example.myapplication.R
import com.example.myapplication.TaskDetail.TaskDetailsFragment

class TaskListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)

        // Używamy TaskRepository, aby uzyskać listę ExerciseList
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Przekazujemy do adaptera pełną listę ExerciseList, a nie Exercise
        recyclerView.adapter = TaskListAdapter(TaskRepository.exerciseLists) { selectedList ->
            // Przejście do szczegółów wybranej listy
            val fragment = TaskDetailsFragment.newInstance(selectedList)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}


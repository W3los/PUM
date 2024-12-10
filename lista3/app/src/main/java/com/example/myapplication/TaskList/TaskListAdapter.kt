package com.example.myapplication.TaskList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.ExerciseList
import com.example.myapplication.R

class TaskListAdapter(
    private val exerciseLists: List<ExerciseList>,
    private val onItemClick: (ExerciseList) -> Unit
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_list, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val exerciseList = exerciseLists[position]
        holder.tvSubject.text = exerciseList.subject.name
        holder.tvTasksCount.text = "Liczba zadań: ${exerciseList.exercises.size}"
        holder.tvGrade.text = "Ocena: ${exerciseList.grade}"

        // Ustawienie kliknięcia na listę
        holder.itemView.setOnClickListener {
            onItemClick(exerciseList)
        }
    }

    override fun getItemCount(): Int {
        return exerciseLists.size
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSubject: TextView = view.findViewById(R.id.tv_subject)
        val tvTasksCount: TextView = view.findViewById(R.id.tv_tasks_count)
        val tvGrade: TextView = view.findViewById(R.id.tv_grade)
    }
}


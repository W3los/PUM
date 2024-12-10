package com.example.myapplication.TaskDetail


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Exercise
import com.example.myapplication.R

class TaskDetailsAdapter(
    private val exercises: List<Exercise>
) : RecyclerView.Adapter<TaskDetailsAdapter.TaskDetailsViewHolder>() {

    class TaskDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTaskNumber: TextView = view.findViewById(R.id.tv_task_number)
        val tvTaskContent: TextView = view.findViewById(R.id.tv_task_content)
        val tvTaskPoints: TextView = view.findViewById(R.id.tv_task_points)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task_details, parent, false)
        return TaskDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskDetailsViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.tvTaskNumber.text = "Zadanie ${position + 1}"
        holder.tvTaskContent.text = exercise.content
        holder.tvTaskPoints.text = "Pkt: ${exercise.points}"
    }

    override fun getItemCount(): Int = exercises.size
}

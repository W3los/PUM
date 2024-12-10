package com.example.myapplication.GradeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.ExerciseList
import com.example.myapplication.R

class GradeListAdapter(
    private val exerciseLists: List<ExerciseList> // Lista ExerciseList z TaskRepository
) : RecyclerView.Adapter<GradeListAdapter.GradeViewHolder>() {

    // Grupowanie ExerciseList według samego przedmiotu, ignorując numer listy
    private val groupedBySubject = exerciseLists.groupBy {
        it.subject.name.split(" ")[0] // bierzemy tylko nazwę przedmiotu, ignorując numer listy
    }

    inner class GradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSubjectName: TextView = view.findViewById(R.id.tv_subject_name)
        val tvListCount: TextView = view.findViewById(R.id.tv_list_count)
        val tvAverageGrade: TextView = view.findViewById(R.id.tv_average_grade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grade, parent, false)
        return GradeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        // Pobieramy nazwę przedmiotu i listę list z tego przedmiotu
        val subjectName = groupedBySubject.keys.toList()[position]
        val subjectLists = groupedBySubject[subjectName] ?: emptyList()

        // Zliczamy liczbę list dla danego przedmiotu
        val listCount = subjectLists.size

        // Obliczamy średnią ocenę dla danego przedmiotu
        val averageGrade = if (listCount > 0) {
            val sumOfGrades = subjectLists.sumOf { it.grade }
            sumOfGrades / listCount.toDouble()
        } else {
            0.0
        }

        // Ustawiamy dane w TextView
        holder.tvSubjectName.text = subjectName
        holder.tvListCount.text = "Liczba list: $listCount"
        holder.tvAverageGrade.text = "Średnia ocena: ${"%.1f".format(averageGrade)}"
    }

    override fun getItemCount(): Int = groupedBySubject.size
}









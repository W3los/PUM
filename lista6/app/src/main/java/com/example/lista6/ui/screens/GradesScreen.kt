package com.example.lista6.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lista6.data.ExerciseList

@Composable
fun GradesScreen(exerciseLists: List<ExerciseList>) {
    // Grupowanie przedmiotów i obliczanie średniej ocen
    val subjectGrades = exerciseLists.groupBy { it.subject.name }
        .map { (subject, lists) ->
            subject to lists.map { it.grade }.average() // Obliczanie średniej oceny dla każdego przedmiotu
        }

    // Wyświetlanie listy średnich ocen dla każdego przedmiotu
    LazyColumn(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        items(subjectGrades) { (subject, averageGrade) ->
            Card(

                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = subject, style = MaterialTheme.typography.headlineMedium)
                    Text(text = "Średnia ocena: %.2f".format(averageGrade))
                }
            }
        }
    }
}

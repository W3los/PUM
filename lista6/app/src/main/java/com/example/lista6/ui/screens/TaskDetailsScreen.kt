package com.example.lista6.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lista6.data.ExerciseList


@Composable
fun TaskDetailsScreen(listName: String, exerciseLists: List<ExerciseList>, index: Int) {
    // Pobieramy odpowiednią listę na podstawie indexu
    val exerciseList = exerciseLists.getOrNull(index) ?: return // Jeśli index jest nieprawidłowy, zakończ funkcję

    Column(modifier = Modifier.padding(16.dp)) {
        // Wyświetlanie nazwy listy
        Text(text = listName, style = MaterialTheme.typography.headlineLarge)

        // Zamiast Column użyjemy LazyColumn dla scrollowalnej listy
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(exerciseList.exercises) { exercise ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Zadanie: ${exercise.content}")
                        Text("Punkty: ${exercise.points}")
                    }
                }
            }
        }
    }
}







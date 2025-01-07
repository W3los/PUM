package com.example.lista6.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    val exerciseList = exerciseLists.getOrNull(index) ?: return

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = listName, style = MaterialTheme.typography.headlineLarge)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(exerciseList.exercises) { exercise ->
                Card(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(exercise.content)
                        Text(exercise.loremIps)
                        Text("Punkty: ${exercise.points}")
                    }
                }
            }
        }
    }
}







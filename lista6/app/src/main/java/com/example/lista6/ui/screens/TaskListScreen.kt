package com.example.lista6.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.clickable
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lista6.data.ExerciseList

@Composable
fun TaskListScreen(navController: NavController, exerciseLists: List<ExerciseList>) {
    // Grupowanie list zadań po przedmiocie
    val groupedLists = exerciseLists.groupBy { it.subject.name }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        // Iteracja po pogrupowanych przedmiotach
        groupedLists.forEach { (subject, lists) ->
            // Numerowanie list w obrębie tego samego przedmiotu
            itemsIndexed(lists) { indexInGroup, exerciseList ->
                // Numerowanie listy w ramach przedmiotu
                val listName = "Lista ${indexInGroup + 1} - $subject" // Numerowanie listy w ramach przedmiotu

                // Zmieniamy kliknięcie, aby przekazać globalny index w obrębie całej exerciseLists
                val globalIndex = exerciseLists.indexOf(exerciseList) // Uzyskujemy oryginalny index w exerciseLists

                Card(
                    modifier = Modifier
                        .fillMaxWidth()  // Rozciąganie na całą szerokość
                        .padding(8.dp)
                        .clickable { navController.navigate("task_details/$globalIndex/$listName") }, // Przekazujemy globalny index
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        // Wyświetlanie numeru listy i przedmiotu
                        Text(text = listName)
                        Text(text = "Ocena: ${exerciseList.grade}")
                        Text(text = "Liczba zadań: ${exerciseList.exercises.size}")
                    }
                }
            }
        }
    }
}






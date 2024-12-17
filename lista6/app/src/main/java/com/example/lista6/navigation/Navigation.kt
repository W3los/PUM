package com.example.lista6.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lista6.data.generateDummyData
import com.example.lista6.ui.components.BottomNavigationBar
import com.example.lista6.ui.screens.GradesScreen
import com.example.lista6.ui.screens.TaskDetailsScreen
import com.example.lista6.ui.screens.TaskListScreen

@Composable
fun MainScreen() {
    // Tworzymy NavController
    val navController = rememberNavController()

    // Przykładowa lista zadań
    val exerciseLists = generateDummyData() // Zastąp to metodą generującą dane

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        // Definiujemy nawigację
        NavHost(
            navController = navController,
            startDestination = "task_list",  // Ekran startowy
            modifier = Modifier.padding(innerPadding)
        ) {
            // Ekran z listą zadań
            composable("task_list") {
                TaskListScreen(navController, exerciseLists)  // Przekazujemy NavController i ExerciseList
            }
            // Ekran z ocenami
            composable("grades_list") {
                GradesScreen(exerciseLists) // Przekazujemy exerciseLists do GradesScreen
            }
            // Ekran szczegółów zadania
            composable("task_details/{index}/{listName}") { backStackEntry ->
                val listName = backStackEntry.arguments?.getString("listName") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
                TaskDetailsScreen(listName, exerciseLists, index)  // Przekazujemy index oraz exerciseLists
            }
        }
    }
}

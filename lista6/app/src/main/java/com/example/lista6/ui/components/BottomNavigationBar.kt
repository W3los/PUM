package com.example.lista6.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Star

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar  {
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Listy zadań") },
            label = { Text("Listy zadań") },
            selected = navController.currentDestination?.route == "task_list",
            onClick = { navController.navigate("task_list") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = "Oceny") },
            label = { Text("Oceny") },
            selected = navController.currentDestination?.route == "grades_list",
            onClick = { navController.navigate("grades_list") }
        )
    }
}

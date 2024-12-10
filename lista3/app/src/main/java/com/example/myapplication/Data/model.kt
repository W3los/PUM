package com.example.myapplication.Data

import java.io.Serializable


data class Exercise(
    val content: String,
    val points: Int
)

data class Subject(
    val name: String
)

data class ExerciseList(
    val exercises: List<Exercise>,
    val subject: Subject,
    val grade: Double
):Serializable


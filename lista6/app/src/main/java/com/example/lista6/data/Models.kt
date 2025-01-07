package com.example.lista6.data

data class Exercise(
    val content: String,
    val loremIps: String,
    val points: Int
)

data class Subject(
    val name: String
)

data class ExerciseList(
    val exercises: List<Exercise>,
    val subject: Subject,
    val grade: Double
)
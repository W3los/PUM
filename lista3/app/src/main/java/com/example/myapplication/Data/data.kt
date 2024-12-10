package com.example.myapplication.Data

import kotlin.random.Random

object DataGenerator {

    /**
     * Funkcja generująca listę zadań (od 1 do 10 zadań).
     */
    fun generateExercise(): List<Exercise> {
        val exerciseCount = Random.nextInt(1, 11) // Liczba zadań: 1-10
        val exercises = mutableListOf<Exercise>()

        for (i in 1..exerciseCount) {
            val content = "Zadanie $i: ${generateRandomText()}"
            val points = Random.nextInt(1, 11) // Punkty: 1-10
            exercises.add(Exercise(content, points))
        }

        return exercises
    }

    /**
     * Funkcja generująca ocenę (od 3.0 do 5.0 z krokiem 0.5).
     */
    fun generateGrade(): Double {
        val grades = listOf(3.0, 3.5, 4.0, 4.5, 5.0)
        return grades.random()
    }

    /**
     * Funkcja generująca losowy tekst (Lorem Ipsum).
     */
    private fun generateRandomText(): String {
        val texts = listOf(
            "Lorem ipsum dolor sit amet",
            "Consectetur adipiscing elit",
            "Sed do eiusmod tempor",
            "Incididunt ut labore et dolore",
            "Magna aliqua. Ut enim ad minim",
            "Veniam, quis nostrud exercitation",
            "Ullamco laboris nisi ut aliquip",
            "Ex ea commodo consequat",
            "Duis aute irure dolor",
            "In reprehenderit in voluptate"
        )
        return texts.random()
    }
}


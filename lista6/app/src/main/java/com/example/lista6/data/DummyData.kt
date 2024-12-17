package com.example.lista6.data

fun generateDummyData(): List<ExerciseList> {
    val subjects = listOf("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy")
    val random = java.util.Random()

    return List(20) { index ->
        val exercises = List(random.nextInt(10) + 1) { i ->
            Exercise("Zadanie ${i + 1}", random.nextInt(10) + 1)
        }
        ExerciseList(
            exercises = exercises,
            subject = Subject(subjects.random()),
            grade = 3.0 + random.nextInt(5) * 0.5
        )
    }
}
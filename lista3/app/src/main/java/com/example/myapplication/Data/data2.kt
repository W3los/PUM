package com.example.myapplication.Data


object TaskRepository {
    private val subjects = listOf(
        Subject("Matematyka"),
        Subject("PUM"),
        Subject("Fizyka"),
        Subject("Elektronika"),
        Subject("Algorytmy")
    )

    val exerciseLists = mutableListOf<ExerciseList>()

    init {
        // Początkowa inicjalizacja z 20 listami
        generateInitialLists()
    }

    private fun generateInitialLists() {
        for (i in 1..20) {
            val subject = subjects.random()  // Losowanie przedmiotu
            val exercises = DataGenerator.generateExercise()  // Generowanie zadań
            val grade = DataGenerator.generateGrade()  // Generowanie oceny

            // Liczymy, ile istnieje list z danym przedmiotem
            val existingCount = exerciseLists
                .count { it.subject.name.startsWith(subject.name) }

            // Tworzymy nazwę listy, np. "Fizyka lista 1", "Fizyka lista 2"
            val listName = "${subject.name} lista ${existingCount + 1}"

            // Dodanie nowej listy do exerciseLists
            exerciseLists.add(
                ExerciseList(
                    exercises = exercises,
                    subject = Subject(listName),
                    grade = grade
                )
            )
        }
    }
}


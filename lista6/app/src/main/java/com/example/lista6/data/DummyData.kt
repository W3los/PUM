package com.example.lista6.data

fun generateDummyData(): List<ExerciseList> {
    val subjects = listOf("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy")
    val random = java.util.Random()
    val lorem = listOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Praesent tincidunt ante quis risus euismod, sit amet aliquam odio vulputate.",
        "Sed a leo a arcu dictum eleifend.",
        "Vivamus fermentum enim ut elit dapibus, vel fermentum leo viverra.",
        "Etiam eu nibh consectetur, tincidunt massa vitae, scelerisque quam.",
        "Nulla vitae risus vulputate, porta eros sit amet, ullamcorper mi.",
        "Suspendisse eget metus vitae odio finibus efficitur sit amet quis tortor.",
        "Etiam consectetur nulla et mauris egestas porta.")

    return List(20) { index ->
        val exercises = List(random.nextInt(10) + 1) { i ->
            Exercise("Zadanie ${i + 1}", lorem.random(), random.nextInt(10) + 1)
        }
        ExerciseList(
            exercises = exercises,
            subject = Subject(subjects.random()),
            grade = 3.0 + random.nextInt(5) * 0.5
        )
    }
}
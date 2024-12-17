package com.example.lista4

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

val sampleQuestions = listOf(
    Question("Co jest głównym składnikiem risotto?", listOf("Pomidory", "Ryż", "Ser twarogowy", "Makaron"), 1),
    Question("Ile przedsionków ma serce człowieka?", listOf("Jeden", "Dwa", "Żadnego", "Trzy"), 1),
    Question("Co jest głównym składnikiem ziemskiej atmosfery?", listOf("Tlen", "Para wodna", "Azot", "Dwutlenek węgla"), 2),
    Question("Ostatnia litera w greckim alfabecie to…", listOf("Alfa", "Gamma", "Zeta", "Omega"), 3),
    Question("Jednostką czego jest amper?", listOf("Prądu elektrycznego", "Pracy", "Światłości", "Masy"), 0),
    Question("Które miasto jest stolicą Egiptu?", listOf("Kair", "Nairobi", "Aleksandria", "Dakar"), 0),
    Question("Czego symbolem chemicznym jest P?", listOf("Potas", "Azot", "Fosfor", "Żelazo"), 2),
    Question("Jaki kolor kryje się pod nazwą ultramaryna?", listOf("Zielony", "Szary", "Niebieski", "Fioletowy"), 2),
    Question("Jaką wielkość fizyczną mierzymy w paskalach?", listOf("Ciepło", "Siłę", "Przyspieszenie", "Ciśnienie"), 3),
    Question("Skąd pochodzą churros?", listOf("Grecja", "Hiszpania", "Portugalia", "USA"), 1),
)

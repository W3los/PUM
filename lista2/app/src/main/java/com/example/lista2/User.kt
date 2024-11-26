package com.example.lista2

data class User(
    val username: String,
    val password: String
)

// Lista wstępnie wygenerowanych użytkowników (przechowywana w pamięci)
val defaultUsers = mutableListOf(
    User("john", "1234"),
    User("jane", "abcd"),
    User("mike", "pass"),
    User("emma", "word"),
    User("alice", "qwerty")
)
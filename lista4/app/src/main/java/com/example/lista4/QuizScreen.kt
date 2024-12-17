package com.example.lista4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun QuizScreen(questions: List<Question>) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableIntStateOf(-1) } // Indeks wybranej odpowiedzi
    var score by remember { mutableIntStateOf(0) } // Liczba poprawnych odpowiedzi

    // Jeśli wszystkie pytania zostały ukończone, pokaż wynik
    if (currentQuestionIndex >= questions.size) {
        ResultScreen(
            score = score,
            totalQuestions = questions.size,
            onRestart = {
                // Resetowanie stanu quizu
                currentQuestionIndex = 0
                selectedAnswer = -1
                score = 0
            }
        )
        return
    }

    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        QuestionHeader(currentQuestionIndex, questions.size, currentQuestion.questionText)

        AnswerOptions(
            options = currentQuestion.options,
            selectedAnswer = selectedAnswer,
            onOptionSelected = { selectedAnswer = it }
        )

        NextButton(
            onNext = {
                if (selectedAnswer == currentQuestion.correctAnswerIndex) {
                    score++ // Zwiększ wynik, jeśli odpowiedź jest poprawna
                }
                // Zwiększamy `currentQuestionIndex`, ale sprawdzamy, czy nie wykracza poza zakres
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++ // Zwiększamy indeks pytania
                    selectedAnswer = -1 // Resetujemy wybór odpowiedzi
                } else {
                    // Po ostatnim pytaniu przechodzimy do wyniku
                    currentQuestionIndex = questions.size
                }
            },
            enabled = selectedAnswer != -1 // Przycisk jest aktywowany tylko, gdy odpowiedź została wybrana
        )
    }
}





@Composable
fun QuestionHeader(currentIndex: Int, totalQuestions: Int, question: String) {
    Column {
        Text(
            text = "Pytanie ${currentIndex + 1} / $totalQuestions",
            style = MaterialTheme.typography.headlineMedium
        )
        LinearProgressIndicator(
            progress = { (currentIndex + 1).toFloat() / totalQuestions },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )
        Text(
            text = question,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun AnswerOptions(options: List<String>, selectedAnswer: Int, onOptionSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        options.forEachIndexed { index, option ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onOptionSelected(index) },  // Reakcja na kliknięcie
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),  // Cień
                shape = RoundedCornerShape(8.dp),  // Zaokrąglone rogi
                colors = CardDefaults.cardColors(containerColor = Color.White)  // Tło karty
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    RadioButton(
                        selected = selectedAnswer == index, // Sprawdzanie, czy odpowiedź jest zaznaczona
                        onClick = { onOptionSelected(index) } // Zaktualizowanie stanu po kliknięciu
                    )
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}



@Composable
fun NextButton(onNext: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onNext,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("Następne")
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(300.dp)
    ) {
        Text(
            text = "Quiz zakończony!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Zdobyłeś: $score / $totalQuestions",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 145.dp)

        )
        Button(
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Początek")
        }
    }
}


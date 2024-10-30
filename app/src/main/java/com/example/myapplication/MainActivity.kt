package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.ProgressBar


class MainActivity : AppCompatActivity() {
    data class Question(
        val number: String,
        val questionText: String,
        val options: List<String>,
        val correctAnswerIndex: Int
    )


    private var score = 0
    private var currentQuestionIndex = 0
    private lateinit var questions: List<Question>
    private var answerSelected = false
    private var selectedAnswerIndex: Int? = null
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Wczytanie pytań z strings.xml
        questions = loadQuestions()

        // Wyświetlenie pierwszego pytania
        displayQuestion()

        // Obsługa przycisku "Następne"
        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            if (currentQuestionIndex < questions.size) {
                if (answerSelected) {
                    // Dodaj punkt, jeśli odpowiedź jest poprawna
                    if (selectedAnswerIndex != null && selectedAnswerIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                        score++
                    }
                    currentQuestionIndex++
                    answerSelected = false
                    selectedAnswerIndex = null // Resetuj wybraną odpowiedź
                    displayQuestion()
                }
            } else {resetQuiz()}
        }
    }

    // Funkcja do wczytywania pytań ze strings.xml
    private fun loadQuestions(): List<Question> {
        val questionsArray = resources.getStringArray(R.array.questions)
        return questionsArray.map { questionData ->
            val parts = questionData.split("|")
            val questionNumber = parts[0]
            val questionText = parts[1]
            val options = parts.subList(2, parts.size - 1)
            val correctAnswerIndex = parts.last().toInt() - 1
            Question(questionNumber, questionText, options, correctAnswerIndex)
        }
    }

    // Wyświetla aktualne pytanie wraz z numerem pytania
    private fun displayQuestion() {
        val nextButton = findViewById<Button>(R.id.nextButton)
        findViewById<TextView>(R.id.question).visibility = View.VISIBLE

        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            findViewById<TextView>(R.id.questionNumber).text = question.number
            findViewById<TextView>(R.id.question).text = question.questionText

            // Przyciski odpowiedzi
            val buttons = listOf(
                findViewById<RadioButton>(R.id.answer1),
                findViewById<RadioButton>(R.id.answer2),
                findViewById<RadioButton>(R.id.answer3),
                findViewById<RadioButton>(R.id.answer4)
            )

            // Reset widoczności i ustawień przycisku "Następne"
            nextButton.text = getString(R.string.Nastepne)
            nextButton.visibility = View.GONE

            // Ukryj wynik
            findViewById<TextView>(R.id.resultTextView).visibility = View.GONE

            // Resetowanie zaznaczenia przycisków
            val radioGroup = findViewById<RadioGroup>(R.id.answersGroup)
            radioGroup.clearCheck()  // Oczyszcza zaznaczenie

            for ((index, button) in buttons.withIndex()) {
                button.text = question.options[index]
                button.visibility = View.VISIBLE
                button.setOnClickListener {
                    selectedAnswerIndex = index // Zapisz wybraną odpowiedź
                    answerSelected = true // Ustaw flagę, że odpowiedź została wybrana
                    // Wyświetl przycisk "Następne"
                    nextButton.visibility = View.VISIBLE
                }
            }
            updateProgressBar()
        } else {
            showResult()
        }
    }
    private fun updateProgressBar() {
        val progress = (currentQuestionIndex + 1) * 100 / questions.size
        progressBar.progress = progress
    }

    // Funkcja pokazująca wynik końcowy z gratulacjami
    private fun showResult() {
        findViewById<TextView>(R.id.questionNumber).text = "Gratulacje!"
        val nextButton = findViewById<Button>(R.id.nextButton)

        val resultMessage = "Twój wynik: $score / ${questions.size}"
        findViewById<TextView>(R.id.resultTextView).apply {
            text = resultMessage
            visibility = View.VISIBLE
        }
        nextButton.text = getString(R.string.reset_button_text)

        // Ukrycie przycisków odpowiedzi i przycisku "Następne" po zakończeniu quizu
        findViewById<Button>(R.id.answer1).visibility = View.GONE
        findViewById<Button>(R.id.answer2).visibility = View.GONE
        findViewById<Button>(R.id.answer3).visibility = View.GONE
        findViewById<Button>(R.id.answer4).visibility = View.GONE
        findViewById<TextView>(R.id.question).visibility = View.GONE
    }
    private fun resetQuiz() {
        score = 0
        currentQuestionIndex = 0
        answerSelected = false

        // Ukryj wynik, zresetuj tekst przycisku i wyświetl pierwsze pytanie
        findViewById<TextView>(R.id.resultTextView).visibility = View.GONE
        findViewById<Button>(R.id.nextButton).text = getString(R.string.Nastepne)
        displayQuestion()
    }
}






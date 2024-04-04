package main

import kotlinMultipleChoiceQuestions
import kotlinQuestions
import kotlinTrueOrFalseQuestions
import main.Questions.MultipleChoiceQuestion
import main.Questions.TrueOrFalseQuestion


fun main() {
    kotlinQuestions.addAll(kotlinMultipleChoiceQuestions)
    kotlinQuestions.addAll(kotlinTrueOrFalseQuestions)

    val quiz = Quiz()
    quiz.startGame()
    println("🃏 Type 'joker' to request a joker at any time 🃏")

    do {
        for (player in quiz.listOfPlayers) {
            if (player.ageCheck()) {
                quiz.generateQuestion()
                println("${player.name}, type your answer: ")
                val playersAnswer = readln()
                if (playersAnswer != "joker") {
                    player.answer(quiz.currentQuestion)
                } else {
                    quiz.useJokerQuestion(player, quiz.currentQuestion)
                    player.answer(quiz.currentQuestion)
                }
                quiz.validateAnswer(player)
            }
        }
    } while (quiz.listOfQuestions.isNotEmpty())

    quiz.defineWinner()
    quiz.endGame()
}
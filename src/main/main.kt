package main

import kotlinMultipleChoiceQuestions
import kotlinQuestions
import kotlinTrueOrFalseQuestions


fun main() {
    kotlinQuestions.addAll(kotlinMultipleChoiceQuestions)
    kotlinQuestions.addAll(kotlinTrueOrFalseQuestions)

    val quiz = Quiz()
    quiz.startGame()
    println("🃏 Type 7 anytime to use a joker 🃏")
    do {
        for (player in quiz.listOfPlayers) {
            if (player.ageCheck()) {
                quiz.generateQuestion()
                player.answer()
                if (player.answer == 7) {
                    quiz.useJokerQuestion(player, quiz.currentQuestion)
                    player.answer()
                }
                quiz.validateAnswer(player)
            }
        }
    } while (quiz.listOfQuestions.isNotEmpty())

    quiz.defineWinner()
    quiz.endGame()
}
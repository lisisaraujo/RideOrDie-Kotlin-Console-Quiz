package main

import kotlinMultipleChoiceQuestions
import kotlinQuestions
import kotlinTrueOrFalseQuestions
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question


fun main() {
    kotlinQuestions.addAll(kotlinMultipleChoiceQuestions)
    kotlinQuestions.addAll(kotlinTrueOrFalseQuestions)
    println(kotlinQuestions)

    val quiz = Quiz()
    quiz.startGame()
    val player1 = quiz.listOfPlayers.first()
    val player2 = quiz.listOfPlayers.last()

    if (player1.ageCheck() && player2.ageCheck()) {
        do {

            quiz.generateQuestion()
            quiz.useJokerQuestion(player1, quiz.currentQuestion)
            player1.answer()
            quiz.useJokerQuestion(player2, quiz.currentQuestion)
            player2.answer()
            quiz.validateAnswer(player1)
            quiz.validateAnswer(player2)
        } while (quiz.listOfQuestions.size > 0)

        quiz.defineWinner()
        quiz.endGame()
    }


}
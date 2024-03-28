package main.Jokers

import main.Players.Player
import main.Questions.Question

open class Joker50(player: Player) : Joker(type = "Joker50", player) {

    override fun playJoker(question: Question): Question {
        super.playJoker(question)
        var correctAnswer = question.possibleAnswers[question.correctAnswer]
        val answersOptions = answerOptions(question).shuffled()
        val correctAnswerIndex = answersOptions.indexOf(correctAnswer)
        val copyQuestion = Question(
            difficultyLevel = question.difficultyLevel,
            questionText = question.questionText,
            category = question.category,
            possibleAnswers = answersOptions,
            correctAnswer = correctAnswerIndex
        )
        println("Select the correct answer: ")
        copyQuestion.showQuestion()
        return copyQuestion
    }

    private fun answerOptions(question: Question): MutableList<String> {
        var answersListWithoutCorrectAnswer: MutableList<String> = mutableListOf()
        var possibleAnswersList: MutableList<String> = mutableListOf()
        for (answer in question.possibleAnswers) {
            if (answer != question.possibleAnswers[question.correctAnswer]) answersListWithoutCorrectAnswer.add(answer)
        }
        possibleAnswersList =
            mutableListOf(question.possibleAnswers[question.correctAnswer], answersListWithoutCorrectAnswer.last())
        return possibleAnswersList
    }
}
package main.Jokers

import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question

open class Joker50(player: Player) : Joker(type = "Joker50", player) {

    override fun playJoker(question: MultipleChoiceQuestion): MultipleChoiceQuestion {
        super.playJoker(question)
        val answersOptions = answerOptions(question).shuffled()
        val copyQuestion = MultipleChoiceQuestion(
            difficultyLevel = question.difficultyLevel,
            questionText = question.questionText,
            category = question.category,
            possibleAnswers = answersOptions,
            correctAnswer = question.correctAnswer
        )
        println("Select the correct answer: ")
        copyQuestion.showQuestion()
        return copyQuestion
    }

    private fun answerOptions(question: MultipleChoiceQuestion): MutableList<String> {
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
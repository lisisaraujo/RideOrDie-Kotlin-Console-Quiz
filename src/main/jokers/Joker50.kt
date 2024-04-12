package main.jokers

import main.players.Player
import main.questions.MultipleChoiceQuestion
import main.questions.Question
import main.questions.TrueOrFalseQuestion

open class Joker50(player: Player) : Joker(type = "Joker50", player) {

    override fun playJoker(question: Question): Question {

        when (question) {

            is MultipleChoiceQuestion -> {
                super.playJoker(question)
                val answersOptions = answerOptions(question).shuffled()
                val copyQuestion = MultipleChoiceQuestion(
                    difficultyLevel = question.difficultyLevel,
                    questionText = question.questionText,
                    category = question.category,
                    possibleAnswers = answersOptions,
                    correctAnswer = question.correctAnswer
                )

                println(" \n Select the correct answer: \n")
                copyQuestion.showQuestion()
                return copyQuestion
            }

            is TrueOrFalseQuestion -> {
                println(" \n Joker 50 can only be applied to Multiple choice questions  \n")
            }
        }
        return question
    }

    private fun answerOptions(question: MultipleChoiceQuestion): MutableList<String> {
        val answersListWithoutCorrectAnswer: MutableList<String> = mutableListOf()
        val possibleAnswersList: MutableList<String>
        for (answer in question.possibleAnswers) {
            if (answer != question.possibleAnswers[question.correctAnswer - 1]) answersListWithoutCorrectAnswer.add(
                answer
            )
        }
        possibleAnswersList =
            mutableListOf(question.possibleAnswers[question.correctAnswer - 1], answersListWithoutCorrectAnswer.last())
        return possibleAnswersList
    }
}
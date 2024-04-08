package main.Jokers

import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

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

                println("Select the correct answer: ")
                copyQuestion.showQuestion()
                return copyQuestion
            }

            is TrueOrFalseQuestion -> {
                println("Joker 50 can only be applied to Multiple choice questions")
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
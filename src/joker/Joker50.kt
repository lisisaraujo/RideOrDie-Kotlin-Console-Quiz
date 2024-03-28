package joker

import main.Player
import main.Question

open class Joker50(player: Player) : Joker(type = "Joker50", player) {

    override fun playJoker(question: Question) {
        super.playJoker(question)
        val answersOptions = answerOptions(question).shuffled()
        println("Select the correct answer: ")
        for (answer in answersOptions) println(answer)
    }

    private fun answerOptions(question: Question): MutableList<String> {
        var answersListWithoutCorrectAnswer: MutableList<String> = mutableListOf()
        var possibleAnswersList: MutableList<String> = mutableListOf()
        for (answer in question.possibleAnswers) {
            if (answer != question.possibleAnswers[question.correctAnswer]) answersListWithoutCorrectAnswer.add(answer)
        }
        possibleAnswersList = mutableListOf(question.possibleAnswers[question.correctAnswer], answersListWithoutCorrectAnswer.last())
        return possibleAnswersList
    }
}
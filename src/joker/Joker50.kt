package joker

import main.Player
import main.Question

open class Joker50(player: Player) : Joker(type = "Joker50", player) {



    override fun useJoker(question: Question) {
        super.useJoker(question)
        var answersOptions: List<Any> = listOf(question.possibleAnswers.random(), question.correctAnswer).shuffled()
        println("Select the correct answer: $answersOptions")
    }
}
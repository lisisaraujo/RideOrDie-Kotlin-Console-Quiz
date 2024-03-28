package joker

import main.Player
import main.Question

class Joker100(player: Player) : Joker(type = "Joker100", player) {

    override fun playJoker(question: Question) {
        super.playJoker(question)

        println("The the correct answer is: ${question.correctAnswer}")

    }
}
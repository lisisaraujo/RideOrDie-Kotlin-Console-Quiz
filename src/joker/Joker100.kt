package joker

import main.Player
import main.Question

class Joker100(player: Player) : Joker(type = "Joker100", player) {

    override fun playJoker(question: Question): Question {
        super.playJoker(question)
        println("The the correct answer is: ${question.correctAnswer + 1}. ${question.possibleAnswers[question.correctAnswer]}")
        return question
    }
}
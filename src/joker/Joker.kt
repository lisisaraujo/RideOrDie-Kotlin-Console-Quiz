package joker

import main.Player
import main.Question

open class Joker(open var type: String, var player: Player) {

    open fun playJoker(question: Question): Question {
        println("🃏🃏🃏🃏🃏 Joker $type in use 🃏🃏🃏🃏🃏")
        return question
    }
}
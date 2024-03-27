package joker

import main.Player
import main.Question

open class Joker(open var type: String, var player: Player) {

    open fun useJoker(question: Question) {
        println("🃏🃏🃏🃏🃏 Joker $type in use 🃏🃏🃏🃏🃏")
    }
}
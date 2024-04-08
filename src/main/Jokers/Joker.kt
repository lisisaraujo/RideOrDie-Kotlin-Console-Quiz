package main.Jokers

import main.Players.Player
import main.Questions.Question

open class Joker(open var type: String, var player: Player) {

    open fun playJoker(question: Question): Question {
        println("🃏🃏🃏🃏🃏 $type 🃏🃏🃏🃏🃏")
        return question
    }

}
package main.Jokers

import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

open class Joker(open var type: String, var player: Player) {

    open fun playJoker(question: Question): Question {
        println("🃏🃏🃏🃏🃏 $type 🃏🃏🃏🃏🃏")
        return question
    }

}
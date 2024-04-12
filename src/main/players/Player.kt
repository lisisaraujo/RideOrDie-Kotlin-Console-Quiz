package main.players

import main.jokers.Joker
import main.jokers.Joker100
import main.jokers.Joker50
import main.questions.Question


open class Player(val name: String, val age: Int) {
    open var answerMultipleChoice: Int = 0
    open var answerTrueOfFalse: Boolean = false
    open var lives: Int = 3
    open var score: Int = 0
    open var scoresList: MutableList<Int> = mutableListOf()
    open var jokers: MutableList<Joker> = mutableListOf()
    open var questions: MutableList<Question> = mutableListOf()
    open var playerAnswer: Any = 0
    open var account: Int = 0


    init {
        score = 0
        jokers = mutableListOf(Joker50(this), Joker50(this), Joker100(this))
    }


    open fun useJoker(question: Question): Question {
        return question
    }

    open fun answer(question: Question): Any {
        return playerAnswer
    }

    open fun resetPlayer() {
        this.jokers = this.jokers
        this.lives = 3
        this.score = 0
    }

}
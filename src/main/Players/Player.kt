package main.Players

import main.Jokers.Joker
import main.Jokers.Joker100
import main.Jokers.Joker50
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

open class Player(val name: String, val age: Int) {
    open var answerMultipleChoice: Int = 0
    open var answerTrueOfFalse: Boolean = false
    open var lives: Int = 3
    open var score: Int = 0
    open var jokers: MutableList<Joker> = mutableListOf()
    open var questions: MutableList<Question> = mutableListOf()
    open var playerAnswer: Any = 0


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

    open fun resetPlayers() {
        this.score = 0
        this.jokers = jokers
        this.lives = 3
    }

}
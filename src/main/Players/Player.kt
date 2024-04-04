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

    fun ageCheck(): Boolean {
        var isOldEnough = false
        if (age < 12) println("Player is too young to play.")
        else isOldEnough = true

        return isOldEnough
    }


    open fun useJoker(question: MultipleChoiceQuestion): MultipleChoiceQuestion {
        println("You have ${jokers.size} jokers to use:")
        for (joker in jokers) println(joker.type)
        println("which type of joker do you want to use? Joker50 / Joker100")
        val typeOfJoker = readln().lowercase()

        val selectedJoker = when (typeOfJoker) {
            "joker50" -> jokers.find { it is Joker50 }
            "joker100" -> jokers.find { it is Joker100 }
            else -> null
        }
        if (selectedJoker != null) {
            jokers.removeIf { it == selectedJoker } // code line from perplexity
            return selectedJoker.playJoker(question)
        } else {
            println("Invalid joker type.")
            return question
        }
    }

    open fun useJoker(question: TrueOrFalseQuestion): TrueOrFalseQuestion {
        println("You have ${jokers.size} jokers to use:")
        for (joker in jokers) println(joker.type)
        println("which type of joker do you want to use? Joker50 / Joker100")
        val typeOfJoker = readln().lowercase()
        val selectedJoker = when (typeOfJoker) {
            "joker50" -> jokers.find { it is Joker50 }
            "joker100" -> jokers.find { it is Joker100 }
            else -> null
        }
        if (selectedJoker != null) {
            jokers.removeIf { it == selectedJoker }
            return selectedJoker.playJoker(question)
        } else {
            println("Invalid joker type.")
            return question
        }
    }

    private fun selectedJokerPlay(question: Question, typeOfJoker: String): Question {
        val selectedJoker = jokers.find { it.type.lowercase() == typeOfJoker }
        if (selectedJoker != null) {
            jokers.remove(selectedJoker)
            return selectedJoker.playJoker(question)
        } else {
            println("No $typeOfJoker found.")
            return question
        }
    }


    open fun answer(question: Question): Any {
        val jokerRequest: String
        println("${this.name}, type your answer: ")
        when (question) {
            is MultipleChoiceQuestion -> {
                answerMultipleChoice = readln().toInt()
                playerAnswer = answerMultipleChoice
            }
            is TrueOrFalseQuestion -> {
                answerTrueOfFalse = readln().toBoolean()
                playerAnswer = answerTrueOfFalse
            }

            else -> {
                jokerRequest = readln()
                playerAnswer = jokerRequest
            }
        }
        return playerAnswer
    }

}
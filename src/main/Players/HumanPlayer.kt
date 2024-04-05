package main.Players

import main.Jokers.Joker100
import main.Jokers.Joker50
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class HumanPlayer(name: String, age: Int): Player(name, age) {

    fun ageCheck(): Boolean {
        var isOldEnough = false
        if (age < 12) println("Player is too young to play.")
        else isOldEnough = true

        return isOldEnough
    }


    override fun useJoker(question: MultipleChoiceQuestion): MultipleChoiceQuestion {
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

    override fun useJoker(question: TrueOrFalseQuestion): TrueOrFalseQuestion {
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

    override fun answer(question: Question): Any {
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
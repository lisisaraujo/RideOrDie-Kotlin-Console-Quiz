package main.players

import main.jokers.Joker100
import main.jokers.Joker50
import main.questions.MultipleChoiceQuestion
import main.questions.Question
import main.questions.TrueOrFalseQuestion

class HumanPlayer(name: String, age: Int) : Player(name, age) {

    fun ageCheck(): Boolean {
        var isOldEnough = false
        if (age < 12) println("Player is too young to play.")
        else isOldEnough = true

        return isOldEnough
    }


    override fun useJoker(question: Question): Question {

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


    override fun answer(question: Question): Any {


        if (playerAnswer == "joker") {
            println("${name}, type your answer: ")
            playerAnswer = readln().lowercase()
        }

        when (question) {
            is MultipleChoiceQuestion -> {
               answerMultipleChoice  = (playerAnswer as String).toInt()
            }

            is TrueOrFalseQuestion -> {
              answerTrueOfFalse = (playerAnswer as String).toBoolean()
            }
        }
        return playerAnswer
    }
}
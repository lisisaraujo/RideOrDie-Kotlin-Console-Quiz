package main.Players

import main.Jokers.Joker100
import main.Jokers.Joker50
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class HumanPlayer(name: String, age: Int) : Player(name, age) {

    fun ageCheck(): Boolean {
        var isOldEnough = false
        if (age < 12) println("Player is too young to play.")
        else isOldEnough = true

        return isOldEnough
    }


    override fun useJoker(question: Question): Question {
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
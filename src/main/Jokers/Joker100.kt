package main.Jokers

import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class Joker100(player: Player) : Joker(type = "Joker100", player) {

    override fun playJoker(question: Question): Question {
        when (question) {
            is MultipleChoiceQuestion -> {
                println(" \n In joker class $type: MultipleChoice \n")
                super.playJoker(question)

                println("The the correct answer is: ")
                println(question.possibleAnswers[question.correctAnswer - 1])

            }
            is TrueOrFalseQuestion -> {
                println(" \n In joker class $type: TrueOrFalse \n")
                super.playJoker(question)

                println(" \n The the correct answer is: ${question.correctAnswer} \n")

            }
        }
        return question
    }
}
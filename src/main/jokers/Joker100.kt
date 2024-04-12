package main.jokers

import main.players.Player
import main.questions.MultipleChoiceQuestion
import main.questions.Question
import main.questions.TrueOrFalseQuestion

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
package main.Jokers

import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class Joker100(player: Player) : Joker(type = "Joker100", player) {

    override fun playJoker(question: Question): Question {
        when (question) {
            is MultipleChoiceQuestion -> {
                println("In joker class $type: MultipleChoice")
                super.playJoker(question)

                println("The the correct answer is: ")
                println(question.possibleAnswers[question.correctAnswer - 1])

            }
            is TrueOrFalseQuestion -> {
                println("In joker class $type: TrueOrFalse")
                super.playJoker(question)

                println("The the correct answer is: ${question.correctAnswer}")

            }
        }
        return question
    }
}
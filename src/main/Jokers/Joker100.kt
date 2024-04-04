package main.Jokers

import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.TrueOrFalseQuestion

class Joker100(player: Player) : Joker(type = "Joker100", player) {

    override fun playJoker(question: MultipleChoiceQuestion): MultipleChoiceQuestion {
        println("In joker class $type: MultipleChoice")
        super.playJoker(question)

        println("The the correct answer is: ")
        println(question.possibleAnswers[question.correctAnswer - 1])
        return question
    }

   override fun playJoker(question: TrueOrFalseQuestion): TrueOrFalseQuestion {
       println("In joker class $type: TrueOrFalse")
        super.playJoker(question)

        println("The the correct answer is: ")
        println(question.possibleAnswers[question.correctAnswer - 1])
        return question
    }

}
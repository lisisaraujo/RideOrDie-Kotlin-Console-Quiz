import main.Jokers.Joker100
import main.Jokers.Joker50
import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class MachinePlayer(name: String, age: Int) : Player(name = "Machine3000", age = 18) {
    var selectedJoker = jokers.random()
    override fun useJoker(question: Question): Question {
        selectedJoker = jokers.random()
        if (selectedJoker != null) {
            jokers.removeIf { it == selectedJoker }
            return selectedJoker.playJoker(question)
        } else {
            println("Invalid joker type.")
            return question
        }
    }


    override fun answer(question: Question): Any {
        val randomJokerRequest = listOf(playerAnswer, playerAnswer, "joker")
        val randomTrueOrFalse = listOf(true, false)
        val randomMultipleChoiceAnswer = listOf(1, 2, 3, 4)
        val useJoker = listOf(true, true, false, false, false)




        when (question) {
            is MultipleChoiceQuestion -> {
                if (useJoker.random()) {
                    var remainingOptions: MultipleChoiceQuestion = useJoker(question) as MultipleChoiceQuestion
                    if (selectedJoker is Joker50) {
                        playerAnswer = remainingOptions.possibleAnswers.random()
                        playerAnswer = question.possibleAnswers.indexOf(playerAnswer) + 1
                    }
                    if (selectedJoker is Joker100) playerAnswer = question.correctAnswer

                } else {
                    playerAnswer = randomMultipleChoiceAnswer.random()
                }
                answerMultipleChoice = playerAnswer as Int
            }

            is TrueOrFalseQuestion -> {
                if (useJoker.random()) {
                    playerAnswer = question.correctAnswer
                } else {
                    playerAnswer = randomTrueOrFalse.random()
                }
                answerTrueOfFalse = playerAnswer as Boolean
            }

            else -> {
                playerAnswer = randomJokerRequest.random()
            }
        }

        println("${name} answer: ${playerAnswer}")
        return playerAnswer
    }

}


import main.Jokers.Joker100
import main.Jokers.Joker50
import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class MachinePlayer(name: String, age: Int) : Player(name = "Wannabe Kotlina", age = 18) {

    var typeOfJoker = jokers.random()
    override fun useJoker(question: Question): Question {

        println("You have ${jokers.size} jokers to use:")
        for (joker in jokers) println(joker.type)
        println("which type of joker do you want to use? Joker50 / Joker100")

        val selectedJoker = when (typeOfJoker) {
            is Joker50 -> jokers.find { it is Joker50 }
            is Joker100 -> jokers.find { it is Joker100 }
            else -> null
        }
        typeOfJoker = jokers.random()
        jokers.removeIf { it == selectedJoker }
        return typeOfJoker.playJoker(question)
    }


    override fun answer(question: Question): Any {
        val randomJokerRequest = listOf(playerAnswer, playerAnswer, "joker")
        val randomTrueOrFalse = listOf(true, false)
        val randomMultipleChoiceAnswer = listOf(1, 2, 3, 4)
        val useJoker = listOf(true, true, false, false, false)

        when (question) {
            is MultipleChoiceQuestion -> {
                if (useJoker.random()) {
                    val remainingOptions: MultipleChoiceQuestion = useJoker(question) as MultipleChoiceQuestion
                    if (typeOfJoker is Joker50) {
                        playerAnswer = remainingOptions.possibleAnswers.random()
                        playerAnswer = question.possibleAnswers.indexOf(playerAnswer) + 1
                    }
                    if (typeOfJoker is Joker100) playerAnswer = question.correctAnswer

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
        Thread.sleep(300)
        println("${name} answer: ${playerAnswer}")
        return playerAnswer
    }

}


import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class MachinePlayer(name: String, age: Int) : Player(name = "Machine3000", age = 18) {
    override var playerAnswer: Any = 0

    override fun useJoker(question: MultipleChoiceQuestion): MultipleChoiceQuestion {
        val selectedJoker = jokers.random()
        if (selectedJoker != null) {
            jokers.removeIf { it == selectedJoker }
            return selectedJoker.playJoker(question)
        } else {
            println("Invalid joker type.")
            return question
        }
    }

    override fun useJoker(question: TrueOrFalseQuestion): TrueOrFalseQuestion {
        val selectedJoker = jokers.random()
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
        val useJoker = listOf(true, false, false, false, false)

        when (question) {
            is MultipleChoiceQuestion -> {
                if (useJoker.random()) {
                    playerAnswer = useJoker(question)
                } else {
                    playerAnswer = randomMultipleChoiceAnswer.random()
                }
            }
            is TrueOrFalseQuestion -> {
                if (useJoker.random()) {
                    playerAnswer = useJoker(question)
                } else {
                    playerAnswer = randomTrueOrFalse.random()
                }
            }
            else -> {
                playerAnswer = randomJokerRequest.random()
            }
        }

        println("${this.name} answer: $playerAnswer")
        return playerAnswer
    }

}


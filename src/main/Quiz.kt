package main

class Quiz(
    listOfPlayers: MutableList<Player>,
    listOfQuestions: MutableList<Question>,
    roundCount: Int,
    endCondition: Boolean,
    numOfPlayers: Int
) {
    val player1 = Player("Lisis", 30, 0, mutableListOf(Joker50("joker50", listOf("a", "b")), Joker50("joker50", listOf("c", "d"))), 5)
    var kotlinQuestions: List<MultipleChoiceQuestion> = listOf()

    fun startGame() {

    }

    fun generateQuestion() {

    }
    fun defineWinner(){

    }
    fun endGame(){

    }
}
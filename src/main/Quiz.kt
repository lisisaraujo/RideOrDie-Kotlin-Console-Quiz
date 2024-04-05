package main

import kotlinQuestions
import MachinePlayer
import main.Colours.RESET
import main.Colours.WHITE_BACKGROUND
import main.Players.HumanPlayer
import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class Quiz(

) {
    var listOfPlayers: MutableList<Player> = mutableListOf()
    val listOfQuestions: MutableList<Question> = kotlinQuestions
    var roundCount: Int = 1
    var winnerExists = false
    var winner: Player = Player("", 0)
    var currentQuestion: Question = listOfQuestions.random()
    var maxPlayers = 4

    private fun numOfPlayers(): Int {
        var input: Int? = null

        while (input == null) {
            println("How many players? 1 - 4")
            val inputString = readlnOrNull()

            if (inputString != null) {
                try {
                    input = inputString.toInt()
                    if (input !in 1..4) {
                        println("Invalid number of players. Please enter a number between 1 and 4.")
                        input = null
                    }
                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a number between 1 and 4.")
                }
            }
        }
        return input
    }

    fun startGame() {
        val numOfPlayers = numOfPlayers()

        repeat(numOfPlayers) {
            println("Player ${it + 1}: ")
            generatePlayer()

        }

        if (listOfPlayers.size >= 2) {
            if (roundCount < 1) println("Game started. Round $roundCount")
            else println("New game started. Round $roundCount")
            roundCount++
        } else {
            println("Not enough players for the game to start. Let another player join.")
        }
        println("Would you like to play against the machine? Yes / No")
        val machinePlayAnswer = readln().lowercase()
        if (machinePlayAnswer == "yes") listOfPlayers.add(MachinePlayer("Machine3000", 18))
        else generatePlayer()

    }

    private fun generatePlayer() {
        println("Add player:")
        print("Name: ")
        val name = readln().lowercase()
        print("Age: ")
        val age = readln().toInt()
        val player = HumanPlayer(name, age)
        if (player.ageCheck()) {
            listOfPlayers.add(player)
        } else {
            println("Player is too young")
            generatePlayer()
        }

        println("Players in this round: ")
        for (player in listOfPlayers) {
            println(player.name)
        }
    }

    fun generateQuestion(): String {
        currentQuestion = listOfQuestions.random()
        currentQuestion.showQuestion()
        for (player in listOfPlayers) {
            player.questions.add(currentQuestion)
        }
        listOfQuestions.remove(currentQuestion)

        return currentQuestion.questionText
    }

    fun useJokerQuestion(player: Player, question: Question): Boolean {
        currentQuestion = question
        var useJoker = false

        println("Which type of joker do you want to use? Joker50 / Joker100")
        currentQuestion = when (question) {
            is MultipleChoiceQuestion -> player.useJoker(question)
            is TrueOrFalseQuestion -> player.useJoker(question)
            else -> question
        }
        useJoker = true
        return useJoker
    }


    fun validateAnswer(player: Player): Boolean {
        var isAnswerCorrect = false

        if (player.answerMultipleChoice == currentQuestion.correctAnswer || player.answerTrueOfFalse == currentQuestion.correctAnswer) {
            println("${player.name} --------- Correct answer! ---------")
            isAnswerCorrect = true
            player.score += 5
        } else {
            println("${player.name} --------- Wrong answer! ---------")
            player.lives -= 1
        }

        return isAnswerCorrect
    }

    fun defineWinner(): Boolean {
        var playersScores: MutableList<Int> = mutableListOf()

        for (player in listOfPlayers) {
            playersScores.add(player.score)
        }

        val highestScore = playersScores.maxOrNull()

        if (highestScore != null) {
            val winners = listOfPlayers.filter { it.score == highestScore }
            if (winners.size == 1) {
                winner = winners[0]
                println("${winner.name} is the winner!")
                winnerExists = true
            } else println("It's a tie!")
        }

        for (player in listOfPlayers) {
            println("${player.name}: ${player.score} points.")
        }

        return winnerExists
    }

    fun endGame() {
        if (winnerExists) println("End of game. ${winner.name} won with ${winner.score} points")
        return
    }
}
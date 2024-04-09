package main

import MachinePlayer
import kotlinQuestions
import main.Colours.*
import main.Players.HumanPlayer
import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

class Quiz(

) {
    var listOfPlayers: MutableList<Player> = mutableListOf()
    var listOfQuestions = kotlinQuestions
    var roundCount: Int = 1
    var winnerExists = false
    var winner: Player = Player("", 0)
    var currentQuestion: Question = listOfQuestions.random()
    var maxPlayers = 4
    var filteredQuestions: MutableList<Question> = mutableListOf()
    var roundWinners: MutableList<Player> = mutableListOf()

    private fun numOfPlayers(): Int {
        var input: Int? = null

        while (input == null) {
            println("How many players? 1 - 4")
            val inputString = readlnOrNull()

            if (inputString != null) {
                try {
                    input = inputString.toInt()
                    if (input !in 1..maxPlayers) {
                        println("$rot Invalid number of players. Please enter a number between 1 and 4. $reset")
                        input = null
                    }
                } catch (e: NumberFormatException) {
                    println("$rot Invalid input. Please enter a number between 1 and 4. $reset")
                }
            }
        }
        return input
    }

    fun startGame() {
        val numOfPlayers = numOfPlayers()
        var i = 1
        repeat(numOfPlayers) {
            generatePlayer()
        }

        println("\n --------- Players in this round: --------- \n")
        for (player in listOfPlayers) {
            println("$i. ${player.name}")
            i++
        }
        if (listOfPlayers.size >= 2) {
            if (roundCount < 1) println("\n Game started. Round $roundCount \n")
            else println("\n New game started. Round $roundCount \n ")
        } else {
            println("Not enough players for the game to start. Let another player join. \n")
            println("Would you like to play against the machine? Yes / No \n")
            val machinePlayAnswer = readln().lowercase()
            if (machinePlayAnswer.contains("y")) listOfPlayers.add(MachinePlayer("Machine3000", 18))
            else generatePlayer()
        }

    }

    private fun generatePlayer() {
        println("------------- Add player -------------")
        print("Name: ")
        val name = readln().lowercase()
        print("Age: ")
        val age = readln().toInt()
        val player = HumanPlayer(name, age)
        if (player.ageCheck()) {
            listOfPlayers.add(player)
        } else {
            generatePlayer()
        }

        Thread.sleep(500)
    }

    fun generateQuestion(): String {
        filteredQuestions = listOfQuestions.filter { it.difficultyLevel == roundCount }.toMutableList()
        if (filteredQuestions.isEmpty()) {
            return "No questions of difficulty level $roundCount available."
        }
        currentQuestion = filteredQuestions.random()
        currentQuestion.showQuestion()
        for (player in listOfPlayers) {
            player.questions.add(currentQuestion)
        }
        listOfQuestions.remove(currentQuestion)
        filteredQuestions.remove(currentQuestion)
        return currentQuestion.questionText
    }

    fun useJokerQuestion(player: Player, question: Question): Boolean {
        currentQuestion = question
        var useJoker: Boolean = false

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
            println("Checking answer...")
            Thread.sleep(500)
            println("\n ${GREEN_BACKGROUND}E ${player.name} ✅ Correct answer! ✅ $RESET \n ")
            isAnswerCorrect = true
            player.score += 5
            player.account = player.score * 1000.0
        } else {
            println("Checking answer...")
            Thread.sleep(500)
            println("\n $RED_BACKGROUND ${player.name} ❌ Wrong answer! ❌ $RESET \n")
            player.lives -= 1
        }
        println("Points: ${player.score}")
        println("Lives: ${player.lives}")
        println("Account: ${player.account}")
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
                roundWinners.add(winner)
                var previousRpundWinner = 0
                if (roundWinners[previousRpundWinner]!= roundWinners.last()) {
                    transferPoints(roundWinners.first(), roundWinners.last())
                    previousRpundWinner++
                } else roundWinners[previousRpundWinner].score *= 3
                println("                               ${winner.name} is the winner!")
                println("                   ${bold}You won ${gruen}${winner.account} ${reset}kotlin skills!!$reset\n")
                println("New account status: ${winner.account} KTL")
                winnerExists = true
            } else println("It's a tie!")
        }

        for (player in listOfPlayers) {
            println("${player.name}: ${player.score} points.")
        }

        return winnerExists
    }

    fun endGame(): Boolean {

        println("           End of game. ${winner.name} won with ${winner.score} points\n")
        println("")
        return true

    }


    fun startNewRound(): Boolean {
        var playNewRoundInput: String?
        var newRound = false
        if (endGame()) {
            println("To play another round, you must bett half of your winnings.")
            println("IF you win, you your points will be multiplied by 3x")
            println("... now, if you lose... your points will go to your opponent 🤡")
            println(" \n Would you like to play another round? Yes / No \n")
            playNewRoundInput = readln().lowercase()
            if (playNewRoundInput == "yes") {
                newRound = true
                roundCount++
                for (player in listOfPlayers) {
                    player.resetPlayers()
                }
            }
        }

        return newRound
    }

    fun transferPoints(player1: Player, player2: Player) {
        player2.score += (player1.score / 2)
        player1.score = player1.score / 2
    }
}
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
    var maxPlayers = 2
    var filteredQuestions: MutableList<Question> = mutableListOf()
    var roundWinners: MutableList<Player> = mutableListOf()
    var previousRoundWinnerIndex = 0
    open var winnersList: MutableList<Player> = mutableListOf()
    open var losersList: MutableList<Player> = mutableListOf()

    private fun numOfPlayers(): Int {
        var input: Int? = null

        while (input == null) {
            println("How many players? 1 - 2")
            val inputString = readlnOrNull()

            if (inputString != null) {
                try {
                    input = inputString.toInt()
                    if (input !in 1..maxPlayers) {
                        println("$rot Invalid number of players. Please enter a 1 or 2. $reset")
                        input = null
                    }
                } catch (e: NumberFormatException) {
                    println("$rot Invalid input. Please enter 1 or 2. $reset")
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
        if (listOfPlayers.size == 2) {
            if (roundCount == 1) println("\n Game started. Round $roundCount \n")
            else println("\n New round started. Round $roundCount \n ")
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
        println("You have ${player.jokers.size} jokers to use:")
        for (joker in player.jokers) println(joker.type)
        println("which type of joker do you want to use? Joker50 / Joker100")

        currentQuestion = when (question) {
            is MultipleChoiceQuestion -> player.useJoker(question)
            is TrueOrFalseQuestion -> player.useJoker(question)
            else -> question
        }
        return true
    }


    fun validateAnswer(player: Player): Boolean {
        var isAnswerCorrect = false

        println("Checking answer...")
        Thread.sleep(500)

        if (player.answerMultipleChoice == currentQuestion.correctAnswer || player.answerTrueOfFalse == currentQuestion.correctAnswer) {
            println("\n ${GREEN_BACKGROUND}E ${player.name} ✅ Correct answer! ✅ $RESET \n ")
            isAnswerCorrect = true
            player.score += 5
            player.account += player.scoresList.sum() * 100
        } else {
            println("\n $RED_BACKGROUND ${player.name} ❌ Wrong answer! ❌ $RESET \n")
            player.lives -= 1
        }
        println("Scores: ${player.scoresList.sum()}")
        println("Lives: ${player.lives}")
        println("Account: ${player.account}")
        return isAnswerCorrect
    }

    fun defineWinner(): Boolean {

        for (player in listOfPlayers) {
            player.scoresList.add(player.score)

        }

        when {
            listOfPlayers.first().scoresList.last() > listOfPlayers.last().scoresList.last() -> {
                println("\n$GREEN_BACKGROUND${listOfPlayers.first().name} won this round --> ${listOfPlayers.first().scoresList.last()} Scores $RESET\n")

                winnersList.add(listOfPlayers.first())
                losersList.add(listOfPlayers.last())
            }

            listOfPlayers.last().scoresList.last() > listOfPlayers.first().scoresList.last() -> {
                println("\n$GREEN_BACKGROUND${listOfPlayers.last().name} won this round --> ${listOfPlayers.last().scoresList.last()} Scores$RESET\n")
                winnersList.add(listOfPlayers.last())
                losersList.add(listOfPlayers.first())
            }

            else -> {

                println(
                    """$YALLOW_BACKGROUND
                        
                It's a tie!
                ${listOfPlayers.first().name} --> ${listOfPlayers.first().scoresList.last()} Scores
                ${listOfPlayers.last().name}  --> ${listOfPlayers.last().scoresList.last()} Scores
            $RESET
                 
            """.trimIndent()
                )
                losersList.add(listOfPlayers.first())
                losersList.add(listOfPlayers.last())
                winnersList.add(listOfPlayers.first())
                winnersList.add(listOfPlayers.last())

            }
        }

        if (roundCount > 1) newRoundScoresUpdate(winnersList[winnersList.size - 2], losersList[losersList.size - 2])

        return winnerExists
    }


    fun startNewRound(winnerList: MutableList<Player>): Boolean {
        var player: Player
        if (winnerList.isEmpty()) {
            player = listOfPlayers.first()
        } else {
            player = winnerList.last()
        }
        var playNewRoundInput: String?
        var newRound = false
        if (endGame() && roundCount < 3) {
            println("${player.name}, as the last winner, you can decide if you want to play a new round.")
            println(
                """
                    ${BLUE_BACKGROUND} To play another round, you must bet all of your scores.
                    IF you win, you your scores will be multiplied by 3x.
                     
                     ... now, if you lose... your scores will be switched with your opponents scores 🤡
                     
            $RESET""".trimIndent()
            )

            println(" \n ${player.name}, would you like to play another round? ${GREEN_BACKGROUND}Yes$RESET / ${RED_BACKGROUND}No $RESET\n")

            playNewRoundInput = readln().lowercase()
            if (playNewRoundInput.contains("y")) {
                newRound = true
                roundCount++
                for (player in listOfPlayers) {
                    player.resetPlayer()
                }
            }
        } else {
            endGame()
        }

        return newRound
    }

    private fun multiplyScores(player: Player) {
        if (roundCount > 1) {
            val newPlayerScores = player.scoresList.map { it * 3 }
            player.scoresList = newPlayerScores.toMutableList()
            println("\n${player.name}: Your scores have been multiplied!! New scores: ${player.scoresList.sum()}\n")
        }

    }

    private fun newRoundScoresUpdate(lastWinner: Player, otherPlayer: Player) {
        if (roundCount > 1 && winnersList.isNotEmpty()) {

            when {
                lastWinner.scoresList.last() > otherPlayer.scoresList.last() -> {
                    multiplyScores(lastWinner)
                }


                lastWinner.scoresList.last() == otherPlayer.scoresList.last() -> {
                    println("\nYou got lucky. Scores stay the same.\n")
                }

                else -> {
                    switchScores(lastWinner, otherPlayer)
                }
            }
        }

    }

    private fun switchScores(player1: Player, player2: Player) {
        var player1NewScores: MutableList<Int> = mutableListOf()
        var player2NewScores: MutableList<Int> = mutableListOf()

        if (player1.scoresList.sum() > player2.scoresList.sum()) {
            player1NewScores = player2.scoresList
            player1.scoresList = player1NewScores
            player2NewScores = player1.scoresList
            player2.scoresList = player2NewScores

            println(
                """
                    Your scores have been switched!
                    ${player1.name}: ${player1.scoresList.sum()} scores
                    ${player2.name}: ${player2.scoresList.sum()} scores
                    
                """.trimIndent()
            )

        } else {
            println("As ${player2.name} made overall scores are still higher than yours after this round, you stay with your scores 🤡")
        }


    }

    fun endGame(): Boolean {

        println("\nEnd of round.\n")

        for (player in listOfPlayers) {
            player.account = player.scoresList.sum() * 100
            println(
                """
                    ${player.name}: 
                    ${player.scoresList.sum()} scores
                    ${player.account} KTL
                   
                """.trimIndent()
            )
        }
        return true
    }
}
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
    open var winnersList: MutableList<Player> = mutableListOf(Player("", 0))
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
            }
        }

        if (roundCount > 1) newRoundScoresUpdate(winnersList.last(), losersList.last())
      //  if(roundCount == 3) newRoundScoresUpdate(winnersList[winnersList.size - 2], losersList[losersList.size - 2])

        // for (player in listOfPlayers) println("${player.name}: ${player.scoresList.last()} scores")

        //  if(roundCount < 3) startNewRound()

        /*       val highestScore = playersScores.maxOrNull()

               if (highestScore != null) {
                   val winners = listOfPlayers.filter { it.score == highestScore }
                   if (winners.size == 1) {
                       winner = winners[0]
                       roundWinners.add(winner)

                       println("                   ${bold}${roundWinners.last().name} won this round --> ${gruen}${roundWinners.last().score} ${reset}Scores$reset\n")
                       println("                     New account status: ${roundWinners.last().account} KTL\n")

                       winnerExists = true

                   } else {
                       println("It's a tie!")
                       winnerExists = false
                       startNewRound()
                   }
               }
               if (roundCount > 1) transferScores(listOfPlayers.first(), listOfPlayers.last())*/

        /*        for (player in listOfPlayers) {
                    println("${player.name}: ${player.score} scores. --> ${player.account} KTL.")
                }*/

        return winnerExists
    }


    fun startNewRound(player: Player): Boolean {
        var playNewRoundInput: String?
        var newRound = false
        if (endGame()) {
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
            if (playNewRoundInput == "yes") {
                newRound = true
                roundCount++
                for (player in listOfPlayers) {
                    player.resetPlayer()
                }
            }
        }

        return newRound
    }

    private fun multiplyScores(player: Player) {
        val newPlayerScores = player.scoresList.map { it * 3 }
        player.scoresList = newPlayerScores.toMutableList()
        println("\n${player.name}: Your scores have been multiplied!! New scores: ${player.scoresList.sum()}\n")
    }

    private fun newRoundScoresUpdate(lastWinner: Player, otherPlayer: Player) {
        if (roundCount > 1) {

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


        /*
         if (roundWinners[previousRoundWinnerIndex] != roundWinners.last()) {
             player2.account += (player1.account / 2)
             player1.account /= 2
             previousRoundWinnerIndex++
         } else roundWinners[previousRoundWinnerIndex].account *= 3*/
    }

    private fun switchScores(player1: Player, player2: Player) {

        var player1NewScores: MutableList<Int> = mutableListOf()
        var player2NewScores: MutableList<Int> = mutableListOf()

        player1NewScores = player2.scoresList
        player2NewScores = player1.scoresList
        player1.scoresList = player1NewScores
        player2.scoresList = player2NewScores
        println(
            """
                    Your scores have been switched!
                    ${player1.name}: ${player1.scoresList.sum()} scores
                    ${player2.name}: ${player2.scoresList.sum()} scores
                    
                """.trimIndent()
        )
    }

    fun endGame(): Boolean {

        println("           \nEnd of round.\n")

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
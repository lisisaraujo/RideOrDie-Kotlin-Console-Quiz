package main

import MachinePlayer
import kotlinQuestions
import main.Colours.*
import main.Players.HumanPlayer
import main.Players.Player
import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion

open class Quiz(

) {
    var listOfPlayers: MutableList<Player> = mutableListOf()
    var listOfQuestions = kotlinQuestions
    var roundCount: Int = 1
    var winnerExists = false
    var winner: Player = Player("", 0)
    var currentQuestion: Question = listOfQuestions.random()
    var maxPlayers = 2
    var filteredQuestions: MutableList<Question> = mutableListOf()
    var winnersList: MutableList<Player> = mutableListOf()
    var losersList: MutableList<Player> = mutableListOf()

    // ask for how many players
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

    // for each player input, generate a player
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

    // check if there is enough players to start game. If not, asf to play with AI
    fun startGame() {
        val numOfPlayers = numOfPlayers()
        var i = 1
        repeat(numOfPlayers) {
            generatePlayer()
        }

        println(" --------- Players in this round: --------- \n")
        for (player in listOfPlayers) {
            println("$i. ${player.name}")
            i++
        }
        if (listOfPlayers.size == 2) {
            if (roundCount == 1) println("\n Game started. Round $roundCount \n")
            else println("\n New round started. Round $roundCount \n ")
        } else {
            println("\nNot enough players for the game to start. Let another player join. \n")
            println("\nWould you like to play against the machine? Yes / No \n")
            val machinePlayAnswer = readln().lowercase()
            if (machinePlayAnswer.contains("y")) listOfPlayers.add(MachinePlayer("Machine3000", 18))
            else generatePlayer()
        }

    }


    // generate question for each player
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

    // if players answer is 'JOKER', ask which joker they want to use
    // depending on the type o joker, the joker will have a different outcome
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

    // check if answer is correct or wrong. Update scores and lives depending on the outcome
    fun validateAnswer(player: Player): Boolean {
        var isAnswerCorrect = false

        println("Checking answer...")
        Thread.sleep(500)

        if (player.answerMultipleChoice == currentQuestion.correctAnswer || player.answerTrueOfFalse == currentQuestion.correctAnswer) {
            println("\n ${GREEN_BACKGROUND} ${player.name} ✅ Correct answer! ✅ $RESET \n ")
            isAnswerCorrect = true
            player.score += 5
            player.account += player.scoresList.sum() * 100
        } else {
            println("\n $RED_BACKGROUND ${player.name} ❌ Wrong answer! ❌ $RESET \n")
            player.lives -= 1
        }
        printPlayersScores(player)
        return isAnswerCorrect
    }

    // checks who won in the current round
    fun defineWinner(): Boolean {

        for (player in listOfPlayers) {
            player.scoresList.add(player.score)

        }
        when {
            listOfPlayers.first().scoresList.last() > listOfPlayers.last().scoresList.last() -> {
                println("\n 🚀$gruen${listOfPlayers.first().name} won this round --> ${listOfPlayers.first().scoresList.last()} Scores $reset 🚀\n")

                winnersList.add(listOfPlayers.first())
                losersList.add(listOfPlayers.last())
            }

            listOfPlayers.last().scoresList.last() > listOfPlayers.first().scoresList.last() -> {
                println("\n🚀 $gruen${listOfPlayers.last().name} won this round --> ${listOfPlayers.last().scoresList.last()} Scores$reset 🚀\n")
                winnersList.add(listOfPlayers.last())
                losersList.add(listOfPlayers.first())
            }

            else -> {

                println(
                    """     
               $gelb It's a tie! 🤼
               ${listOfPlayers.first().name} --> ${listOfPlayers.first().scoresList.last()} Scores
               ${listOfPlayers.last().name}  --> ${listOfPlayers.last().scoresList.last()} Scores
           
                 
            $reset """.trimIndent()
                )
                if (roundCount == 1) {
                    losersList.add(listOfPlayers.first())
                    losersList.add(listOfPlayers.last())
                    winnersList.add(listOfPlayers.first())
                    winnersList.add(listOfPlayers.last())
                }


            }
        }

        if (roundCount > 1) newRoundScoresUpdate(winnersList.first(), losersList.first())

        return winnerExists
    }

    // asks last winner if they want to start a new round
    // if yes, resets players scores and starts a new round on the next level
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
            println("")
            println(
                """
                    $gruen${player.name}$reset, as the last winner, you can decide if you want to play a new round.
                    
                    To play another round, you must bet all of your scores.
                    
                    IF you win, your scores will be multiplied by 3x.
                    ... now, if you lose... your scores will be switched with your opponents scores 🤡
                     
           """.trimIndent()
            )

            println("\n$gruen${player.name}$reset, would you like to play another round? ${GREEN_BACKGROUND}Yes$RESET / ${RED_BACKGROUND}No $RESET\n")

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
            Thread.sleep(300)
            println(
                """
                ${player.name}: Your scores have been multiplied!! 🎉 
                
                New scores: ${player.scoresList.sum()} 😎
                
            """.trimIndent()
            )
        }

    }

    private fun switchScores(player1: Player, player2: Player) {
        var player1NewScores: MutableList<Int> = mutableListOf()
        var player2NewScores: MutableList<Int> = mutableListOf()
        Thread.sleep(300)
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

    private fun newRoundScoresUpdate(lastWinner: Player, otherPlayer: Player) {
        if (roundCount > 1 && winnersList.isNotEmpty()) {

            when {
                lastWinner.scoresList.last() > otherPlayer.scoresList.last() -> {
                    multiplyScores(lastWinner)
                }


                lastWinner.scoresList.last() == otherPlayer.scoresList.last() -> {
                    Thread.sleep(300)
                    println("\nYou got lucky. Scores stay the same.\n")

                }

                else -> {
                    switchScores(lastWinner, otherPlayer)
                }
            }
        }

    }


    private fun printPlayersScores(player: Player) {
        Thread.sleep(500)
        println(
            """
            ~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~
                         Scores: ${player.score}
                         Lives: ${player.lives}
                         Account: ${player.account}
            ~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~
        """.trimIndent()
        )
        Thread.sleep(500)
        println()
        println("${blau}Next question...$reset")
        println()
        Thread.sleep(1000)
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

    open fun finalWinner() {
        if (roundCount >= 3) {
            if (listOfPlayers.first().account > listOfPlayers.last().account) winner =
                listOfPlayers.first()
            else winner = listOfPlayers.last()

            println("No questions left.")
            println(
                """$PURPLE_BACKGROUND
------------------------------------------------------------------------------------------------------------------------------------
                                    The winner is:
                                   
                                    ${winner.name}
                                    
                                    YOU WON ${winner.account} new Kotlin Skills!!! 🤓
                                    
                                    You are ready for the next module. See you there!! 👋🏻
------------------------------------------------------------------------------------------------------------------------------------
    
$RESET""".trimIndent()
            )
        }
    }
}
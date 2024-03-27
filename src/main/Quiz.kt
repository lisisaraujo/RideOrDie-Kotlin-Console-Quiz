package main

class Quiz(


    ) {
    var listOfPlayers: MutableList<Player> = mutableListOf()
    val listOfQuestions: MutableList<Question> = kotlinQuestions
    var roundCount: Int = 1
    var winnerExists = false
    var winner = ""
    var currentQuestion: Question = listOfQuestions.random()


    fun startGame() {
        println("Define the two players for this round: ")
        print("Player 1 name: ")
        var player1Name = readln().lowercase()
        print("Player 1 age: ")
        var player1Age = readln().toInt()
        var player1 = Player(player1Name, player1Age)
        player1.ageCheck()
      print("Player 2 name: ")
        var player2Name = readln().lowercase()
        print("Player 2 age: ")
        var player2Age = readln().toInt()
        var player2 = Player(player2Name, player2Age)
        player2.ageCheck()
        listOfPlayers.add(player1)
       listOfPlayers.add(player2)

        if (listOfPlayers.size >= 2) {
            if (roundCount < 1) println("Game started.Round $roundCount")
            else println("New game started. Round $roundCount")
            roundCount++
        } else println("Not enough players for the game to start. Let another player join.")
    }

    fun generateQuestion(): String {
        currentQuestion = listOfQuestions.random()
        currentQuestion.showQuestion()
        for(player in listOfPlayers){
            player.questions.add(currentQuestion)
        }
        listOfQuestions.remove(currentQuestion)
        return currentQuestion.questionText
    }
    fun validateAnswer(player: Player): Boolean {
        var isAnswerCorrect = false
        if (player.answer == currentQuestion.correctAnswer) {
            println("--------- Correct answer! ---------")
            isAnswerCorrect = true
            player.score += 5
        } else {
            println("--------- Wrong answer! ---------")
            player.lives -= 1
        }

        return isAnswerCorrect
    }

    fun defineWinner(): Boolean {

        when {
            listOfPlayers.first().score > listOfPlayers.last().score -> {
                winner = listOfPlayers.first().name
                winnerExists = true
                println("$winner is the winner!")
            }

            listOfPlayers.first().score < listOfPlayers.last().score -> {
                winner = listOfPlayers.last().name
                winnerExists = true
                println("$winner is the winner!")
            }

            else -> println("It's a tie!")
        }
        return winnerExists
    }

    fun endGame() {
        if (winnerExists) println("End of game. $winner won.")
        return
    }
}
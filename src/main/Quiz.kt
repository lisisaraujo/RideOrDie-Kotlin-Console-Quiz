package main

class Quiz(


) {
    var listOfPlayers: MutableList<Player> = mutableListOf()
    val listOfQuestions: MutableList<Question> = kotlinQuestions
    var roundCount: Int = 1
    var winnerExists = false
    var winner: Player = Player("random", 0)
    var currentQuestion: Question = listOfQuestions.random()


    fun startGame() {
        println("Define the two players for this round.")
        println("Player 1: ")
        generatePlayer()
        println("Player 2: ")
        generatePlayer()

        if (listOfPlayers.size >= 2) {
            if (roundCount < 1) println("Game started. Round $roundCount")
            else println("New game started. Round $roundCount")
            roundCount++
        } else {
            println("Not enough players for the game to start. Let another player join.")
         generatePlayer()
        }
    }

    fun generatePlayer(){
        print("Name: ")
        val name = readln().lowercase()
        print("Age: ")
        val age = readln().toInt()
        val player = Player(name, age)
        player.ageCheck()
        listOfPlayers.add(player)
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

    fun useJokerQuestion(player: Player, question: Question) {
        println("${player.name}, Would you like to use a joker? Yes / No")
        val answer = readln().lowercase()
        if (answer == "yes") player.useJoker(question)
        else return
    }

    fun validateAnswer(player: Player): Boolean {
        var isAnswerCorrect = false
        if (player.answer - 1 == currentQuestion.correctAnswer) {
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

        when {
            listOfPlayers.first().score > listOfPlayers.last().score -> {
                winner = listOfPlayers.first()
                winnerExists = true
                println("${winner.name} is the winner!")
            }

            listOfPlayers.first().score < listOfPlayers.last().score -> {
                winner = listOfPlayers.last()
                winnerExists = true
                println("${winner.name} is the winner!")
            }

            else -> println("It's a tie!")
        }
        return winnerExists
    }

    fun endGame() {
        if (winnerExists) println("End of game. ${winner.name} won with ${winner.score} points")
        return
    }
}
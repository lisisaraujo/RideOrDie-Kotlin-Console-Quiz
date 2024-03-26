package main

open class Question(
    open val difficultyLevel: Int,
    open val questionText: String,
    open val category: String,
    open val possibleAnswers: List<String>,

    ) {
    open var playersAnswer = ""
    open val correctAnswer = ""
    open var isAnswerCorrect = false

    open fun showQuestion() {
        var i = 1
        println("Question: $questionText")
        for (answer in possibleAnswers) {
            println("$i. $answer")
            i++
        }

    }

    open fun playersInput(): String {
        println("Your answer:")
        playersAnswer = readln()
        return playersAnswer
    }

    fun validateAnswer(): Boolean {
        if (playersAnswer == correctAnswer) {
            println("--------- Correct answer! ---------")
            isAnswerCorrect = true
        } else println("--------- Wrong answer! ---------")
        return isAnswerCorrect
    }

/*    open fun updateScore() {
        if (isAnswerCorrect) player.score += 5
    }*/


}
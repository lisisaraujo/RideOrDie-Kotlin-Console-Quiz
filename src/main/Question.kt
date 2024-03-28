package main

open class Question(
    open val difficultyLevel: Int,
    open val questionText: String,
    open val category: String,
    open val possibleAnswers: List<String>,
    var correctAnswer: Int
    ) {

    open fun showQuestion() {
        var i = 1
        println("\n Question: $questionText \n")
        for (answer in possibleAnswers) {
            println("$i. $answer")
            i++
        }
    }
}
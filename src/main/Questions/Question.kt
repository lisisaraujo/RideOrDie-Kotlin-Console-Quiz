package main.Questions

import main.Colours.*

open class Question(
    open val difficultyLevel: Int,
    open val questionText: String,
    open val category: String,
    open val possibleAnswers: List<String>,
    open val correctAnswer: Any = 0
) {


    open fun showQuestion() {
        println("$blau \n ----------------  $category question: level $difficultyLevel ----------------  \n $reset")
        println("\n Question: $questionText \n")
        for (answer in possibleAnswers) {
            println(answer)
        }
        println()
    }

}
package main.Questions

open class Question(
    open val difficultyLevel: Int,
    open val questionText: String,
    open val category: String,
    open val possibleAnswers: List<String>,
    open val correctAnswer: Any = 0
) {


    open fun showQuestion() {
        println("-------- $category questions: level $difficultyLevel --------")

        println("\n Question: $questionText \n")
        for (answer in possibleAnswers) {
            println(answer)
        }

    }

}
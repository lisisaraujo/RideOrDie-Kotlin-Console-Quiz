package main.Questions

open class Question(
    open val difficultyLevel: Int,
    open val questionText: String,
    open val category: String,
    open val possibleAnswers: List<String>,
    open var correctAnswer: Int
) {

    open fun showQuestion() {
        println("-------- $category questions --------")
    }
}
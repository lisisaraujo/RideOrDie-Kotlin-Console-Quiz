package main.Questions

class TrueOrFalseQuestion(
    difficultyLevel: Int,
    questionText: String,
    category: String,
    possibleAnswers: List<String>,
    override var correctAnswer: Boolean = false
) : Question(
    difficultyLevel,
    questionText,
    category,
    possibleAnswers,

) {


/*
    override fun showQuestion() {
        println("-------- $category questions --------")

        println("\n Question: $questionText \n")
        for (answer in possibleAnswers) {
            println(answer)
        }

    }
*/

}
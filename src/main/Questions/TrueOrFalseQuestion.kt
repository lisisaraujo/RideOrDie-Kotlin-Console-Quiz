package main.Questions

class TrueOrFalseQuestion(
    difficultyLevel: Int,
    questionText: String,
    category: String,
    possibleAnswers: List<String>,
    correctAnswer: Int
) : Question(
    difficultyLevel,
    questionText,
    category,
    possibleAnswers,
    correctAnswer
) {

    override fun showQuestion() {
        super.showQuestion()
        var i = 1
        println("\n Question: $questionText \n")
        for (answer in possibleAnswers) {
            println("$i. $answer")
            i++
        }
    }
}
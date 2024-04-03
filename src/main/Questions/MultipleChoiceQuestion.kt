package main.Questions

class MultipleChoiceQuestion(
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
        println("-------- $category questions --------")

        println("\n Question: $questionText \n")
        for (answer in possibleAnswers) {
            println(answer)
        }

    }
}
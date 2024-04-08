package main.Questions

class MultipleChoiceQuestion(
    difficultyLevel: Int,
    questionText: String,
    category: String,
    possibleAnswers: List<String>,
    override var correctAnswer: Int = 0

    ) : Question(
    difficultyLevel,
    questionText,
    category,
    possibleAnswers,

    ) {



/*    override fun showQuestion() {
        println("-------- $category questions --------")

        println("\n Question: $questionText \n")
        for (answer in possibleAnswers) {
            println(answer)
        }

    }*/
}

package main.questions

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


}

package main.questions

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



}
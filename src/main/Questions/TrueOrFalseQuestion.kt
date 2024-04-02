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

}
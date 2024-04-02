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

}
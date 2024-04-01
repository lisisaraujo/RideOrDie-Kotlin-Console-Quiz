package main

import main.Questions.MultipleChoiceQuestion

val kotlinQuestion1 = MultipleChoiceQuestion(
    difficultyLevel = 1,
    questionText = "What is Kotlin used for?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. Developing Android apps, web applications, and software.",
        "2. Only for developing iOS apps.",
        "3. Exclusively for web development.",
        "4. Limited to scientific computing."
    ),
    correctAnswer = 1
)

val kotlinQuestion2 = MultipleChoiceQuestion(
    difficultyLevel = 1,
    questionText = "Difference between Kotlin and Java?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. Java is newer and safer than Kotlin.",
        "2. Kotlin is newer, safer, and more concise.",
        "3. Kotlin is a subset of Java.",
        "4. Java is more concise than Kotlin."
    ),
    correctAnswer = 2
)

val kotlinQuestion3 = MultipleChoiceQuestion(
    difficultyLevel = 1,
    questionText = "Difference between val and var in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. Both val and var are immutable.",
        "2. val can be changed, var is constant.",
        "3. val is immutable, var is mutable.",
        "4. val and var are interchangeable in Kotlin."
    ),
    correctAnswer = 3
)

val kotlinQuestion4 = MultipleChoiceQuestion(
    difficultyLevel = 1,
    questionText = "Difference between function and method in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(

        "1. Functions can only be used in classes.",
        "2. Methods are standalone, functions are associated with classes.",
        "3. Functions cannot have parameters, methods can.",
        "4. Function is standalone, method is associated with a class.",
    ),
    correctAnswer = 4
)

val kotlinQuestion5 = MultipleChoiceQuestion(
    difficultyLevel = 1,
    questionText = "Difference between data class and regular class in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. Data class has default implementations, regular class does not.",
        "2. Data class cannot have properties.",
        "3. Regular class has default implementations.",
        "4. Data class is less flexible than a regular class."
    ),
    correctAnswer = 1
)
val kotlinQuestions: MutableList<MultipleChoiceQuestion> =
    mutableListOf(kotlinQuestion1, kotlinQuestion2, kotlinQuestion3, kotlinQuestion4, kotlinQuestion5)


fun main() {

    val quiz = Quiz()
    quiz.startGame()
    val player1 = quiz.listOfPlayers.first()
    val player2 = quiz.listOfPlayers.last()

    if (player1.ageCheck() && player2.ageCheck()) {
        do {
            quiz.generateQuestion()
            quiz.useJokerQuestion(player1, quiz.currentQuestion)
            player1.answer()
            quiz.useJokerQuestion(player2, quiz.currentQuestion)
            player2.answer()
            quiz.validateAnswer(player1)
            quiz.validateAnswer(player2)
        } while (quiz.listOfQuestions.size > 0)

        quiz.defineWinner()
        quiz.endGame()
    }


}
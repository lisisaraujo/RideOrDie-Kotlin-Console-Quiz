package main

val kotlinQuestion1 = Question(
    difficultyLevel = 1,
    questionText = "What is Kotlin used for?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "Developing Android apps, web applications, and software.",
        "Only for developing iOS apps.",
        "Exclusively for web development.",
        "Limited to scientific computing."
    ),
    correctAnswer = 1
)

val kotlinQuestion2 = Question(
    difficultyLevel = 1,
    questionText = "Difference between Kotlin and Java?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "Java is newer and safer than Kotlin.",
        "Kotlin is newer, safer, and more concise.",
        "Kotlin is a subset of Java.",
        "Java is more concise than Kotlin."
    ),
    correctAnswer = 2
)

val kotlinQuestion3 = Question(
    difficultyLevel = 1,
    questionText = "Difference between val and var in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "Both val and var are immutable.",
        "val can be changed, var is constant.",
        "val is immutable, var is mutable.",
        "val and var are interchangeable in Kotlin."
    ),
    correctAnswer = 3
)

val kotlinQuestion4 = Question(
    difficultyLevel = 1,
    questionText = "Difference between function and method in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(

        "Functions can only be used in classes.",
        "Methods are standalone, functions are associated with classes.",
        "Functions cannot have parameters, methods can.",
        "Function is standalone, method is associated with a class.",
    ),
    correctAnswer = 4
)

val kotlinQuestion5 = Question(
    difficultyLevel = 1,
    questionText = "Difference between data class and regular class in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "Data class has default implementations, regular class does not.",
        "Data class cannot have properties.",
        "Regular class has default implementations.",
        "Data class is less flexible than a regular class."
    ),
    correctAnswer = 1
)
val kotlinQuestions: MutableList<Question> =
    mutableListOf(kotlinQuestion1, kotlinQuestion2, kotlinQuestion3, kotlinQuestion4, kotlinQuestion5)


fun main() {

    val quiz = Quiz()
    quiz.startGame()
    val player1 = quiz.listOfPlayers.first()
    val player2 = quiz.listOfPlayers.last()

    if (player1.ageCheck() && player2.ageCheck()) {
        do {
            quiz.generateQuestion()
            player1.answer()
            player2.answer()
            quiz.validateAnswer(player1)
            quiz.validateAnswer(player2)
        } while (quiz.listOfQuestions.size > 0)

        quiz.defineWinner()
        quiz.endGame()
    }


}
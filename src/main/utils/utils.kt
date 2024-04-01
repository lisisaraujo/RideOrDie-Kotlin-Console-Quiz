package main.utils

import main.Questions.MultipleChoiceQuestion

// kotlin questions (multiple-choice)

val kotlinQuestion1 = MultipleChoiceQuestion(
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

val kotlinQuestion2 = MultipleChoiceQuestion(
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

val kotlinQuestion3 = MultipleChoiceQuestion(
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

val kotlinQuestion4 = MultipleChoiceQuestion(
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

val kotlinQuestion5 = MultipleChoiceQuestion(
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
 val kotlinQuestions: MutableList<MultipleChoiceQuestion> =
    mutableListOf(kotlinQuestion1, kotlinQuestion2, kotlinQuestion3, kotlinQuestion4, kotlinQuestion5)

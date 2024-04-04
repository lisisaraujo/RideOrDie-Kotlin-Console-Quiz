import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion
import kotlin.jvm.internal.Intrinsics.Kotlin


// multiple-choice kotlin questions

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
val kotlinMultipleChoiceQuestions: MutableList<MultipleChoiceQuestion> =
    mutableListOf(kotlinQuestion1, kotlinQuestion2, kotlinQuestion3, kotlinQuestion4, kotlinQuestion5)

// true of false kotlin questions

val kotlinQuestion6 = TrueOrFalseQuestion(2,"Kotlin is a purely functional programming language.", "Kotlin", listOf("1. True", "2. False"), true )
val kotlinQuestion7 = TrueOrFalseQuestion(2,"Kotlin's standard library does not include extensions for working with collections.", "Kotlin", listOf("1. True", "2. False"), true )
val kotlinQuestion8 = TrueOrFalseQuestion(2,"Kotlin's null safety feature prevents null pointer exceptions at compile time.", "Kotlin", listOf("1. True", "2. False"), false )
val kotlinQuestion9 = TrueOrFalseQuestion(2,"In Kotlin, the \"when\" expression is equivalent to Java's \"switch\" statement.", "Kotlin", listOf("1. True", "2. False"), false )
val kotlinQuestion10 = TrueOrFalseQuestion(2,"Kotlin's coroutines are primarily used for concurrent programming tasks.", "Kotlin", listOf("1. True", "2. False"), false )

val kotlinTrueOrFalseQuestions: MutableList<TrueOrFalseQuestion> = mutableListOf(kotlinQuestion6, kotlinQuestion7, kotlinQuestion8, kotlinQuestion9, kotlinQuestion10)

val kotlinQuestions: MutableList<Question> = mutableListOf()


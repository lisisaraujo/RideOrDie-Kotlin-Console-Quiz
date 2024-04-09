import main.Questions.MultipleChoiceQuestion
import main.Questions.Question
import main.Questions.TrueOrFalseQuestion
import kotlin.jvm.internal.Intrinsics.Kotlin


// multiple-choice kotlin questions

val L1kotlinQuestion1 = MultipleChoiceQuestion(
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

val L1kotlinQuestion2 = MultipleChoiceQuestion(
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

val L1kotlinQuestion3 = MultipleChoiceQuestion(
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

val L1kotlinQuestion4 = MultipleChoiceQuestion(
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

val L1kotlinQuestion5 = MultipleChoiceQuestion(
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

val L2kotlinQuestion6 = MultipleChoiceQuestion(
    difficultyLevel = 2,
    questionText = "What is a primary constructor in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. A constructor defined inside a companion object.",
        "2. A constructor with parameters defined at the class declaration.",
        "3. A constructor that can only be invoked from within the class.",
        "4. A constructor that has default parameter values."
    ),
    correctAnswer = 2
)

val L2kotlinQuestion7 = MultipleChoiceQuestion(
    difficultyLevel = 2,
    questionText = "What is the 'lateinit' keyword used for in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. To declare a variable that can only be initialized once.",
        "2. To declare a variable that can be initialized lazily.",
        "3. To delay the initialization of a variable until it is accessed.",
        "4. To declare a variable that can be assigned null."
    ),
    correctAnswer = 3
)

val L2kotlinQuestion8 = MultipleChoiceQuestion(
    difficultyLevel = 2,
    questionText = "In Kotlin, what is the purpose of the 'with' function?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. To execute a block of code on an object without its name.",
        "2. To create a new instance of a class.",
        "3. To define extension functions.",
        "4. To provide a shorthand for lambda expressions."
    ),
    correctAnswer = 1
)

val L2kotlinQuestion9 = MultipleChoiceQuestion(
    difficultyLevel = 2,
    questionText = "What is the difference between '==' and '===' operators in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. '==' compares reference equality, '===' compares value equality.",
        "2. '==' compares value equality, '===' compares reference equality.",
        "3. '==' compares object types, '===' compares values.",
        "4. '==' compares reference equality, '===' compares object types."
    ),
    correctAnswer = 2
)

val L2kotlinQuestion10 = MultipleChoiceQuestion(
    difficultyLevel = 2,
    questionText = "What does the 'it' keyword represent in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. A reference to the current class instance.",
        "2. A reference to the outer class from an inner class.",
        "3. A shorthand for the 'this' keyword.",
        "4. A reference to the single parameter in a lambda expression."
    ),
    correctAnswer = 4
)

val L3kotlinQuestion11 = MultipleChoiceQuestion(
    difficultyLevel = 3,
    questionText = "What does the 'operator' keyword do in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. It defines a custom operator function for a class.",
        "2. It specifies the precedence of operators.",
        "3. It restricts the usage of certain operators.",
        "4. It marks an inline function."
    ),
    correctAnswer = 1
)

val L3kotlinQuestion12 = MultipleChoiceQuestion(
    difficultyLevel = 3,
    questionText = "What is the purpose of the 'crossinline' keyword in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. It ensures that a lambda expression is executed asynchronously.",
        "2. It allows a lambda expression to escape its enclosing function.",
        "3. It restricts the usage of inline functions within a lambda.",
        "4. It prevents non-local returns from within a lambda expression."
    ),
    correctAnswer = 2
)

val L3kotlinQuestion13 = MultipleChoiceQuestion(
    difficultyLevel = 3,
    questionText = "What is the difference between 'internal' and 'protected' visibility modifiers in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. 'internal' restricts visibility to the same module, 'protected' restricts to subclasses.",
        "2. 'internal' restricts visibility to the same file, 'protected' restricts to the same package.",
        "3. 'internal' restricts visibility to the same package, 'protected' restricts to subclasses.",
        "4. 'internal' restricts visibility to the same class, 'protected' restricts to the same file."
    ),
    correctAnswer = 1
)

val L3kotlinQuestion14 = MultipleChoiceQuestion(
    difficultyLevel = 3,
    questionText = "What is the purpose of the 'reified' keyword in Kotlin?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. It specifies a generic type that is erased at runtime.",
        "2. It allows for the creation of reified types.",
        "3. It enables the use of reflection on generic types.",
        "4. It restricts the inheritance of generic types."
    ),
    correctAnswer = 3
)

val L3kotlinQuestion15 = MultipleChoiceQuestion(
    difficultyLevel = 3,
    questionText = "What is the 'invoke' operator in Kotlin used for?",
    category = "Kotlin",
    possibleAnswers = listOf(
        "1. To define a function with a single parameter.",
        "2. To call a function with no arguments.",
        "3. To invoke an object as if it were a function.",
        "4. To execute a function with a lambda expression."
    ),
    correctAnswer = 3
)

val kotlinMultipleChoiceQuestions: MutableList<MultipleChoiceQuestion> =
    mutableListOf(

        L1kotlinQuestion1,
        L1kotlinQuestion2,
        L1kotlinQuestion3,
        L1kotlinQuestion4,
        L1kotlinQuestion5,
        L2kotlinQuestion6,
        L2kotlinQuestion7,
        L2kotlinQuestion8,
        L2kotlinQuestion9,
        L2kotlinQuestion10,
        L3kotlinQuestion11,
        L3kotlinQuestion12,
        L3kotlinQuestion13,
        L3kotlinQuestion14,
        L3kotlinQuestion15
    )

// true of false kotlin questions

val L1kotlinQuestion16 = TrueOrFalseQuestion(
    difficultyLevel = 1,
    questionText = "Kotlin can be used for Android app development.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = true
)

val L1kotlinQuestion17 = TrueOrFalseQuestion(
    difficultyLevel = 1,
    questionText = "Kotlin supports both object-oriented and functional programming paradigms.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = true
)

val L1kotlinQuestion18 = TrueOrFalseQuestion(
    difficultyLevel = 1,
    questionText = "Kotlin is interoperable with Java, allowing seamless integration of Kotlin code into existing Java projects.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = true
)

val L1kotlinQuestion19 = TrueOrFalseQuestion(
    difficultyLevel = 1,
    questionText = "In Kotlin, 'val' keyword is used to declare mutable variables.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = false
)

val L1kotlinQuestion20 = TrueOrFalseQuestion(
    difficultyLevel = 1,
    questionText = "Kotlin provides nullable and non-nullable types to prevent null pointer exceptions.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = true
)

val L2kotlinQuestion21 = TrueOrFalseQuestion(
    2,
    "Kotlin is a purely functional programming language.",
    "Kotlin",
    listOf("1. True", "2. False"),
    true
)
val L2kotlinQuestion22 = TrueOrFalseQuestion(
    2,
    "Kotlin's standard library does not include extensions for working with collections.",
    "Kotlin",
    listOf("1. True", "2. False"),
    true
)
val L2kotlinQuestion23 = TrueOrFalseQuestion(
    2,
    "Kotlin's null safety feature prevents null pointer exceptions at compile time.",
    "Kotlin",
    listOf("1. True", "2. False"),
    false
)
val L2kotlinQuestion24 = TrueOrFalseQuestion(
    2,
    "In Kotlin, the \"when\" expression is equivalent to Java's \"switch\" statement.",
    "Kotlin",
    listOf("1. True", "2. False"),
    false
)
val L2kotlinQuestion25 = TrueOrFalseQuestion(
    2,
    "Kotlin's coroutines are primarily used for concurrent programming tasks.",
    "Kotlin",
    listOf("1. True", "2. False"),
    false
)


val L3kotlinQuestion26 = TrueOrFalseQuestion(
    difficultyLevel = 3,
    questionText = "Kotlin's extension functions can access private members of a class.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = false
)

val L3kotlinQuestion27 = TrueOrFalseQuestion(
    difficultyLevel = 3,
    questionText = "Kotlin's 'sealed' classes allow for exhaustive when statements.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = true
)

val L3kotlinQuestion28 = TrueOrFalseQuestion(
    difficultyLevel = 3,
    questionText = "Kotlin supports multiple inheritance through class inheritance.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = false
)

val L3kotlinQuestion29 = TrueOrFalseQuestion(
    difficultyLevel = 3,
    questionText = "Kotlin's 'lateinit' properties must be initialized explicitly before usage.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = false
)

val L3kotlinQuestion30 = TrueOrFalseQuestion(
    difficultyLevel = 3,
    questionText = "In Kotlin, the 'apply' function is used to call a member function on a nullable object.",
    category = "Kotlin",
    possibleAnswers = listOf("1. True", "2. False"),
    correctAnswer = false
)

val kotlinTrueOrFalseQuestions: MutableList<TrueOrFalseQuestion> =
    mutableListOf(

        L1kotlinQuestion16,
        L1kotlinQuestion17,
        L1kotlinQuestion18,
        L1kotlinQuestion19,
        L1kotlinQuestion20,
        L2kotlinQuestion21,
        L2kotlinQuestion22,
        L2kotlinQuestion23,
        L2kotlinQuestion24,
        L2kotlinQuestion25,
        L3kotlinQuestion26,
        L3kotlinQuestion27,
        L3kotlinQuestion28,
        L3kotlinQuestion29,
        L3kotlinQuestion30

    )

val kotlinQuestions: MutableList<Question> = mutableListOf()


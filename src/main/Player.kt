package main

class Player(val name: String, val age: Int, var score: Int, val jokers: MutableList<Joker>, val lives: Int) {

    fun ageCheck() {
        if (age < 12) println("Player is too young to play.")
        else println("Welcome to the game, $name!")
    }

    fun useJoker50() {
        println("You have ${jokers.size} jokers to be used")
        for (joker in jokers) println(joker.type)
        println("Would you like to use a joker?")
        val jokerRequest = readln().lowercase()
        if (jokerRequest == "yes") {
            println("which type of joker do you want to use? Joker50 / Joker100")
            val typeOfJoker = readln().lowercase()
            if (typeOfJoker == "joker50") {
                var jokerToRemove: Joker
                for (joker in jokers) {
                    if (joker.type == typeOfJoker) {
                        jokerToRemove = joker
                        jokers.remove(jokerToRemove)
                        println("Joker removed successfully!")
                    } else println("No joker $typeOfJoker found.")
                }
            }
        }
    }
}
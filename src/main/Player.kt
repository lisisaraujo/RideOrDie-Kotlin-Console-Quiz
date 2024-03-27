package main

import joker.Joker
import joker.Joker50

class Player(val name: String, val age: Int) {
    var answer: Int = 0
    var lives: Int = 3
    var score: Int = 0
    var jokers: MutableList<Joker> = mutableListOf()
    var questions: MutableList<Question> = mutableListOf()

    init {
        score = 0
        jokers = mutableListOf(Joker50(this), Joker50(this))
    }

    fun ageCheck(): Boolean {
        var isOldEnough = false
        if (age < 12) {
            println("Player is too young to play.")
        } else {
            isOldEnough = true
            println("Welcome to the game, $name!")
        }
        return isOldEnough
    }

    fun useJoker(question: Question) {
        println("You have ${jokers.size} jokers to use:")
        for (joker in jokers) println(joker.type)
        println("which type of joker do you want to use? Joker50 / Joker100")
        val typeOfJoker = readln().lowercase()

        if (typeOfJoker == "joker50") {
            var selectedJoker: Joker
            for (joker in jokers) {
                if (joker.type == typeOfJoker) {
                    selectedJoker = joker
                    joker.useJoker(question)
                    jokers.remove(selectedJoker)
                    println("Joker removed successfully!")
                } else println("No $typeOfJoker found.")
            }
        }
    }

    fun answer() {
        println("${this.name}, type your answer: ")
        answer = readln().toInt()
    }


}
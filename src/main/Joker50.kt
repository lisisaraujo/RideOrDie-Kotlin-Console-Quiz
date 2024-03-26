package main

class Joker50(type: String, answers: List<String>): Joker(type, answers) {

    override fun useJoker() {
        super.useJoker()
        println("The correct answer is either $answers")
    }
}
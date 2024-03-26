package main

fun main() {
    val playersList: MutableList<Player> = mutableListOf()
  //  val quiz = Quiz()
    val player1 = Player("Lisis", 30, 0, mutableListOf(Joker50("joker50", listOf("a", "b")), Joker50("joker50", listOf("c", "d"))), 5)
    var kotlinQuestions: List<MultipleChoiceQuestion> = listOf()
    player1.useJoker50()
    println(player1.jokers)
}
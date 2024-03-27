package main

open class Joker(open var type: String, var player: Player) {

    open fun useJoker(question: Question) {
        println("🃏🃏🃏🃏🃏 Joker $type in use 🃏🃏🃏🃏🃏")
    }
}
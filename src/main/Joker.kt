package main

open class Joker (open var type: String, open var answers: List<String>){

    open fun useJoker(){
        println("🃏🃏🃏🃏🃏 Joker $type in use 🃏🃏🃏🃏🃏")
    }
}
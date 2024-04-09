package main

import MachinePlayer
import kotlinMultipleChoiceQuestions
import kotlinQuestions
import kotlinTrueOrFalseQuestions
import main.Colours.*
import java.awt.Color.orange


fun main() {

    kotlinQuestions.addAll(kotlinMultipleChoiceQuestions)
    kotlinQuestions.addAll(kotlinTrueOrFalseQuestions)


    val quiz = Quiz()
    println(
        """$BOLD $rot
                     (      (      (                  )    (        (       (             
            )\ )   )\ )   )\ )            ( /(    )\ )     )\ )    )\ )          
            (()/(  (()/(  (()/(    (       )\())  (()/(    (()/(   (()/(   (      
             /(_))  /(_))  /(_))   )\     ((_)\    /(_))    /(_))   /(_))  )\     
            (_))   (_))   (_))_   ((_)      ((_)  (_))     (_))_   (_))   ((_)  $reset  $gelb 
            | _ \  |_ _|   |   \  | __|    / _ \  | _ \     |   \  |_ _|  | __|   
            |   /   | |    | |) | | _|    | (_) | |   /     | |) |  | |   | _|    
            |_|_\  |___|   |___/  |___|    \___/  |_|_\     |___/  |___|  |___|   $reset $rot
                         .-.-.  .-.-.  .-.-.  .-.-.  .-.-.  .-.-. 
                        ($gelb K $reset$rot.' ($gelb o $rot.' ( ${gelb}t$rot .' ( ${gelb}l$rot .' ( ${gelb}i$rot .' ( ${gelb}n$rot .' 
                         `.(    `.(    `.(    `.(    `.(    `.(   $reset $RESET
                                                                              
     $reset""".trimIndent()
    )

    println("""
        $BOLD $gruen                         🚀 Welcome to our final Kotlin quiz! 🚀 $RESET $reset
        
        
        """.trimIndent())

    quiz.startGame()
    Thread.sleep(1000)
    println("\n $blauBack 🃏 Type 'joker' to request a joker at any time 🃏 $reset \n")

    fun playRound() {

        do {
            for (player in quiz.listOfPlayers) {
                if(player.lives > 0) {
                    quiz.generateQuestion()

                   println("${player.name}, type your answer: ")
                    if (player !is MachinePlayer) {
                        player.playerAnswer = readln().lowercase()
                        if (player.playerAnswer != "joker") {
                            player.answer(quiz.currentQuestion)
                        } else {
                            quiz.useJokerQuestion(player, quiz.currentQuestion)
                            player.answer(quiz.currentQuestion)
                        }
                    } else {
                        player.answer(quiz.currentQuestion)
                    }
                    Thread.sleep(300)
                    quiz.validateAnswer(player)
                    Thread.sleep(300)
                } else {
                    println("               🏴‍☠️${player.name}, you lost all your lives. Game over! 🏴‍☠️")
                    quiz.defineWinner()
                    quiz.endGame()
                    return
                }

            }
        } while (quiz.filteredQuestions.isNotEmpty())

        quiz.defineWinner()
        quiz.endGame()
    }

    do {
        playRound()
    } while (quiz.startNewRound() && quiz.roundCount <= 3)

    println("No questions left.")
    quiz.defineWinner()
    quiz.endGame()
}
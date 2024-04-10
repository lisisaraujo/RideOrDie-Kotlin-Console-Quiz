package main

import MachinePlayer
import kotlinMultipleChoiceQuestions
import kotlinQuestions
import kotlinTrueOrFalseQuestions
import main.Colours.*
import main.Players.Player


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

    println(
        """
        $BOLD $gruen                         🚀 Welcome to our final Kotlin quiz! 🚀 $RESET $reset
        
        
        """.trimIndent()
    )

    quiz.startGame()
    Thread.sleep(500)
    println("\n $blauBack       🃏 Type 'joker' to request a joker at any time🃏         $reset \n")
    Thread.sleep(1000)

    println("                       ${gruen}LETS GO!$reset\n")
    Thread.sleep(300)
    println("                       Round ${quiz.roundCount}\n")
    Thread.sleep(300)

    fun playRound() {

        do {
            for (player in quiz.listOfPlayers) {
                if (player.lives > 0) {
                    Thread.sleep(300)
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
                    return
                }

            }
        } while (quiz.filteredQuestions.isNotEmpty())

        quiz.defineWinner()

    }

    do {
        playRound()
    } while (quiz.startNewRound(quiz.winnersList) && quiz.roundCount <= 3)

    if (quiz.roundCount >= 3) {
        if (quiz.listOfPlayers.first().account > quiz.listOfPlayers.last().account) quiz.winner =
            quiz.listOfPlayers.first()
        else quiz.winner = quiz.listOfPlayers.last()

        println("No questions left.")
        println(
            """$PURPLE_BACKGROUND
    The winner is:
   
    ${quiz.winner.name}
    
    
$RESET""".trimIndent()
        )
    }

}
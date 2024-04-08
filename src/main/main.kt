package main

import MachinePlayer
import kotlinMultipleChoiceQuestions
import kotlinQuestions
import kotlinTrueOrFalseQuestions
import main.Colours.*


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
(_))   (_))   (_))_   ((_)      ((_)  (_))     (_))_   (_))   ((_)    
| _ \  |_ _|   |   \  | __|    / _ \  | _ \     |   \  |_ _|  | __|   
|   /   | |    | |) | | _|    | (_) | |   /     | |) |  | |   | _|    
|_|_\  |___|   |___/  |___|    \___/  |_|_\     |___/  |___|  |___|   
                                                                      

     $reset""".trimIndent()
    )
    println("$BOLD $rot $WHITE_BACKGROUND 🤡 Welcome to final 'Ride Or Die' Kotlin quiz! 🤡 $RESET $reset")
    quiz.startGame()
    println("$blauBack 🃏 Type 'joker' to request a joker at any time 🃏 $reset")

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
                    Thread.sleep(500)
                    quiz.validateAnswer(player)
                    Thread.sleep(500)
                } else {
                    println("🏴‍☠️${player.name}, you lost all your lives. Game over! 🏴‍☠️")
                    quiz.defineWinner()
                    quiz.endGame()
                    return
                }

            }
        } while (quiz.listOfQuestions.isNotEmpty())

        quiz.defineWinner()
        quiz.endGame()
    }

    do {
        playRound()
    } while (quiz.startNewRound())
}
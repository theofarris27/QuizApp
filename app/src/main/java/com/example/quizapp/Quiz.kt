package com.example.quizapp

class Quiz(val questions: List <Question>) {
    var score = 0
    val totalQuestions = questions.size
    var currentQuestion = 0

     fun checkTrue(){
        if(getAnswer() != false){
            score++
            currentQuestion++
        }
         else{
             score--
            currentQuestion++
        }
    }
     fun checkFalse(){
        if(getAnswer() != true){
            score++
            currentQuestion++
        }
        else{
            score--
            currentQuestion++
        }
    }
     fun getAnswer(): Boolean{
        return questions.get(currentQuestion).answer
    }
     fun updateText(): String{
        return questions.get(currentQuestion).question
    }
     fun checkEnd(): Boolean {
         if (totalQuestions <= currentQuestion + 1) {
             return true
         }
         else{
             return false
         }
     }
    }

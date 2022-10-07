package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
lateinit var trueButton: Button
lateinit var falseButton: Button
lateinit var text: TextView
lateinit var onlyGroup: Group
class MainActivity : AppCompatActivity() {
    companion object{
        val TAG = "MainActivity"
    }
    private lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQuestions()
        wireWidgets()
        text.text = quiz.updateText()
        falseButton.setOnClickListener {
            quiz.checkFalse()
            text.text = quiz.updateText()
            endChecker()
        }
        trueButton.setOnClickListener {
            quiz.checkTrue()
            text.text = quiz.updateText()
            endChecker()}
    }


    private fun endChecker(){
        if(quiz.checkEnd()) {
            text.text = "" + quiz.score
            onlyGroup.visibility = View.GONE
        }
    }

    private fun loadQuestions(){
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }

        Log.d(TAG, "onCreate: $jsonString")
        val gson = Gson()
        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonString, type)
        quiz = Quiz(questions)

    }
    private fun wireWidgets(){
        trueButton = findViewById(R.id.main_button_leftButton)
        falseButton = findViewById(R.id.main_button_rightButton)
        text = findViewById(R.id.textView)
        onlyGroup = findViewById(R.id.buttonGroup)
    }
}
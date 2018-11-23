package com.example.mde12.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickIntBtn(v: View) {
        val b = v as Button
        val buttonText : String = b.text.toString()
        outputView.append(buttonText)
    }

    fun onClickDieBtn(v: View) {
        val b = v as Button
        val buttonText : String = b.text.toString()
        outputView.append(buttonText + "\n")
        // trigger die rolling logic
        rollDice(10, 20)
    }

    private fun rollDice(totalDice: Int, dieValue: Int) {
        outputView.append("Result >>> ")
        val totalDiceMin : Int = if (totalDice > 0) totalDice else 1
        for (i in 1..totalDiceMin) {
            outputView.append(Random.nextInt(0, dieValue + 1).toString() + " ")
        }
        outputView.append("\n")
    }
}

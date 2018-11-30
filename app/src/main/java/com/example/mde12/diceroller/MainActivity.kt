package com.example.mde12.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.outputView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputView.setMovementMethod(ScrollingMovementMethod())
    }

    private var totalDice: String = "0"

    fun onClickIntBtn(view: View) {
        val b = view as Button
        val buttonText : String = b.text.toString()
        outputView.append(buttonText)
        totalDice += buttonText
    }

    fun onClickDieBtn(view: View) {
        val b = view as Button
        val buttonText : String = b.text.toString()
        outputView.append(buttonText + "\n")

        rollDice(buttonText.removePrefix("d").toInt())
    }

    fun onClickDelBtn(view: View) {
        if (totalDice.length > 1) {
            totalDice = totalDice.substring(0, totalDice.length - 1)
            outputView.setText(outputView.text.substring(0, outputView.text.length - 1))
        }
    }

    fun onClickClrBtn(view: View) {
        outputView.setText(outputView.text.substring(0, outputView.text.length - (totalDice.length - 1)))
        totalDice = "0"
    }

    private fun rollDice(dieValue: Int) {
        outputView.append("Result >>> ")
        val totalDiceMin : Int = if (totalDice.toInt() > 0) totalDice.toInt() else 1
        for (i in 1..totalDiceMin) {
            outputView.append(Random.nextInt(1, dieValue + 1).toString() + " ")
        }
        outputView.append("\n\n")

        totalDice = "0"
    }
}

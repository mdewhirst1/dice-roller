package com.example.mde12.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.outputView
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var desiredNumberOfDice: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun scrollDown() {
        val scrollview : ScrollView = findViewById(R.id.outputScrollView)
        scrollview.post { scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    fun onClickIntBtn(view: View) {
        val b = view as Button
        val buttonText : String = b.text.toString()
        outputView.append(buttonText)
        desiredNumberOfDice += buttonText
    }

    fun onClickDieBtn(view: View) {
        val b = view as Button
        val buttonText : String = b.text.toString()
        outputView.append(buttonText)

        val rollOutput = rollDice(buttonText.removePrefix("d").toByte(), desiredNumberOfDice)
        outputView.append("\n\nResult >>> " + rollOutput + "\n\n")

        desiredNumberOfDice = "0"
        scrollDown()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onClickDelBtn(view: View) {
        if (desiredNumberOfDice.length > 1) {
            desiredNumberOfDice = desiredNumberOfDice.substring(0, desiredNumberOfDice.length - 1)
            outputView.text = outputView.text.substring(0, outputView.text.length - 1)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onClickClrBtn(view: View) {
        outputView.text = outputView.text.substring(0, outputView.text.length - (desiredNumberOfDice.length - 1))
        desiredNumberOfDice = "0"
    }

    private fun rollDice(dieValue: Byte, numOfDice: String): String {
        val numOfDiceInt = getDesiredDiceAsNumber(numOfDice)
        var outputText = ""

        for (i in 1..numOfDiceInt) {
            outputText += "${Random.nextInt(1, dieValue + 1)} "
        }

        if (numOfDiceInt >= 1000) {
            outputText += "\n\nDid you really need that many dice rolling?\n\n"
        }

        return outputText
    }

    fun getDesiredDiceAsNumber(desiredNumberOfDice: String): Int {
        var result = 1
        
        try {
            result = max(desiredNumberOfDice.toInt(), result)
        } catch (e : NumberFormatException) {
            result = Int.MAX_VALUE
        }

        return min(1000, result) //at most 1000. Want to remove this
    }
}

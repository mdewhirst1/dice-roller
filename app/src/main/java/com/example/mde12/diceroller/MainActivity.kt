package com.example.mde12.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.outputView
import java.math.BigInteger
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var totalDice: String = "0"

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
        totalDice += buttonText
    }

    fun onClickDieBtn(view: View) {
        val b = view as Button
        val buttonText : String = b.text.toString()
        outputView.append(buttonText)

        val rollOutput = rollDice(buttonText.removePrefix("d").toInt(), totalDice.toBigInteger())
        outputView.append("\n\nResult >>> " + rollOutput + "\n\n")

        totalDice = "0"
        scrollDown()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onClickDelBtn(view: View) {
        if (totalDice.length > 1) {
            totalDice = totalDice.substring(0, totalDice.length - 1)
            outputView.text = outputView.text.substring(0, outputView.text.length - 1)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onClickClrBtn(view: View) {
        outputView.text = outputView.text.substring(0, outputView.text.length - (totalDice.length - 1))
        totalDice = "0"
    }

    private fun rollDice(dieValue: Int, numOfDice: BigInteger): String {
        var outputText = ""

        var safeNumOfDice = numOfDice.max(BigInteger.ONE) //at least 1
        safeNumOfDice = safeNumOfDice.min(BigInteger.valueOf(1000)) //at most 1000. Want to remove this

        while (safeNumOfDice.compareTo(BigInteger.ZERO) > 0) {
            outputText = outputText + Random.nextInt(1, dieValue + 1).toString() + " "
            safeNumOfDice = safeNumOfDice.subtract(BigInteger.ONE)
        }
        return outputText
    }
}

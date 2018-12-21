package com.example.mde12.diceroller

import org.junit.Test
import org.junit.Assert.assertEquals

class MainActivityTest {

    private val testActivity = MainActivity()

    @Test
    fun `get Desired Dice As Number Returns A Number`() {
        val result = testActivity.getDesiredDiceAsNumber("123")
        assertEquals(123, result)
    }

    @Test
    fun `get Desired Dice As Number Returns At Least 1`() {
        val result = testActivity.getDesiredDiceAsNumber("0")
        assertEquals(1, result)
    }

    @Test
    fun `get Desired Dice As Number Handles Stupidly Large Numbers`() {
        val result = testActivity.getDesiredDiceAsNumber((Long.MAX_VALUE).toString())
        assertEquals(1000, result)
    }
}

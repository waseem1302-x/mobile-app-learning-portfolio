package com.example.composeinteractionlab

import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorTest {
    @Test
    fun calculateTip_returnsPercentageOfAmount() {
        assertEquals(15.0, calculateTip(amount = 100.0, percentage = 15, roundUp = false), 0.001)
    }

    @Test
    fun calculateTip_roundsUpWhenRequested() {
        assertEquals(16.0, calculateTip(amount = 101.0, percentage = 15, roundUp = true), 0.001)
    }

    @Test
    fun calculateTip_returnsZeroForNonPositiveInput() {
        assertEquals(0.0, calculateTip(amount = 0.0, percentage = 20, roundUp = false), 0.001)
        assertEquals(0.0, calculateTip(amount = -10.0, percentage = 20, roundUp = false), 0.001)
    }

    @Test
    fun parseAmountInput_acceptsDotAndCommaDecimals() {
        assertEquals(10.5, parseAmountInput("10.50")!!, 0.001)
        assertEquals(10.5, parseAmountInput("10,50")!!, 0.001)
    }

    @Test
    fun parseAmountInput_rejectsMalformedValues() {
        assertEquals(null, parseAmountInput("10.5.0"))
        assertEquals(null, parseAmountInput("10,5,0"))
        assertEquals(null, parseAmountInput(""))
    }
}

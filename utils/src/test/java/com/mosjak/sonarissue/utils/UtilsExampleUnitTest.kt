package com.mosjak.sonarissue.utils

import org.junit.Assert.assertEquals
import org.junit.Test


class UtilsExampleUnitTest {

    private val javaCalculator = UtilsSampleCalculatorJava()
    private val kotlinCalculator = UtilsSampleCalculatorKotlin()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, javaCalculator.add(2, 2))
        assertEquals(4, kotlinCalculator.add(2, 2))
    }
}

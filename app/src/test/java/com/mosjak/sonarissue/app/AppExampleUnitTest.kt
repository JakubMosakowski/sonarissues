package com.mosjak.sonarissue.app

import org.junit.Assert.assertEquals
import org.junit.Test

class AppExampleUnitTest {

    private val javaCalculator = AppSampleCalculatorJava()
    private val kotlinCalculator = AppSampleCalculatorKotlin()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, javaCalculator.add(2, 2))
        assertEquals(4, kotlinCalculator.add(2, 2))
    }
}

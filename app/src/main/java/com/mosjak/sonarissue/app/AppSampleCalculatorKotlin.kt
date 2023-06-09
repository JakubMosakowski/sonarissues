package com.mosjak.sonarissue.app

import com.mosjak.sonarissue.utils.UtilsSampleCalculatorKotlin

class AppSampleCalculatorKotlin {

    fun add(a: Int, b: Int) = a + b

    fun sub(a: Int, b: Int) = a - b

    fun subUtils(a: Int, b: Int) = UtilsSampleCalculatorKotlin().sub(a, b)
}

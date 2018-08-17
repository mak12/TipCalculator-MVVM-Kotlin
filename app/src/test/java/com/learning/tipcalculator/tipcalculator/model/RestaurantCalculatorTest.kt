package com.learning.tipcalculator.tipcalculator.model


import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author Mak
 *         <p>Created on 8/14/2018.</p>
 */
class RestaurantCalculatorTest {

    lateinit var calculator :  RestaurantCalculator

    @Before
    fun setup(){
        calculator = RestaurantCalculator()
    }

    @Test
    fun testCalculatorTip(){
        val baseTc = TipCalculation(checkAmount = 10.00)

        val testVals = listOf(baseTc.copy(tipPct = 25, tipAmount = 2.5, grandTotal = 12.50),
                baseTc.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.50),
                baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.80)
                )

        testVals.forEach { assertEquals(it ,calculator.calculateTip(it.checkAmount, it.tipPct))
        }
//        val checkInput = 10.00
//        val tipPctInput = 25
//
//        val expectedResult = TipCalculation(
//                checkAmount = checkInput,
//                tipPct = 25,
//                tipAmount = 2.50,
//                grandTotal = 12.50
//        )
//
//        assertEquals( expectedResult, calculator.calculateTip(checkInput, tipPctInput))
    }
}
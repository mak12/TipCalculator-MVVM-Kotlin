package com.learning.tipcalculator.tipcalculator.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.time.Instant

/**
 * @author Mak
 *         <p>Created on 8/14/2018.</p>
 */
class TipCalculationRepositoryTest {

    @get:Rule
    var rule : TestRule = InstantTaskExecutorRule()

    lateinit var repository : TipCalculationRepository

    @Before
    fun setup(){
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip(){

        val  tip = TipCalculation(locationName = "Oreo",
                checkAmount = 100.00, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0 )
        repository.saveTipCalculation(tip)

        assertEquals(tip , repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun loadSavedTipCalculations(){
        val  tip1 = TipCalculation(locationName = "Oreo",
                checkAmount = 100.00, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0 )
        val  tip2 = TipCalculation(locationName = "Pancake",
                checkAmount = 100.00, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0 )

        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)

        repository.loadSavedTipCalculations().observeForever { tipCalculations ->
            assertEquals(2, tipCalculations?.size)

        }
    }


}
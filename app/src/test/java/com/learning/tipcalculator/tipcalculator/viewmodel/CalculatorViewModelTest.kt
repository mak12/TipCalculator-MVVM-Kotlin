package com.learning.tipcalculator.tipcalculator.viewmodel

import android.app.Application
import com.learning.tipcalculator.tipcalculator.R
import com.learning.tipcalculator.tipcalculator.model.RestaurantCalculator
import com.learning.tipcalculator.tipcalculator.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CalculatorViewModel

    @Mock
    lateinit var mockCalculator : RestaurantCalculator

    @Mock
    lateinit var app: Application

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        stubResources(0.00 , "$0.00")
        calculatorViewModel = CalculatorViewModel(app,mockCalculator)
    }

    private fun stubResources(given : Double, returnStub : String){
        `when`(app.getString(R.string.dollar_amount,given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculateTip(){
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        stubResources(10.00 , "$10.00")
        stubResources(1.5 , "$1.50")
        stubResources(11.5 , "$11.50")

        calculatorViewModel.calculateTip()

        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)

    }

    @Test
    fun testCalculateTipBadTipPercent(){

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        verify(mockCalculator , never()).calculateTip(ArgumentMatchers.anyDouble() , ArgumentMatchers.anyInt())
    }

    @Test
    fun testCalculateTipBadCheckInput(){

        calculatorViewModel.inputCheckAmount = ""
        calculatorViewModel.inputTipPercentage = "15"

        verify(mockCalculator , never()).calculateTip(ArgumentMatchers.anyDouble() , ArgumentMatchers.anyInt())
    }

    @Test
    fun testSaveCurrentTip(){

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        val stubLocationName = "Green eggs with Bacon"

        fun setUpTipCalculation(){
            calculatorViewModel.inputCheckAmount = "10.00"
            calculatorViewModel.inputTipPercentage = "15"

            `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        }

        setUpTipCalculation()
        calculatorViewModel.calculateTip()
        calculatorViewModel.saveCurrentTip(stubLocationName)

        verify(mockCalculator).saveTipCalculation(stub.copy(locationName = stubLocationName))

        assertEquals(stubLocationName, calculatorViewModel.locationName)
    }

}
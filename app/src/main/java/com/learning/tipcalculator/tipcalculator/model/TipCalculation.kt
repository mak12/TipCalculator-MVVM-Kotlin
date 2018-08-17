package com.learning.tipcalculator.tipcalculator.model
/**
 * @author Mak
 *         <p>Created on 8/16/2018.</p>
 *        <p>Model class for Tip</p>
 */
data class TipCalculation(
        val locationName : String ="",
        val checkAmount : Double = 0.0,
        val tipPct : Int = 0,
        val tipAmount : Double = 0.0,
        val grandTotal : Double = 0.0
)
package com.learning.tipcalculator.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import java.math.RoundingMode

class RestaurantCalculator(val repository: TipCalculationRepository = TipCalculationRepository()) {
    fun calculateTip(checkInput: Double, tipPctInput: Int): TipCalculation {

        val tipAmount = (checkInput * (tipPctInput.toDouble() / 100.0))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()


        val grandTotal = checkInput + tipAmount
        return TipCalculation(
                checkAmount = checkInput,
                tipPct = tipPctInput,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

    fun saveTipCalculation(tip: TipCalculation) {
        repository.saveTipCalculation(tip)
    }

    fun loadTipCalculationByName(locationName: String): TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }
}
package com.learning.tipcalculator.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import android.view.animation.Transformation
import com.learning.tipcalculator.tipcalculator.R
import com.learning.tipcalculator.tipcalculator.model.RestaurantCalculator
import com.learning.tipcalculator.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
        app:Application ,val calculator : RestaurantCalculator = RestaurantCalculator()) : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount=""
    var inputTipPercentage= ""

    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount,lastTipCalculated.checkAmount)
    val outputTipAmount get()= getApplication<Application>().getString(R.string.dollar_amount,lastTipCalculated.tipAmount)
    val outputTotalDollarAmount get()= getApplication<Application>().getString(R.string.dollar_amount,lastTipCalculated.grandTotal)
    val locationName get() = lastTipCalculated.locationName

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc : TipCalculation){
        lastTipCalculated = tc
        notifyChange()
    }

    fun saveCurrentTip(name : String){
        val tipToSave = lastTipCalculated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)

        updateOutputs(tipToSave)
    }

    fun loadSavedTipCalculationSummaries() : LiveData<List<TipCalculationSummaryItem>>{
        return Transformations.map(calculator.loadSavedTipCalculations(),{
            tipCalculationObjects -> tipCalculationObjects.map {
                TipCalculationSummaryItem(it.locationName, getApplication<Application>().getString(R.string.dollar_amount , it.grandTotal))
        }
        })
    }

    /**
     * function to load the tip by name, when user selects it, and notify the view that variables have changed
     * */
    fun loadTipCalculation(name : String){

        val tc = calculator.loadTipCalculationByName(name)

        if (tc != null){
            inputCheckAmount = tc.checkAmount.toString()
            inputTipPercentage = tc.tipPct.toString()

            updateOutputs(tc)
            notifyChange()
        }
    }

    /**
     * function to calculate the tip when fab is clicked and notify the view that variables have changed
     * */
    fun calculateTip(){

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount!=null && tipPct!=null) {
            Log.d(TAG , "CheckAmount:$checkAmount, TipPct:$tipPct")
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }

}
private const val TAG = "CalculatorViewModel"
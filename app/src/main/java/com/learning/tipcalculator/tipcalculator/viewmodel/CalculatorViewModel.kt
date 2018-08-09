package com.learning.tipcalculator.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
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
//    fun calculateTip(){
//
//        Log.d(TAG, "calculateTipInvoked")
//
//        val checkAmount = inputCheckAmount.get()?.toDoubleOrNull()
//        val tipPct = inputTipPercentage.get()?.toIntOrNull()
//
//        if (checkAmount!=null && tipPct!=null) {
//            Log.d(TAG , "CheckAmount:$checkAmount, TipPct:$tipPct")
//            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
//            clearInputs()
//        }
//    }

    fun calculateTip(){

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount!=null && tipPct!=null) {
            Log.d(TAG , "CheckAmount:$checkAmount, TipPct:$tipPct")
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }

//    fun clearInputs(){
////        inputCheckAmount.set("0.00")
////        inputTipPercentage.set("0")
//        inputCheckAmount="0.00"
//        inputTipPercentage="0"
//        notifyChange()
//    }
}
private const val TAG = "CalculatorViewModel"
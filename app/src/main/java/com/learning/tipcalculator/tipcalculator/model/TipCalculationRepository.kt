package com.learning.tipcalculator.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
/**
 * @author Mak
 *        <p>Created on 8/16/2018.</p>
 *        <p>Repo for tip calculation</p>
 */
class TipCalculationRepository {

    private val savedTips = mutableMapOf<String, TipCalculation>()

    fun saveTipCalculation(tip : TipCalculation){
        savedTips[tip.locationName] = tip
    }

    fun loadTipCalculationByName(locationName : String) : TipCalculation?{
        return savedTips[locationName]
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        val liveData = MutableLiveData<List<TipCalculation>>()
        liveData.value = savedTips.values.toList()
        return liveData
    }
}
package com.learning.tipcalculator.tipcalculator.view

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RelativeLayout
import com.learning.tipcalculator.tipcalculator.R
import com.learning.tipcalculator.tipcalculator.databinding.ActivityTipCalculatorBinding
import com.learning.tipcalculator.tipcalculator.eventhandlers.TipsEventsHandlers
import com.learning.tipcalculator.tipcalculator.viewmodel.CalculatorViewModel

class TipCalculatorActivity : AppCompatActivity() , TipsEventsHandlers{

    lateinit var mBinding: ActivityTipCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG , "onCreate")
        //mViewModel= ViewModelProviders.of(this)[CalculatorViewModel::class.java]
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_tip_calculator)
        mBinding.vm = CalculatorViewModel(application)

        setSupportActionBar(mBinding.toolbar)
    }

    override fun calculateTip() {
    }

    override fun onDestroy() {
        Log.d(TAG , "onDestroy")
        super.onDestroy()
    }
}

private var TAG = "TipCalculationActivity"
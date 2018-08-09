package com.learning.tipcalculator.tipcalculator.view

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import com.learning.tipcalculator.tipcalculator.R
import com.learning.tipcalculator.tipcalculator.databinding.ActivityTipCalculatorBinding
import com.learning.tipcalculator.tipcalculator.eventhandlers.TipsEventsHandlers
import com.learning.tipcalculator.tipcalculator.viewmodel.CalculatorViewModel

class TipCalculatorActivity : AppCompatActivity(), SaveDialogFragment.CallBack{

    lateinit var mBinding: ActivityTipCalculatorBinding

    override fun onSaveTip(name: String) {
        mBinding.vm?.saveCurrentTip(name)
        Snackbar.make(mBinding.root,"Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_tip_calculation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog(){
        val saveDialogFragment = SaveDialogFragment()
        saveDialogFragment.show(supportFragmentManager, "SaveDialog")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG , "onCreate")

        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_tip_calculator)
        //mBinding.vm = CalculatorViewModel(application)
        mBinding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        setSupportActionBar(mBinding.toolbar)
    }

    override fun onDestroy() {
        Log.d(TAG , "onDestroy")
        super.onDestroy()
    }
}

private var TAG = "TipCalculationActivity"
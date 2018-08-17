package com.learning.tipcalculator.tipcalculator


import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.learning.tipcalculator.tipcalculator.view.TipCalculatorActivity
import org.junit.Rule
import org.junit.Test

/**
* @author Mak
*         <p>Created on 8/16/2018.</p>
 *        <p>Espresso test for UI testing</p>
*/
class TipCalculatorActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(TipCalculatorActivity::class.java)

    @Test
    fun testTipCalculator(){

        // Calculator Tip
        enter(checkAmount = 15.99 , tipPercent = 15)
        calculateTip()
        assertOutput(name = "",  checkAmount="$15.99", tipAmount = "$2.40", total = "$18.39")

        // Save Tip
        saveTip(name = "BBQ Max")
        assertOutput(name = "BBQ Max",  checkAmount="$15.99", tipAmount = "$2.40", total = "$18.39")

        // Clear Outputs
        clearOutputs()
        assertOutput(name = "",  checkAmount="$0.00", tipAmount = "$0.00", total = "$0.00")

        // Load Tips
        loadTip(name = "BBQ Max")
        assertOutput(name = "BBQ Max",  checkAmount="$15.99", tipAmount = "$2.40", total = "$18.39")
    }

    private fun enter(checkAmount : Double, tipPercent : Int){

        onView(withId(R.id.input_check_amount)).perform(replaceText(checkAmount.toString()))
        onView(withId(R.id.input_tip_percentage)).perform(replaceText(tipPercent.toString()))
        //onView(withId(R.id.input_tip_percentage)).perform(closeSoftKeyboard())
    }

    private fun calculateTip(){

        onView(withId(R.id.fab)).perform(click())
    }

    private fun assertOutput(name : String, checkAmount: String, tipAmount: String, total : String){

        onView(withId(R.id.bill_amount)).check(matches(withText(checkAmount)))
        onView(withId(R.id.tip_amount)).check(matches(withText(tipAmount)))
        onView(withId(R.id.grand_total)).check(matches(withText(total)))
        onView(withId(R.id.calculation_name)).check(matches(withText(name)))
    }

    private fun clearOutputs(){

        enter(checkAmount = 0.0, tipPercent = 0)
        calculateTip()
    }

    private fun saveTip(name : String){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())        // getting options menu reference here

        onView(withText(R.string.action_save)).perform(click())                        // performing click on menu item (save)

        onView(withHint(R.string.save_hint)).perform(replaceText(name))

        onView(withText(R.string.action_save)).perform(click())
    }

    private fun loadTip(name: String){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())

        onView(withText(R.string.action_load)).perform(click())

        onView(withText(name)).perform(click())
    }
}
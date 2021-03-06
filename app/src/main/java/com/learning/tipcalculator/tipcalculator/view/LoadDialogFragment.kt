package com.learning.tipcalculator.tipcalculator.view

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import com.learning.tipcalculator.tipcalculator.R
import com.learning.tipcalculator.tipcalculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.saved_tip_calculations_list.view.*
/**
 * @author Mak
 *         <p>Created on 8/16/2018.</p>
 *        <p>Load Dialog to load saved tips</p>
 */
class LoadDialogFragment : DialogFragment() {

    /**
     * interface callBack for our onClick event to be handled in activity
     * */
    interface CallBack{
        fun onTipSelected(name : String)
    }

    private var loadTipCallBack : CallBack? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loadTipCallBack = context as? CallBack
    }

    override fun onDetach() {
        super.onDetach()
        loadTipCallBack = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = context?.let {ctx ->

            AlertDialog.Builder(ctx)
                    .setView(createView(ctx))
                    .setNegativeButton(getString(R.string.action_cancel), null)
                    .create()
        }

        return dialog!!
    }

    private fun createView(ctx : Context) : View{
        val rv = LayoutInflater.from(ctx)
                        .inflate(R.layout.saved_tip_calculations_list, null)
                        .recycler_saved_calculations

        rv.setHasFixedSize(true)
        rv.addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))

        /**
         * initializing the adapter
         * */
        val adapter = TipSummaryAdapter{
            loadTipCallBack?.onTipSelected(it.locationName)
            dismiss()
        }
        /**
         * setting adapter to our recycler view
         * */
        rv.adapter = adapter

        val vm = ViewModelProviders.of(activity!!).get(CalculatorViewModel::class.java)
        vm.loadSavedTipCalculationSummaries().observe(this , Observer {
            if (it != null){
                adapter.updateList(it)
            }
        })

        return rv
    }
}
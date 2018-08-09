package com.learning.tipcalculator.tipcalculator.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.EditText
import com.learning.tipcalculator.tipcalculator.R


class SaveDialogFragment : DialogFragment() {

    interface CallBack{
        fun onSaveTip(name : String)
    }

    private var saveTipCallback: SaveDialogFragment.CallBack? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        saveTipCallback = context as? CallBack
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val saveDailog = context?.let { ctx ->

            val editText = EditText(ctx)
            editText.id = viewId
            editText.hint = getString(R.string.save_hint)

            AlertDialog.Builder(ctx)
                    .setView(editText)
                    .setNegativeButton(getString(R.string.action_cancel),null)
                    .setPositiveButton(getString(R.string.action_save), {_,_ -> onSave(editText)})
                    .create()
        }

        return saveDailog!!
    }

    private fun onSave(editText: EditText){
        val text = editText.text.toString()
        if (text.isNotEmpty()){
            saveTipCallback?.onSaveTip(text)
        }
    }

    companion object {
        val viewId = View.generateViewId()
    }
}
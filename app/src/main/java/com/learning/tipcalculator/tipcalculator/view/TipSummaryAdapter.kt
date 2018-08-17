package com.learning.tipcalculator.tipcalculator.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.learning.tipcalculator.tipcalculator.R
import com.learning.tipcalculator.tipcalculator.databinding.ActivityTipCalculatorBinding
import com.learning.tipcalculator.tipcalculator.databinding.SavedTipCalculationsListItemBinding
import com.learning.tipcalculator.tipcalculator.viewmodel.TipCalculationSummaryItem
/**
 * @author Mak
 *         <p>Created on 8/13/2018.</p>
 *        <p>Adapter for Recycler View</p>
 */
class TipSummaryAdapter(val onItemSelected : (item : TipCalculationSummaryItem) -> Unit) :
        RecyclerView.Adapter<TipSummaryAdapter.TipSummaryViewHolder>() {

    private val calculationSummaryItem = mutableListOf<TipCalculationSummaryItem>()

    fun updateList(updatedList : List<TipCalculationSummaryItem>){
        calculationSummaryItem.clear()
        calculationSummaryItem.addAll(updatedList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipSummaryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SavedTipCalculationsListItemBinding>(inflater , R.layout.saved_tip_calculations_list_item,
                parent, false)

        return TipSummaryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return calculationSummaryItem.size
    }

    override fun onBindViewHolder(holder: TipSummaryViewHolder, position: Int) {
        holder.bind(calculationSummaryItem[position])
    }


    inner class TipSummaryViewHolder(val binding: SavedTipCalculationsListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item : TipCalculationSummaryItem){
            binding.item = item
            binding.root.setOnClickListener{ onItemSelected(item) }
            binding.executePendingBindings()

        }
    }
}
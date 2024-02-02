package com.cm.tradetune.ui.home.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.R
import com.cm.tradetune.data.model.MarketItemDto



class WatchListAdapter(private var watchList: List<MarketItemDto>) :
    RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>() {

    fun submitList(newWatchList: List<MarketItemDto>?) {
        if (newWatchList != null) {
            watchList = newWatchList
        }
    }

    class WatchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context:Context =itemView.context
        val indicesSymbolTextView: TextView = itemView.findViewById(R.id.indicesSymbolTextView)
        val currentPriceTextView: TextView = itemView.findViewById(R.id.currentPriceTextView)
        val todayChangeTextView: TextView = itemView.findViewById(R.id.todayChangeTextView)
        val changePercentTextView: TextView = itemView.findViewById(R.id.changePercentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_to_watch, parent, false)
        return WatchListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.indicesSymbolTextView.text = watchList[position].symbol
        holder.currentPriceTextView.text = watchList[position].currentPrice.toString()
        holder.todayChangeTextView.text = watchList[position].changeInPrice.toString()
        holder.changePercentTextView.text = "(${watchList[position].changePercent}%)"
        if (watchList[position].changePercent<0){
            holder.changePercentTextView.setTextColor(ContextCompat.getColor(holder.context, R.color.red))
            holder.todayChangeTextView.setTextColor(ContextCompat.getColor(holder.context, R.color.red))
        }else{
            holder.changePercentTextView.setTextColor(ContextCompat.getColor(holder.context, R.color.green))
            holder.todayChangeTextView.setTextColor(ContextCompat.getColor(holder.context, R.color.green))
        }

    }

    override fun getItemCount(): Int = watchList.size

}

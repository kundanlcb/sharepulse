package com.cm.tradetune.ui.watchlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.R
import com.cm.tradetune.data.model.MarketItemDto


class ManageWatchListAdapter(private var watchList: List<MarketItemDto>) :
    RecyclerView.Adapter<ManageWatchListAdapter.WatchListViewHolder>() {

    fun submitList(newWatchList: List<MarketItemDto>?) {
        if (newWatchList != null) {
            watchList = newWatchList
        }
    }

    class WatchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val tvSecurityName: TextView = itemView.findViewById(R.id.tvSecurityName)
        val tvSecuritySymbol: TextView = itemView.findViewById(R.id.tvSecuritySymbol)
        val addSecurityButton: TextView = itemView.findViewById(R.id.addSecurityButton)
        val removeSecurityButton: TextView = itemView.findViewById(R.id.removeSecurityButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_modify_watchlist, parent, false)
        return WatchListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.tvSecuritySymbol.text = watchList[position].symbol
        holder.tvSecurityName.text = watchList[position].name
        holder.addSecurityButton.setOnClickListener { }
        holder.removeSecurityButton.setOnClickListener { }

    }

    override fun getItemCount(): Int = watchList.size

}

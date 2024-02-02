package com.cm.tradetune.ui.home.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.R
import com.cm.tradetune.data.model.TrendingDto
import com.cm.tradetune.databinding.ItemTrendingBinding

class TrendingIndicesAdapter (private var trendingIndices: List<TrendingDto>) :
    RecyclerView.Adapter<TrendingIndicesAdapter.ViewHolder>() {

    fun submitList(newTrendList: List<TrendingDto>?) {
        if (newTrendList != null) {
            trendingIndices = newTrendList
        }
    }
    class ViewHolder(private val binding: ItemTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val context: Context =itemView.context
        fun bind(trending: TrendingDto) {
            binding.textViewIndexName.text = trending.name
            binding.textViewIndexPrice.text= trending.currentPrice.toString()
            binding.textViewIndexChange.text = "${trending.todayPercentChange}%"
            if (trending.todayPercentChange<0){
                binding.textViewIndexChange.setTextColor(ContextCompat.getColor(context, R.color.red))
                binding.textViewIndexPrice.setTextColor(ContextCompat.getColor(context, R.color.red))


            }else{
                binding.textViewIndexChange.setTextColor(ContextCompat.getColor(context, R.color.green))
                binding.textViewIndexPrice.setTextColor(ContextCompat.getColor(context, R.color.green))
            }
            // Bind additional data as needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrendingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trendingIndices[position])

    }

    override fun getItemCount(): Int {
        return trendingIndices.size
    }
}
package com.cm.tradetune.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.databinding.ItemIndicSuggestionBinding

class SuggestionAdapter(private var suggestionList: List<MarketItemDto>, private val onItemClick: (MarketItemDto) -> Unit) :
    RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder>() {

    fun submitList(newSuggestionList: List<MarketItemDto>?) {
        if (newSuggestionList != null) {
            suggestionList = newSuggestionList
        }
    }

    class SuggestionViewHolder(private val binding: ItemIndicSuggestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marketItem: MarketItemDto) {
            binding.textViewIndicesSymbol.text = marketItem.symbol
            binding.textViewIndicesName.text = marketItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        val binding = ItemIndicSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        holder.bind(suggestionList[position])
        holder.itemView.setOnClickListener {
            onItemClick(suggestionList[position])
        }
    }

    override fun getItemCount(): Int = suggestionList.size
}

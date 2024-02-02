package com.cm.tradetune.ui.home.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.R
import com.cm.tradetune.data.model.FeedDto

// PollOptionAdapter.kt

class PollOptionAdapter(private val feedDto: FeedDto) :
    RecyclerView.Adapter<PollOptionAdapter.PollOptionViewHolder>() {

    class PollOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionTextView: TextView = itemView.findViewById(R.id.optionTextView)
        val textViewPollPercent: TextView = itemView.findViewById(R.id.textViewPollPercent)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollOptionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poll_option, parent, false)
        return PollOptionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PollOptionViewHolder, position: Int) {
        if (feedDto.alreadyVoted){
            holder.itemView.isClickable=false
            holder.optionTextView.text = feedDto.pollData?.get(position)?.option ?: ""
            "${feedDto.pollData?.get(position)?.percent.toString()}%".also { holder.textViewPollPercent.text = it }
            holder.progressBar.progress=feedDto.pollData?.get(position)?.percent?:0
        }else{
            holder.optionTextView.text = feedDto.pollData?.get(position)?.option ?: ""
            holder.textViewPollPercent.text=""
            holder.progressBar.progress=0
        }
       holder.itemView.setOnClickListener {
           feedDto.alreadyVoted=true
           notifyDataSetChanged()
       }
    }

    override fun getItemCount(): Int = feedDto.pollData?.size?:0
}

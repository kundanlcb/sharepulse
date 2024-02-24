package com.cm.tradetune.ui.onboarding

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.data.model.UserDto
import com.cm.tradetune.databinding.ItemUserSearchBinding
import com.cm.tradetune.databinding.ItemUserSuggestionBinding
import com.cm.tradetune.ui.profile.Profile
import com.cm.tradetune.ui.search.placeholder.PlaceholderContent.PlaceholderItem

class OnboardingListAdapter(
    private var values: List<UserDto>
) : RecyclerView.Adapter<OnboardingListAdapter.ViewHolder>() {

    fun submitList(newUserList: List<UserDto>?) {
        if (newUserList != null) {
            values = newUserList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemUserSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvUserName.text = item.userName
        holder.tvName.text = item.firstName
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent: Intent =  Intent(context, Profile::class.java)
            intent.putExtra("profile_content", item.userName) // pass data to FeedDetailActivity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemUserSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvUserName: TextView = binding.textViewUsername
        val tvName: TextView = binding.textViewName


    }

}
package com.cm.tradetune.ui.search.user

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.data.model.UserDto
import com.cm.tradetune.databinding.ItemUserSearchBinding
import com.cm.tradetune.databinding.ItemUserSuggestionBinding
import com.cm.tradetune.ui.search.placeholder.PlaceholderContent.PlaceholderItem

class UserListAdapter(
    private var values: List<UserDto>
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

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
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemUserSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvUserName: TextView = binding.textViewUsername
        val tvName: TextView = binding.textViewName


    }

}
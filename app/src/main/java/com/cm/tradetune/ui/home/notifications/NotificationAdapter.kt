package com.cm.tradetune.ui.home.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.data.model.Notification
import com.cm.tradetune.databinding.ListItemNotificationBinding
import com.cm.tradetune.util.TimeUtil.Companion.toMillis
import com.cm.tradetune.util.toTimeAgo

class NotificationAdapter(private var notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    fun submitList(newNotifications: List<Notification>?) {
        if (newNotifications != null) {
            notifications = newNotifications
        }
    }

    class ViewHolder(private val binding: ListItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val context: Context = itemView.context
        fun bind(notification: Notification) {
            binding.notificationContent.text = notification.message
            val timestampInMillis = notification.timestamp.toMillis()
            val timeAgoString = timestampInMillis.toTimeAgo()
            binding.notificationTime.text = timeAgoString
            // Bind additional data as needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notifications[position])

    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}
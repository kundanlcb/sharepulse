package com.cm.tradetune.ui.home.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.data.model.Notification
import com.cm.tradetune.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var viewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel=
            ViewModelProvider(this)[NotificationsViewModel::class.java]
        binding=FragmentNotificationsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNotificationList()
    }

    private fun setUpNotificationList() {
        // Initialize RecyclerView and its adapter
        val emptyFeedList: List<Notification> = emptyList()
        notificationAdapter = NotificationAdapter(emptyFeedList)
        // Set layout manager for the RecyclerView
        binding.recyclerNotification.layoutManager = LinearLayoutManager(requireContext())

        // Set the adapter to the RecyclerView
        binding.recyclerNotification.adapter = notificationAdapter


        // Observe LiveData from ViewModel
        viewModel.getNotificationsData().observe(viewLifecycleOwner) { notifications ->
            // Update the adapter with the new data
            notificationAdapter.submitList(notifications)
        }
    }
}
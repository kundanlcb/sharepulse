package com.cm.tradetune.ui.home.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.databinding.FragmentProfileBinding
import com.cm.tradetune.ui.home.home.FeedAdapter

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFeedList()

        binding.btnEditProfile.setOnClickListener {
             navigateToEditAccount()
        }
    }

    private fun navigateToEditAccount() {
        val intent = Intent(activity, EditAccount::class.java)
        startActivity(intent)
    }
    private fun setUpFeedList() {
        // Initialize RecyclerView and its adapter
        val emptyFeedList: List<FeedDto> = emptyList()
        feedAdapter = FeedAdapter(parentFragmentManager,lifecycle,emptyFeedList)
        // Set layout manager for the RecyclerView
        binding.recyclerViewUserFeed.layoutManager = LinearLayoutManager(requireContext())

        // Set the adapter to the RecyclerView
        binding.recyclerViewUserFeed.adapter = feedAdapter


        // Observe LiveData from ViewModel
        viewModel.getFeedListLiveData().observe(viewLifecycleOwner) { feedList ->
            // Update the adapter with the new data
            feedAdapter.submitList(feedList)
        }
    }


}
package com.cm.tradetune.ui.home.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.databinding.FragmentHomeBinding
import com.cm.tradetune.ui.feed.CreateFeed
import com.cm.tradetune.ui.feed.IndicesSelection

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var feedAdapter: FeedAdapter

    private lateinit var watchListAdapter: WatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFeedList()
        setUpWatchList()
        binding.addFeedFab.setOnClickListener {
            navigateToCreateFeed()
        }
        binding.btnAddToWatch.setOnClickListener {
            navigateToAddIndices()
        }
    }

    private fun setUpFeedList() {
        // Initialize RecyclerView and its adapter
        val emptyFeedList: List<FeedDto> = emptyList()
        feedAdapter = FeedAdapter(parentFragmentManager,lifecycle,emptyFeedList)
        // Set layout manager for the RecyclerView
        binding.recyclerFeed.layoutManager = LinearLayoutManager(requireContext())

        // Set the adapter to the RecyclerView
        binding.recyclerFeed.adapter = feedAdapter


        // Observe LiveData from ViewModel
        homeViewModel.getFeedListLiveData().observe(viewLifecycleOwner) { feedList ->
            // Update the adapter with the new data
            feedAdapter.submitList(feedList)
        }
    }

    private fun navigateToCreateFeed() {
        val intent = Intent(activity, CreateFeed::class.java)
        startActivity(intent)
    }

    private fun navigateToAddIndices() {
        val intent = Intent(activity, IndicesSelection::class.java)
        startActivity(intent)
    }

    private fun setUpWatchList() {
        val emptyWatchList: List<MarketItemDto> = emptyList()
        watchListAdapter = WatchListAdapter(emptyWatchList)
        // Set layout manager for the RecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerWatchlist.layoutManager = layoutManager

        // Set the adapter to the RecyclerView
        binding.recyclerWatchlist.adapter = watchListAdapter

        // Set up the refresh listener
        binding.swipeRefreshLayout.setOnRefreshListener {
            // Handle the refresh action here
            // You can call your ViewModel method to fetch new data
            homeViewModel.refreshFeedList()

            // After finishing the refresh, call setRefreshing(false) to stop the loading indicator
            // You can do this when the data is loaded or when an error occurs
            binding.swipeRefreshLayout.isRefreshing = false
        }

        // Observe LiveData from ViewModel
        homeViewModel.getWatchListLiveData().observe(viewLifecycleOwner) { watchList ->
            // Update the adapter with the new data
            watchListAdapter.submitList(watchList)
        }
    }


}
package com.cm.tradetune.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.R
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.databinding.ActivityEditAccountBinding
import com.cm.tradetune.databinding.ActivityProfileBinding
import com.cm.tradetune.ui.home.home.FeedAdapter
import com.cm.tradetune.ui.home.profile.ProfileViewModel

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set up the Toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Profile"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back) // Replace with your back arrow icon
        }
        setUpFeedList()
        // Rest of your activity setup
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed() // Handle back button press
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpFeedList() {
        // Initialize RecyclerView and its adapter
        val emptyFeedList: List<FeedDto> = emptyList()
        feedAdapter = FeedAdapter(supportFragmentManager,lifecycle,emptyFeedList)
        // Set layout manager for the RecyclerView
        binding.recyclerViewUserFeed.layoutManager = LinearLayoutManager(this)

        // Set the adapter to the RecyclerView
        binding.recyclerViewUserFeed.adapter = feedAdapter


        // Observe LiveData from ViewModel
        viewModel.getFeedListLiveData().observe(this) { feedList ->
            // Update the adapter with the new data
            feedAdapter.submitList(feedList)
        }
    }
}
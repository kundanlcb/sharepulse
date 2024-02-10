package com.cm.tradetune.ui.securities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.R
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.databinding.ActivityEquityDetailBinding
import com.cm.tradetune.ui.home.home.FeedAdapter

class EquityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityEquityDetailBinding
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var viewModel: EquityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this)[EquityViewModel::class.java]
        binding = ActivityEquityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set up the Toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "[Infosys] (INFY)"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back) // Replace with your back arrow icon
        }
        setUpFeedList()
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

    private fun setUpFundamentals() {
        val layoutSecurityFundamentals = binding.layoutSecurityFundamentals
        layoutSecurityFundamentals.textBookValueValue.text = "30.15"
        layoutSecurityFundamentals.textFaceValueValue.text = "10.50"
        layoutSecurityFundamentals.textIndustryPEValue.text = "20.15"
        layoutSecurityFundamentals.textMarketCapValue.text = "2.15B"
        layoutSecurityFundamentals.textPBRatioValue.text = "2.15"
        layoutSecurityFundamentals.textPERatioValue.text = "30"
    }

    private fun setChart(){
        
    }

    private fun setUpFeedList() {
        // Initialize RecyclerView and its adapter
        val emptyFeedList: List<FeedDto> = emptyList()
        feedAdapter = FeedAdapter(supportFragmentManager, lifecycle, emptyFeedList)
        // Set layout manager for the RecyclerView
        binding.rvEquityPost.layoutManager = LinearLayoutManager(this)

        // Set the adapter to the RecyclerView
        binding.rvEquityPost.adapter = feedAdapter


        // Observe LiveData from ViewModel
        viewModel.getFeedListLiveData().observe(this) { feedList ->
            // Update the adapter with the new data
            feedAdapter.submitList(feedList)
        }
    }
}
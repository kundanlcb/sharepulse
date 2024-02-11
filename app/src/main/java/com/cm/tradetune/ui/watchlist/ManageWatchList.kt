package com.cm.tradetune.ui.watchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.R
import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.databinding.ActivityManageWatchListBinding
import com.cm.tradetune.databinding.ActivitySearchBinding
import com.cm.tradetune.ui.search.SearchViewModel
import com.cm.tradetune.ui.search.indice.IndicesAdapter
import com.cm.tradetune.ui.search.user.UserListAdapter

class ManageWatchList : AppCompatActivity() {

    private lateinit var binding: ActivityManageWatchListBinding
    private lateinit var viewModel: ManageWatchListVM
    private lateinit var manageWatchlistAdapter: ManageWatchListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageWatchListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.searchView.requestFocus()
        viewModel =
            ViewModelProvider(this)[ManageWatchListVM::class.java]

        setUpEquityList()
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
    private fun setUpEquityList() {
        // Initialize RecyclerView and its adapter
        val emptyMarketItemList: List<MarketItemDto> = emptyList()
        manageWatchlistAdapter = ManageWatchListAdapter(emptyMarketItemList)
        // Set layout manager for the RecyclerView
        binding.rvManageWatchlist.layoutManager = LinearLayoutManager(this)

        // Set the adapter to the RecyclerView
        binding.rvManageWatchlist.adapter = manageWatchlistAdapter


        // Observe LiveData from ViewModel
        viewModel.getSuggestionListLiveData().observe(this) { itemList ->
            // Update the adapter with the new data
            manageWatchlistAdapter.submitList(itemList)
        }
    }
}
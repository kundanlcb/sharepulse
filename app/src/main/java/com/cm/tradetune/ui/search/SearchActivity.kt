package com.cm.tradetune.ui.search

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.data.model.UserDto
import com.cm.tradetune.databinding.ActivitySearchBinding
import com.cm.tradetune.ui.home.profile.ProfileViewModel
import com.cm.tradetune.ui.search.indice.IndicesAdapter
import com.cm.tradetune.ui.search.user.UserListAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var userAdapter: UserListAdapter
    private lateinit var equityAdapter: IndicesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.searchView.requestFocus()
        viewModel =
            ViewModelProvider(this)[SearchViewModel::class.java]

        setUpEquityList()
        setUpUserList()
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

    private fun setUpUserList() {
        // Initialize RecyclerView and its adapter
        val emptyUserList: List<UserDto> = emptyList()
        userAdapter = UserListAdapter(emptyUserList)
        // Set layout manager for the RecyclerView
        binding.rvUserSearch.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        // Set the adapter to the RecyclerView
        binding.rvUserSearch.adapter = userAdapter

        // Observe LiveData from ViewModel
        viewModel.getUserListLiveData().observe(this) { userList ->
            // Update the adapter with the new data
            userAdapter.submitList(userList)
        }
    }

    private fun setUpEquityList() {
        // Initialize RecyclerView and its adapter
        val emptyUserList: List<EquityDto> = emptyList()
        equityAdapter = IndicesAdapter(emptyUserList)
        // Set layout manager for the RecyclerView
        binding.rvEquitySearch.layoutManager = LinearLayoutManager(this)

        // Set the adapter to the RecyclerView
        binding.rvEquitySearch.adapter = equityAdapter


        // Observe LiveData from ViewModel
        viewModel.getTrendListLiveData().observe(this) { trendingList ->
            // Update the adapter with the new data
            equityAdapter.submitList(trendingList)
        }
    }
}
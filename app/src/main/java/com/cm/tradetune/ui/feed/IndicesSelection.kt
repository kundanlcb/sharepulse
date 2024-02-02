package com.cm.tradetune.ui.feed

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.R
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.databinding.ActivityIndicesSelectionBinding

class IndicesSelection : AppCompatActivity() {

    private lateinit var binding: ActivityIndicesSelectionBinding
    private lateinit var searchView: SearchView
    private lateinit var suggestionAdapter: SuggestionAdapter
    private lateinit var indicesSelectionViewModel: IndicesSelectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIndicesSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        indicesSelectionViewModel = ViewModelProvider(this)[IndicesSelectionViewModel::class.java]
        // Set up your suggestion data (replace with your data source)
        setUpSuggestionList()

        // Set up the suggestion adapter


        // Set up the SearchView
        searchView = binding.toolbar.findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                println(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Update the suggestion list based on the new query
                println(newText)
                return true
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_indices_selection, menu)
        return true
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


    private fun setUpSuggestionList() {
        val emptyWatchList: List<MarketItemDto> = emptyList()
        suggestionAdapter = SuggestionAdapter(emptyWatchList){ selectedItem ->
            // Handle item click here
            val resultIntent = Intent()
            resultIntent.putExtra("selected_indices", selectedItem.name)
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Close the activity
        }
        // Set layout manager for the RecyclerView
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerIndicesSuggestion.layoutManager = layoutManager

        // Set the adapter to the RecyclerView
        binding.recyclerIndicesSuggestion.adapter = suggestionAdapter

        // Observe LiveData from ViewModel
        indicesSelectionViewModel.getSuggestionListLiveData().observe(this) { suggestionList ->
            // Update the adapter with the new data
            suggestionAdapter.submitList(suggestionList)
        }
    }
}
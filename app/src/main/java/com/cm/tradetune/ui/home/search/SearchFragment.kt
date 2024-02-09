package com.cm.tradetune.ui.home.search

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.databinding.FragmentSearchBinding
import com.cm.tradetune.ui.search.SearchActivity
import com.cm.tradetune.ui.search.SearchViewModel


class SearchFragment : Fragment() {


    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var trendAdapter: TrendingIndicesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel=
            ViewModelProvider(this)[SearchViewModel::class.java]
        binding=FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTrendList()
        binding.tvSearchButton.setOnClickListener {
            navigateToSearchScreen()
        }
    }

    private fun navigateToSearchScreen() {
        val intent = Intent(activity, SearchActivity::class.java)
        startActivity(intent)
    }
    private fun setUpTrendList() {
        // Initialize RecyclerView and its adapter
        val emptyFeedList: List<EquityDto> = emptyList()
        trendAdapter = TrendingIndicesAdapter(emptyFeedList)
        // Set layout manager for the RecyclerView
        binding.recyclerTrend.layoutManager = LinearLayoutManager(requireContext())

        // Set the adapter to the RecyclerView
        binding.recyclerTrend.adapter = trendAdapter


        // Observe LiveData from ViewModel
        viewModel.getTrendListLiveData().observe(viewLifecycleOwner) { trendList ->
            // Update the adapter with the new data
            trendAdapter.submitList(trendList)
        }
    }


}
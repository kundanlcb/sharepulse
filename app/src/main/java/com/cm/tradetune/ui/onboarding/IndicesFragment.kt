package com.cm.tradetune.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.databinding.FragmentIndicesBinding
import com.cm.tradetune.ui.search.indice.IndicesAdapter
import com.cm.tradetune.ui.search.indice.IndicesViewModel

/**
 * A fragment representing a list of Items.
 */
class IndicesFragment : Fragment() {

    private lateinit var viewModel: IndicesViewModel
    private lateinit var binding: FragmentIndicesBinding
    private lateinit var indicesAdapter: IndicesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this)[IndicesViewModel::class.java]
        binding = FragmentIndicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpIndicesList()

    }

    private fun setUpIndicesList() {
        // Initialize RecyclerView and its adapter
        val emptyUserList: List<EquityDto> = emptyList()
        indicesAdapter = IndicesAdapter(emptyUserList)
        // Set layout manager for the RecyclerView
        binding.rvIndices.layoutManager = LinearLayoutManager(requireContext())

        // Set the adapter to the RecyclerView
        binding.rvIndices.adapter = indicesAdapter


        // Observe LiveData from ViewModel
        viewModel.getIndicesLiveData().observe(viewLifecycleOwner) { trendingList ->
            // Update the adapter with the new data
            indicesAdapter.submitList(trendingList)
        }
    }
}
package com.cm.tradetune.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.data.model.UserDto
import com.cm.tradetune.databinding.FragmentUserBinding
import com.cm.tradetune.ui.search.user.UserListAdapter
import com.cm.tradetune.ui.search.user.UserViewModel

/**
 * A fragment representing a list of Items.
 */
class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentUserBinding
    private lateinit var userAdapter: UserListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUserList()

    }

    private fun setUpUserList() {
        // Initialize RecyclerView and its adapter
        val emptyUserList: List<UserDto> = emptyList()
        userAdapter = UserListAdapter(emptyUserList)
        // Set layout manager for the RecyclerView
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())

        // Set the adapter to the RecyclerView
        binding.rvUsers.adapter = userAdapter


        // Observe LiveData from ViewModel
        viewModel.getUserListLiveData().observe(viewLifecycleOwner) { userList ->
            // Update the adapter with the new data
            userAdapter.submitList(userList)
        }
    }
}
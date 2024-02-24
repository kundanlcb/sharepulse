package com.cm.tradetune.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cm.tradetune.ui.search.feed.PostListFragment

class OnboardingPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        // Return the number of pages
        return 2/* your page count */;
    }

    override fun createFragment(position: Int): Fragment {
        // Create and return the fragment for the given page position
        return when (position) {
            0 -> UserFragment()
            1 -> IndicesFragment()
            // Add more cases for different fragment types...
            else -> PostListFragment() // Default to a specific fragment type if needed
        }
    }
}
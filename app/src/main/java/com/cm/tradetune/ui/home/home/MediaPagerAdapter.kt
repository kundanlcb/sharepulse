package com.cm.tradetune.ui.home.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.data.model.MediaMetaDto

class MediaPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val feedDto: FeedDto
) : FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
      return feedDto.mediaList?.size ?: 0
    }

    override fun createFragment(position: Int): Fragment {

        val  mediaMeta: MediaMetaDto? = feedDto.mediaList?.get(position)
        return when (mediaMeta?.mediaType) {
            "photo" -> PhotoFragment.newInstance(mediaMeta.mediaUrl)
            "video" -> VideoFragment.newInstance(mediaMeta.mediaUrl)
            // Add more cases for different fragment types...
            else -> PhotoFragment.newInstance(mediaMeta?.mediaUrl ?: "") // Default to a specific fragment type if needed
        }
    }
}

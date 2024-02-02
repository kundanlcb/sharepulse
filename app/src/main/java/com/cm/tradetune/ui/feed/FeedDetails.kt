package com.cm.tradetune.ui.feed

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cm.tradetune.data.model.CommentDto
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.databinding.ActivityFeedDetailsBinding
import com.cm.tradetune.ui.home.home.MediaAdapter
import com.cm.tradetune.ui.home.home.MediaPagerAdapter
import com.cm.tradetune.util.TimeUtil.Companion.toMillis
import com.cm.tradetune.util.toTimeAgo


class FeedDetails : AppCompatActivity() {

    private lateinit var binding: ActivityFeedDetailsBinding
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var feedDetailsViewModel: FeedDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Post"

        // Set up the post detail views
        // For example, you can dynamically add TextViews to the binding.postDetailContainer
        feedDetailsViewModel = ViewModelProvider(this)[FeedDetailsViewModel::class.java]
        // Set up the RecyclerView with the custom adapter for comments

        val intent = intent
        val feedDto: FeedDto? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("post_content", FeedDto::class.java)
        } else {
            (intent.getSerializableExtra("post_content") as FeedDto?)
        }
        setFeedData(feedDto)
        setupCommentList()


    }

    private fun setFeedData(feedDto: FeedDto?) {
        val includedLayoutBinding = binding.layoutContent
        includedLayoutBinding.contentTextView.text= feedDto?.contentText ?: ""
        includedLayoutBinding.layoutUpper.usernameTextView.text=feedDto?.userName ?: ""
        val timestampInMillis = feedDto?.time?.toMillis()
        val timeAgoString = timestampInMillis?.toTimeAgo()
        includedLayoutBinding.layoutUpper.timeTextView.text=timeAgoString

        includedLayoutBinding.layoutLower.tvLikeCount.text= (feedDto?.likes ?: "").toString()
        includedLayoutBinding.layoutLower.tvRepostCount.text= (feedDto?.repostCount ?: "").toString()
        includedLayoutBinding.layoutLower.tvCommentCount.text= (feedDto?.repostCount ?: "").toString()

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        includedLayoutBinding.mediaPager.adapter =
            feedDto?.let { MediaPagerAdapter(supportFragmentManager, lifecycle, it) }
       // includedLayoutBinding.dotsIndicator.attachTo(includedLayoutBinding.mediaPager)
      /*  includedLayoutBinding.recyclerFeedMedia.layoutManager = layoutManager
        includedLayoutBinding.recyclerFeedMedia.adapter = feedDto?.mediaList?.let { MediaAdapter(it) }
*/
    }

    private fun setupCommentList() {
        val emptyWatchList: List<CommentDto> = emptyList()
        commentAdapter = CommentAdapter(emptyWatchList)
        // Set layout manager for the RecyclerView
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewComment.layoutManager = layoutManager

        // Set the adapter to the RecyclerView
        binding.recyclerViewComment.adapter = commentAdapter

        // Observe LiveData from ViewModel
        feedDetailsViewModel.getCommentListLiveData().observe(this) { comments ->
            // Update the adapter with the new data
            commentAdapter.submitList(comments)
        }
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
}
package com.cm.tradetune.ui.home.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.cm.tradetune.R
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.ui.feed.FeedDetails
import com.cm.tradetune.ui.feed.PollDetails
import com.cm.tradetune.util.TimeUtil.Companion.toMillis
import com.cm.tradetune.util.toTimeAgo


class FeedAdapter(private val fragmentManager: FragmentManager, private val lifecycle: Lifecycle, private var feedList: List<FeedDto>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_POLL = 1
    private val VIEW_TYPE_CONTENT = 2

    fun submitList(newFeedList: List<FeedDto>?) {
        if (newFeedList != null) {
            feedList = newFeedList
        }
    }


    inner class PollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val likeBtn: ImageButton = itemView.findViewById(R.id.likeButton)
        val repostButton: ImageButton = itemView.findViewById(R.id.repostButton)
        val pollQuestionTextView: TextView = itemView.findViewById(R.id.pollQuestionTextView)
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val tvLikeCount: TextView = itemView.findViewById(R.id.tvLikeCount)
        val tvRepostCount: TextView = itemView.findViewById(R.id.tvRepostCount)

        private var recyclerPollOptions: RecyclerView =
            itemView.findViewById(R.id.optionsRecyclerView)

        fun bind(feed: FeedDto) {
            val layoutManager = LinearLayoutManager(itemView.context)
            recyclerPollOptions.layoutManager = layoutManager
            recyclerPollOptions.adapter = PollOptionAdapter(feed)
        }

        fun updateLikeButton(isLiked: Boolean) {
            likeBtn.isSelected = isLiked
        }

        fun updateRepostButton(isRepost: Boolean) {
            repostButton.isSelected = isRepost
        }

    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val likeBtn: ImageButton = itemView.findViewById(R.id.likeButton)
        val repostButton: ImageButton = itemView.findViewById(R.id.repostButton)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val tvLikeCount: TextView = itemView.findViewById(R.id.tvLikeCount)
        val tvRepostCount: TextView = itemView.findViewById(R.id.tvRepostCount)
        private val mediaPager: ViewPager2 = itemView.findViewById(R.id.mediaPager)
       // private val dotsIndicator: DotsIndicator = itemView.findViewById(R.id.dots_indicator)
        /* private var recyclerMedia: RecyclerView =
             itemView.findViewById(R.id.recycler_feed_media)*/

        fun bind(feed: FeedDto) {
            mediaPager.adapter =
                MediaPagerAdapter(fragmentManager, lifecycle, feed)
           // dotsIndicator.attachTo(mediaPager)
            // Set LayoutManager for MediaAdapter
            /*   val layoutManager = LinearLayoutManager(itemView.context)
               layoutManager.orientation = LinearLayoutManager.HORIZONTAL
               recyclerMedia.layoutManager = layoutManager
               recyclerMedia.adapter = feed.mediaList?.let { MediaAdapter(it) }*/
        }

        fun updateLikeButton(isLiked: Boolean) {
            likeBtn.isSelected = isLiked
        }

        fun updateRepostButton(isRepost: Boolean) {
            repostButton.isSelected = isRepost
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_POLL -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_poll, parent, false)
                PollViewHolder(itemView)
            }

            VIEW_TYPE_CONTENT -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_content, parent, false)
                ContentViewHolder(itemView)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentFeed = feedList[position]

        holder.itemView.setOnClickListener {
            // Handle item click here
            val context = holder.itemView.context
            val intent: Intent = if (currentFeed.isPoll) {
                Intent(context, PollDetails::class.java)
            } else {
                Intent(context, FeedDetails::class.java)

            }

            intent.putExtra("post_content", currentFeed) // pass data to FeedDetailActivity
            context.startActivity(intent)
        }



        when (holder) {
            is PollViewHolder -> {
                holder.likeBtn.setOnClickListener {
                    currentFeed.likedByUser = !currentFeed.likedByUser
                    holder.updateLikeButton(currentFeed.likedByUser)
                    currentFeed.likes += if (currentFeed.likedByUser) 1 else -1
                    notifyItemChanged(position)

                }
                holder.repostButton.setOnClickListener {
                    currentFeed.isReposted = !currentFeed.isReposted
                    holder.updateRepostButton(currentFeed.isReposted)
                    currentFeed.repostCount += if (currentFeed.isReposted) 1 else -1
                    notifyItemChanged(position)
                }
                holder.updateRepostButton(currentFeed.isReposted)
                holder.updateLikeButton(currentFeed.likedByUser)
                holder.usernameTextView.text = currentFeed.userName
                holder.pollQuestionTextView.text = currentFeed.pollQuestion
                val timestampInMillis = currentFeed.time.toMillis()
                val timeAgoString = timestampInMillis.toTimeAgo()
                holder.timeTextView.text = timeAgoString
                holder.tvLikeCount.text = currentFeed.likes.toString()
                holder.tvRepostCount.text = currentFeed.repostCount.toString()
                holder.bind(currentFeed)
            }

            is ContentViewHolder -> {
                holder.likeBtn.setOnClickListener {
                    currentFeed.likedByUser = !currentFeed.likedByUser
                    holder.updateLikeButton(currentFeed.likedByUser)
                    currentFeed.likes += if (currentFeed.likedByUser) 1 else -1
                    notifyItemChanged(position)
                }
                holder.repostButton.setOnClickListener {
                    currentFeed.isReposted = !currentFeed.isReposted
                    holder.updateRepostButton(currentFeed.isReposted)
                    currentFeed.repostCount += if (currentFeed.isReposted) 1 else -1
                    notifyItemChanged(position)
                }

                holder.updateRepostButton(currentFeed.isReposted)
                holder.updateLikeButton(currentFeed.likedByUser)
                holder.contentTextView.text = currentFeed.contentText
                holder.usernameTextView.text = currentFeed.userName
                val timestampInMillis = currentFeed.time.toMillis()
                val timeAgoString = timestampInMillis.toTimeAgo()
                holder.timeTextView.text = timeAgoString
                holder.tvLikeCount.text = currentFeed.likes.toString()
                holder.tvRepostCount.text = currentFeed.repostCount.toString()

                // Load media (photo or video) using Picasso or ExoPlayer
                if (!currentFeed.mediaList.isNullOrEmpty()) {
                    holder.bind(currentFeed)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (feedList[position].isPoll) VIEW_TYPE_POLL else VIEW_TYPE_CONTENT
    }

    override fun getItemCount(): Int {
        return feedList.size
    }


}
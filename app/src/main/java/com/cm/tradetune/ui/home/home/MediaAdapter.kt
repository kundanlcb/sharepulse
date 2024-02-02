package com.cm.tradetune.ui.home.home// MediaAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.media3.common.MediaItem
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.R
import com.cm.tradetune.data.model.MediaMetaDto
import com.squareup.picasso.Picasso

class MediaAdapter(private val mediaList: List<MediaMetaDto>) :
    RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.postImageView)
      //  val playerView: PlayerView = itemView.findViewById(R.id.playerView)
       /* val exoPlayer: ExoPlayer = ExoPlayer.Builder(itemView.context).build()

        init {
            playerView.player = exoPlayer
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_media_view, parent, false)
        return MediaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val mediaItem = mediaList[position]

        if (mediaItem.mediaType == "photo") {
            // Load photo using Picasso
            Picasso.get()
                .load(mediaItem.mediaUrl)
                .placeholder(R.drawable.placeholder) // Placeholder resource
                .error(R.drawable.error_placeholder)
                .into(holder.imageView)

            // Hide ExoPlayer view
           // holder.playerView.visibility = View.GONE
        } else if (mediaItem.mediaType == "video") {
            // Load video using Media3-ExoPlayer
           // holder.playerView.visibility = View.VISIBLE
            val mediaUrl = MediaItem.fromUri(mediaItem.mediaUrl)
          //  holder.exoPlayer.setMediaItem(mediaUrl)
          //  holder.exoPlayer.prepare()
        }
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun onViewRecycled(holder: MediaViewHolder) {
        super.onViewRecycled(holder)
      /*  if (holder.exoPlayer.isPlaying) {
            holder.exoPlayer.stop()
        }*/
    }
}

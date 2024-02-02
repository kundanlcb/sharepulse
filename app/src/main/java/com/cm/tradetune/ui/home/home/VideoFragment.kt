package com.cm.tradetune.ui.home.home

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.cm.tradetune.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private lateinit var mediaController: MediaController

    companion object {

        private const val ARG_VIDEO_URL = "video_url"

        fun newInstance(videoUrl: String): VideoFragment {
            val fragment = VideoFragment()
            val args = Bundle()
            args.putString(ARG_VIDEO_URL, videoUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeVideoPlayer()
        // Access the video URL from arguments
        val videoUrl = arguments?.getString(ARG_VIDEO_URL)
      //  loadVideoThumbnail(videoUrl)
        playVideo(videoUrl)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun loadVideoThumbnail(videoUrl: String?) {
        videoUrl?.let {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(requireContext(), Uri.parse(it))
            val bitmap: Bitmap? = retriever.frameAtTime
           // binding.thumbnailImageView.setImageBitmap(bitmap)
        }
    }
    private fun initializeVideoPlayer() {
        mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoMedia)
        binding.videoMedia.setMediaController(mediaController)
    }

    private fun playVideo(videoUrl: String?) {
        // Start video playback using the provided video URL
        videoUrl?.let {
            binding.videoMedia.setVideoURI(Uri.parse(it))
            binding.videoMedia.setOnPreparedListener { mediaPlayer: MediaPlayer ->
                // Set up the media controller once the video is prepared
                mediaController.setMediaPlayer(binding.videoMedia)
                mediaController.setAnchorView(binding.videoMedia)
                mediaPlayer.start()
            }
        }
    }

}
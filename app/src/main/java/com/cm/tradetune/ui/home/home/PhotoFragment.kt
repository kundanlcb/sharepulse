package com.cm.tradetune.ui.home.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cm.tradetune.R
import com.cm.tradetune.databinding.FragmentPhotoBinding
import com.cm.tradetune.databinding.FragmentVideoBinding
import com.squareup.picasso.Picasso

class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    companion object {
        fun newInstance() = PhotoFragment()

        private const val ARG_PHOTO_URL = "photo_url"

        fun newInstance(photoUrl: String): PhotoFragment {
            val fragment = PhotoFragment()
            val args = Bundle()
            args.putString(ARG_PHOTO_URL, photoUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access the video URL from arguments
        val photoUrl = arguments?.getString(ARG_PHOTO_URL)

        Picasso.get()
            .load(photoUrl)
            .placeholder(R.drawable.placeholder) // Placeholder resource
            .error(R.drawable.error_placeholder)
            .into(binding.imagePostMedia)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
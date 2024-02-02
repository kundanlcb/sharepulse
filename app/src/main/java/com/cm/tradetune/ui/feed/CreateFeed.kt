package com.cm.tradetune.ui.feed

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.cm.tradetune.TradeMateApplication
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.data.model.HelloMessage
import com.cm.tradetune.websocket.WebSocketManager
import com.cm.tradetune.databinding.ActivityCreateFeedBinding
import com.cm.tradetune.di.DaggerAppComponent
import com.cm.tradetune.util.Utils.generateUniqueId
import com.cm.tradetune.util.Utils.getCurrentTime
import javax.inject.Inject


class CreateFeed : AppCompatActivity() {
    private lateinit var binding: ActivityCreateFeedBinding

    @Inject
    lateinit var createFeedViewModel: CreateFeedViewModel
    private lateinit var webSocketManager: WebSocketManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webSocketManager = WebSocketManager()
        binding.llCreateFeedPoll.visibility = View.GONE
        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        (application as TradeMateApplication).appComponent.inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Post Feed"
        // Set up click listeners for attachment buttons
        binding.buttonAttachImage.setOnClickListener {
            // Handle attaching an image
        }
        binding.buttonPoll.setOnClickListener {
            binding.llCreateFeedNormal.visibility = View.GONE
            binding.llCreateFeedPoll.visibility = View.VISIBLE
        }

        binding.closePollUi.setOnClickListener {
            binding.llCreateFeedNormal.visibility = View.VISIBLE
            binding.llCreateFeedPoll.visibility = View.GONE
            binding.option3.visibility = View.GONE
        }

        binding.plusButton.setOnClickListener {
            binding.option3.visibility = View.VISIBLE
        }


        // Set up click listener for selecting indices using ActivityResultLauncher
        binding.buttonSelectIndices.setOnClickListener {
            // Open the indices selection activity using the launcher
            selectIndicesLauncher.launch(
                Intent(this, IndicesSelection::class.java)
            )
        }

        // Set up click listener for the publish button
        binding.buttonPublish.setOnClickListener {
            // Validate if the user has written something (implement your validation logic here)
            createFeedViewModel.fetchEquities()
            // If validation passes, create a post object and append it to the feed JSON
            // Example:
            val postContent = binding.editTextFeed.text.toString()
            val selectedIndices = binding.buttonSelectIndices.text.toString()

            if (postContent.isNotEmpty()) {
                val messageWithContent = HelloMessage(postContent)
                webSocketManager.sendName(messageWithContent)
                val postObject = createPostObject(postContent, selectedIndices)
                createFeedViewModel.updateFeeds(postObject)
                // finish()
                // Append the postObject to the feed JSON or perform further actions
            } else {
                // Show an error message to the user indicating that they need to write something
            }

        }
    }

    // Function to create a post object
    private fun createPostObject(postContent: String, selectedIndices: String): FeedDto {
        // Implement your logic to create a Post object with the provided data
        return FeedDto(
            userName = "John Doe", // Replace with the actual username
            time = getCurrentTime(), // You can implement this method to get the current time
            isPoll = false, // Replace with the actual logic for determining if it's a poll
            contentText = postContent,
            likes = 0,
            alreadyVoted = false,
            repostCount = 0,
            shareCount = 0,
            isReposted = false,
            likedByUser = false,
            repostedByUser = false,
            id = generateUniqueId() // You can implement this method to generate a unique ID
        )
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

    private val selectIndicesLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            // Handle the result data from the indices selection activity
            val selectedIndices = data?.getStringExtra("selected_indices")
            binding.buttonSelectIndices.text = selectedIndices
            // Do something with selectedIndices
        }
    }

    companion object {
        const val REQUEST_CODE_SELECT_INDICES = 123 // Use any unique request code
    }
}

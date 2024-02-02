package com.cm.tradetune.ui.feed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.data.model.UserDto
import com.cm.tradetune.data.webservice.EquityRepository
import com.cm.tradetune.data.webservice.FailureResponse
import com.cm.tradetune.data.webservice.UserRepository
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class CreateFeedViewModel @Inject constructor(
    application: Application, private val userRepository: UserRepository,
    private val equityRepository: EquityRepository) : AndroidViewModel(application) {
    // MutableLiveData to hold the list of feeds
    private val _feedList = MutableLiveData<List<FeedDto>>()
    val feedList: LiveData<List<FeedDto>> get() = _feedList

    private val jsonFileName = "feeds.json"

    init {
        // Load initial feeds from JSON
        val feedListJsonString =
            JsonUtil.loadJsonFromAsset(application.applicationContext, jsonFileName)
        val feedListType = object : TypeToken<List<FeedDto>>() {}.type
        val initialFeedList = Gson().fromJson<List<FeedDto>>(feedListJsonString, feedListType)

        // Update LiveData with initial feeds
        _feedList.value = initialFeedList


    }

    // Function to update the list of feeds
    fun updateFeeds(feed: FeedDto) {
        // Get the current list of feeds
        val currentFeeds = _feedList.value?.toMutableList() ?: mutableListOf()

        // Add the new feed
        currentFeeds.add(feed)

        // Update LiveData with the updated list
        _feedList.value = currentFeeds

        // Save the updated list to the JSON file
        saveFeedListToJson(currentFeeds)
    }

    private fun saveFeedListToJson(feedList: List<FeedDto>) {
        // Convert the list to JSON
        val json = Gson().toJson(feedList)

        // Save the JSON string to the file
        JsonUtil.saveJsonToFile(getApplication(), json, jsonFileName)
    }

    fun fetchUserData(userId: String) {
        val onSuccess: (UserDto?) -> Unit = { data ->
            if (data != null) {
                println(data.userName)
            }
        }

        val onFailure: (FailureResponse) -> Unit = { failureResponse ->
            println(failureResponse.message)
        }

        userRepository.getUserData(userId, onSuccess, onFailure)
    }

     fun fetchEquities() {
         println("Inside: fetchEquities")
        val onSuccess: (List<EquityDto>?) -> Unit = { data ->
            if (data != null) {
                println(data.first().name)
            }
        }

        val onFailure: (FailureResponse) -> Unit = { failureResponse ->
            println(failureResponse.message)
        }

        equityRepository.getEquities(onSuccess,onFailure)
    }
}

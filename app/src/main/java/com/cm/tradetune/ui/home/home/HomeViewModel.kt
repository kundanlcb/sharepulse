package com.cm.tradetune.ui.home.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.util.JsonUtil

class HomeViewModel(application: Application) : AndroidViewModel(application){

     private val feedListLiveData: MutableLiveData<List<FeedDto>> = MutableLiveData()
    private val watchListLiveData: MutableLiveData<List<MarketItemDto>> = MutableLiveData()

    init {
        // Load JSON data and parse into Feed objects
        loadFeedList()
        loadWatchList()
    }

    fun refreshFeedList() {
        // Implement the logic to refresh the feed list here
        // For example, you can fetch new data from a data source
        // and update the LiveData with the new data

        // For demonstration purposes, let's reload the initial feed data
        loadFeedList()
    }

    fun getFeedListLiveData(): LiveData<List<FeedDto>> {
        return feedListLiveData
    }

    fun getWatchListLiveData(): LiveData<List<MarketItemDto>> {
        return watchListLiveData
    }

    private fun loadFeedList() {
        val jsonString = JsonUtil.loadJsonFromAsset(getApplication(), "feeds.json")
        val feedListType = object : TypeToken<List<FeedDto>>() {}.type
        val feedList = Gson().fromJson<List<FeedDto>>(jsonString, feedListType)

        // Update LiveData
        feedListLiveData.value = feedList
    }

    private fun loadWatchList() {
        val watchListJsonString = JsonUtil.loadJsonFromAsset(getApplication(), "watchlist.json")
        val watchListType = object : TypeToken<List<MarketItemDto>>() {}.type
        val watchList = Gson().fromJson<List<MarketItemDto>>(watchListJsonString, watchListType)

        // Update LiveData
        watchListLiveData.value = watchList
    }
}
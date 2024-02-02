package com.cm.tradetune.ui.home.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.FeedDto
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileViewModel(application: Application) : AndroidViewModel(application){

    private val feedListLiveData: MutableLiveData<List<FeedDto>> = MutableLiveData()
    private val watchListLiveData: MutableLiveData<List<MarketItemDto>> = MutableLiveData()

    init {
        // Load JSON data and parse into Feed objects
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "feeds.json")
        val feedListType = object : TypeToken<List<FeedDto>>() {}.type
        val feedList = Gson().fromJson<List<FeedDto>>(jsonString, feedListType)

        // Update LiveData
        feedListLiveData.value = feedList

    }

    fun getFeedListLiveData(): LiveData<List<FeedDto>> {
        return feedListLiveData
    }


}
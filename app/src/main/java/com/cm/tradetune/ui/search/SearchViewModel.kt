package com.cm.tradetune.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.data.model.TrendingDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val trendingLiveData: MutableLiveData<List<TrendingDto>> = MutableLiveData()

    init {
        // Load JSON data and parse into Feed objects
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "trending.json")
        val trendingListType = object : TypeToken<List<TrendingDto>>() {}.type
        val trendingList = Gson().fromJson<List<TrendingDto>>(jsonString, trendingListType)

        // Update LiveData
        trendingLiveData.value = trendingList


    }

    fun getTrendListLiveData(): LiveData<List<TrendingDto>> {
        return trendingLiveData
    }
}

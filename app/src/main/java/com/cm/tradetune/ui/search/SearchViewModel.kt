package com.cm.tradetune.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.data.model.UserDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val trendingLiveData: MutableLiveData<List<EquityDto>> = MutableLiveData()
    private val userListLiveData: MutableLiveData<List<UserDto>> = MutableLiveData()

    init {

        loadUserData(application)
        loadEquityData(application)

    }

    private fun loadUserData(application: Application) {
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "users.json")
        val userListType = object : TypeToken<List<UserDto>>() {}.type
        val userList = Gson().fromJson<List<UserDto>>(jsonString, userListType)

        // Update LiveData
        userListLiveData.value = userList
    }

    private fun loadEquityData(application: Application) {
        // Load JSON data and parse into Feed objects
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "equities.json")
        val trendingListType = object : TypeToken<List<EquityDto>>() {}.type
        val trendingList = Gson().fromJson<List<EquityDto>>(jsonString, trendingListType)

        // Update LiveData
        trendingLiveData.value = trendingList
    }

    fun getTrendListLiveData(): LiveData<List<EquityDto>> {
        return trendingLiveData
    }

    fun getUserListLiveData(): LiveData<List<UserDto>> {
        return userListLiveData
    }
}

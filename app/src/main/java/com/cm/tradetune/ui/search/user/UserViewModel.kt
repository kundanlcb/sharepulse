package com.cm.tradetune.ui.search.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.TrendingDto
import com.cm.tradetune.data.model.UserDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserViewModel(application: Application) : AndroidViewModel(application){
    private val userListLiveData: MutableLiveData<List<UserDto>> = MutableLiveData()

    init {
        // Load JSON data and parse into Feed objects
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "users.json")
        val userListType = object : TypeToken<List<UserDto>>() {}.type
        val userList = Gson().fromJson<List<UserDto>>(jsonString, userListType)

        // Update LiveData
        userListLiveData.value = userList


    }

    fun getUserListLiveData(): LiveData<List<UserDto>> {
        return userListLiveData
    }
}
package com.cm.tradetune.ui.home.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.Notification
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NotificationsViewModel(application: Application) : AndroidViewModel(application){
    private val notificationsLiveData: MutableLiveData<List<Notification>> = MutableLiveData()

    init {
        // Load JSON data and parse into Feed objects
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "notifications.json")
        val notificationsType = object : TypeToken<List<Notification>>() {}.type
        val notifications = Gson().fromJson<List<Notification>>(jsonString, notificationsType)

        // Update LiveData
        notificationsLiveData.value = notifications


    }

    fun getNotificationsData(): LiveData<List<Notification>> {
        return notificationsLiveData
    }
}
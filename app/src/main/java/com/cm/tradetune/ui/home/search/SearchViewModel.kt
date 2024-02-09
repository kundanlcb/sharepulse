package com.cm.tradetune.ui.home.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IndicesViewModel(application: Application) : AndroidViewModel(application){
    private val indicesLiveData: MutableLiveData<List<EquityDto>> = MutableLiveData()

    init {
        // Load JSON data and parse into Feed objects
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "equities.json")
        val indicesListType = object : TypeToken<List<EquityDto>>() {}.type
        val indicesList = Gson().fromJson<List<EquityDto>>(jsonString, indicesListType)

        // Update LiveData
        indicesLiveData.value = indicesList


    }

    fun getIndicesLiveData(): LiveData<List<EquityDto>> {
        return indicesLiveData
    }
}
package com.cm.tradetune.ui.search.indice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.TrendingDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IndicesViewModel(application: Application) : AndroidViewModel(application){
    private val indicesLiveData: MutableLiveData<List<TrendingDto>> = MutableLiveData()

    init {
        // Load JSON data and parse into Feed objects
        val jsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "trending.json")
        val indicesListType = object : TypeToken<List<TrendingDto>>() {}.type
        val indicesList = Gson().fromJson<List<TrendingDto>>(jsonString, indicesListType)

        // Update LiveData
        indicesLiveData.value = indicesList


    }

    fun getIndicesLiveData(): LiveData<List<TrendingDto>> {
        return indicesLiveData
    }
}
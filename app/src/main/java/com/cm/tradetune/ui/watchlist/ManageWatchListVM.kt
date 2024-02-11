package com.cm.tradetune.ui.watchlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.MarketItemDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ManageWatchListVM(application: Application) : AndroidViewModel(application) {

    // MutableLiveData to hold the list of suggestions
    private val _suggestions = MutableLiveData<List<MarketItemDto>>()


    init {

        val suggestionListJsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "watchlist.json")
        val suggestionListType = object : TypeToken<List<MarketItemDto>>() {}.type
        val suggestionList = Gson().fromJson<List<MarketItemDto>>(suggestionListJsonString, suggestionListType)

        // Update LiveData
        _suggestions.value = suggestionList
    }

    // Function to update the list of suggestions
    fun updateSuggestions(newSuggestions: List<MarketItemDto>) {
        _suggestions.value = newSuggestions
    }
    fun getSuggestionListLiveData(): LiveData<List<MarketItemDto>> {
        return _suggestions
    }

}

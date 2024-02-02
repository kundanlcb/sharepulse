package com.cm.tradetune.ui.feed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cm.tradetune.data.model.CommentDto
import com.cm.tradetune.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FeedDetailsViewModel(application: Application) : AndroidViewModel(application) {

    // MutableLiveData to hold the list of suggestions
    private val _comments = MutableLiveData<List<CommentDto>>()


    init {

        val commentListJsonString = JsonUtil.loadJsonFromAsset(application.applicationContext, "comments.json")
        val commentListType = object : TypeToken<List<CommentDto>>() {}.type
        val commentList = Gson().fromJson<List<CommentDto>>(commentListJsonString, commentListType)

        // Update LiveData
        _comments.value = commentList
    }

    // Function to update the list of suggestions
    fun updateComments(newComments: List<CommentDto>) {
        _comments.value = newComments
    }
    fun getCommentListLiveData(): LiveData<List<CommentDto>> {
        return _comments
    }

}

package com.cm.tradetune.webservice

import com.cm.tradetune.data.model.UserDto
import javax.inject.Inject

class UserRepository @Inject constructor(private val webService: WebService, private val apiService: ApiService) {

    fun getUserData(
        userId: String,
        onSuccess: (UserDto?) -> Unit,
        onFailure: (FailureResponse) -> Unit
    ) {
        val single = apiService.fetchData()
        webService.handleRequest(single, onSuccess, onFailure)
    }
}

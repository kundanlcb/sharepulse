package com.cm.tradetune.webservice

import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.data.model.UserDto

class EquityRepository(private val webService: WebService, private val apiService: ApiService) {

    fun getEquities(
        onSuccess: (List<EquityDto>?) -> Unit,
        onFailure: (FailureResponse) -> Unit
    ) {
        println("Inside:EquityRepository fetchEquities")

        val single = apiService.fetchSecurities()
        webService.handleRequest(single, onSuccess, onFailure)
    }
}

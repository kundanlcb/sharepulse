package com.cm.tradetune.webservice

import com.cm.tradetune.data.model.EquityDto
import com.cm.tradetune.data.model.UserDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // Define your API endpoints using Retrofit annotations
    @GET("endpoint")
    fun fetchData(): Single<Response<UserDto>>
    @GET("/equity")
    fun fetchSecurities(): Single<Response<List<EquityDto>>>

}


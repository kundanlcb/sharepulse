package com.cm.tradetune.data.webservice

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class WebService {

    fun <T> handleRequest(
        single: Single<Response<CommonResponse<T>>>,
        onSuccess: (T?) -> Unit,
        onFailure: (FailureResponse) -> Unit
    ) {
        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.success) {
                        onSuccess.invoke(body.data)
                    } else {
                        onFailure.invoke(FailureResponse(body?.message ?: "Unknown error", null))
                    }
                } else {
                    onFailure.invoke(FailureResponse("Network error", null))
                }
            }, { throwable ->
                onFailure.invoke(FailureResponse("Unexpected error", throwable))
            })
    }
}

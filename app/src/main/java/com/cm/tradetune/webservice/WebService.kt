package com.cm.tradetune.webservice

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class WebService {

    fun <T> handleRequest(
        single: Single<Response<T>>,
        onSuccess: (T?) -> Unit,
        onFailure: (FailureResponse) -> Unit
    ) {
        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    onSuccess.invoke(response.body())
                } else {
                    onFailure.invoke(FailureResponse(response.errorBody().toString(), null))
                }
            }, { throwable ->
                onFailure.invoke(FailureResponse(throwable.localizedMessage, throwable))
            })
    }
}

package com.cm.tradetune.data.webservice

data class CommonResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: T?
)

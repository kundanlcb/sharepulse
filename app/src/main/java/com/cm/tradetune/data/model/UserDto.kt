package com.cm.tradetune.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDto(
    val userName: String,
    val firstName: String,
    val lastName: String,
    @SerializedName("userDp") val userDp: String,
    val id: Int
) : Serializable

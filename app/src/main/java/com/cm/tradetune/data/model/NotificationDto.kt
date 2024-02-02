package com.cm.tradetune.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Notification(val type: NotificationType,
                        val message: String,
                        val timestamp: String,
                        @SerializedName("postId") val postReference: String? = null,
                        @SerializedName("profileId") val profileReference: String? = null):
    Serializable

enum class NotificationType {
    LIKE, COMMENT, REPOST, FOLLOW, NEW_POST
}

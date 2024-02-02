package com.cm.tradetune.data.model

import java.io.Serializable

data class CommentDto(
    val userName: String,
    val timestamp: String,
    val commentContent: String
): Serializable

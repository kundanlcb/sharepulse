package com.cm.tradetune.data.model

import java.io.Serializable

data class PollDataDto(
    val option: String,
    val percent: Int
): Serializable

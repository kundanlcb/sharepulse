package com.cm.tradetune.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrendingDto(
    val name: String,
    val symbol: String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("today_percent_change") val todayPercentChange: Double
): Serializable

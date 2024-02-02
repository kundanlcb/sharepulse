package com.cm.tradetune.data.model

import java.io.Serializable

data class MarketItemDto(
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val changePercent: Double,
    val changeInPrice: Double,
    val trendDirection: String
): Serializable

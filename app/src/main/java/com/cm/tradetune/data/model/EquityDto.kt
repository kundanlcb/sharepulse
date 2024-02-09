package com.cm.tradetune.data.model

import java.io.Serializable

class EquityDto(
    val id: Long,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val todayPercentChange: Double,
    val marketCap: String,
    val bookValue: String,
    val faceValue: Double,
    val peRatio: Double,
    val pbRatio: Double,
    val industryPERatio: Double,
) : Serializable

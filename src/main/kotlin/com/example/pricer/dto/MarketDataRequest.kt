package com.example.pricer.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class MarketDataRequest(
    @field:NotNull @field:Positive val underlyingPrice: Double,
    @field:NotNull @field:Positive val volatility: Double,
    @field:NotNull @field:Positive val riskFreeRate: Double,
    @field:NotNull val dividendYield: Double = 0.0,
    @field:NotNull @field:Positive val strikePrice: Double,
    @field:NotNull @field:Positive val maturity: Double,
    @field:NotNull val optionType: String,
    @field:NotNull val modelType: String,
    @field:NotNull val isCall: Boolean,
    val averagingType: String? = null // Optional field for Asian options
)

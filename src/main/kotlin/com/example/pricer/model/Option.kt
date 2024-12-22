package com.example.pricer.model

import com.example.pricer.dto.OptionType
import com.example.pricer.pricingmodel.PricingModel

interface Option {
    val type: OptionType
    val strikePrice: Double
    val maturity: Double

    fun price(model: PricingModel, marketData: MarketData): Double
    fun payoff(underlyingPrice: Double): Double
}
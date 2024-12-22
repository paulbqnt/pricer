package com.example.pricer.dto

import com.example.pricer.model.MarketData
import com.example.pricer.pricingmodel.PricingModel

interface Option {
    val strikePrice: Double
    val maturity: Double
    val type: OptionType
    fun price(model: PricingModel, marketData: MarketData): Double
}
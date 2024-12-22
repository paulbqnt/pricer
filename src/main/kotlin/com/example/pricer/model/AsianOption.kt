package com.example.pricer.model
import com.example.pricer.model.Option
import com.example.pricer.pricingmodel.PricingModel


data class EuropeanOption(
    override val strikePrice: Double,
    override val maturity: Double,
    val isCall: Boolean
) : Option {
    override fun price(model: PricingModel, marketData: MarketData): Double {
        return model.calculatePrice(this, marketData)
    }
}

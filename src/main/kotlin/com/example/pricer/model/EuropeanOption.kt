package com.example.pricer.model
import com.example.pricer.dto.OptionType
import com.example.pricer.model.Option
import com.example.pricer.pricingmodel.PricingModel


data class EuropeanOption(
    override val strikePrice: Double,
    override val maturity: Double,
    val isCall: Boolean
) : Option {
    override val type: OptionType = OptionType.EUROPEAN

    override fun price(model: PricingModel, marketData: MarketData): Double {
        return model.calculatePrice(this, marketData)
    }

    override fun payoff(underlyingPrice: Double): Double {
        return if (isCall) {
            Math.max(underlyingPrice - strikePrice, 0.0)
        } else {
            Math.max(strikePrice - underlyingPrice, 0.0)
        }
    }
}

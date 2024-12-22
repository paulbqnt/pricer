package com.example.pricer.service

import com.example.pricer.model.MarketData
import com.example.pricer.model.Option
import com.example.pricer.pricingmodel.BlackScholesModel
import com.example.pricer.pricingmodel.MonteCarloModel
import org.springframework.stereotype.Service

@Service
class PricerService(
    private val blackScholesModel: BlackScholesModel,
//    private val binomialModel: BinomialModel,
    private val monteCarloModel: MonteCarloModel,
) {
    companion object {
        private const val BLACK_SCHOLES = "BlackScholes"
        private const val BINOMIAL = "Binomial"
        private const val MONTE_CARLO = "MonteCarlo"
    }

    fun calculatePrice(option: Option, modelType: String, marketData: MarketData): Double {
        val model = when (modelType) {
            BLACK_SCHOLES -> blackScholesModel
//            BINOMIAL -> binomialModel
            MONTE_CARLO -> monteCarloModel
            else -> throw IllegalArgumentException("Unsupported model type: $modelType")
        }
        return option.price(model, marketData)
    }

}
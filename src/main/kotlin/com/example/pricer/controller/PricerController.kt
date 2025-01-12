package com.example.pricer.controller

import com.example.pricer.dto.MarketDataRequest
import com.example.pricer.model.*
import com.example.pricer.service.PricerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
@RequestMapping("/api/options")
class PricerController(
    private val pricerService: PricerService
) {
    @PostMapping("/price")
    fun calculateOptionPrice(
        @RequestBody marketDataRequest: MarketDataRequest
    ): ResponseEntity<Double> {
        val option = when (marketDataRequest.optionType) {
            "EUROPEAN" -> EuropeanOption(
                strikePrice = marketDataRequest.strikePrice,
                maturity = marketDataRequest.strikePrice,
                isCall = marketDataRequest.isCall,
            )
            "AMERICAN" -> AmericanOption(
                strikePrice = marketDataRequest.strikePrice,
                maturity = marketDataRequest.maturity,
                isCall = marketDataRequest.isCall
            )
            else -> throw IllegalArgumentException("Invalid option type")
        }

        val marketData = MarketData(
            underlyingPrice = marketDataRequest.underlyingPrice,
            volatility = marketDataRequest.volatility,
            riskFreeRate = marketDataRequest.riskFreeRate,
            dividendYield = marketDataRequest.dividendYield,
        )

        return try {
            val price = pricerService.calculatePrice(option, marketDataRequest.modelType, marketData)
            println(marketDataRequest.modelType)
            ResponseEntity.ok(price)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }

}
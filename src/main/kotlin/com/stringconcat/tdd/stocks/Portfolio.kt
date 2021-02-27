package com.stringconcat.tdd.stocks

import com.stringconcat.tdd.Money
import com.stringconcat.tdd.RateProvider
import com.stringconcat.tdd.sumToCurrency
import java.math.BigDecimal

class Portfolio(private val instruments: Map<Shares, Int> = emptyMap()) {

    fun totalPrice(
        inCurrency: Money.Currency,
        marketInfoProvider: MarketInfoProvider,
        rateProvider: RateProvider
    ): Money {
        return instruments.asSequence()
            .map { priceForInstrument(instrument = it.key, inPossession = it.value, marketInfoProvider) }
            .toList()
            .sumToCurrency(inCurrency, rateProvider)
    }

    private fun priceForInstrument(
        instrument: Shares,
        inPossession: Int,
        marketInfoProvider: MarketInfoProvider
    ) = marketInfoProvider.getInstrumentPrice(instrument) * inPossession
}
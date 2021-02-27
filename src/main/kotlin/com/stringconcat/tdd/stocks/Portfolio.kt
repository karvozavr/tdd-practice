package com.stringconcat.tdd.stocks

import com.stringconcat.tdd.Money
import com.stringconcat.tdd.RateProvider
import com.stringconcat.tdd.sumToCurrency

class Portfolio(private val instruments: Map<Shares, Int> = emptyMap()) {

    fun totalPrice(
        inCurrency: Money.Currency,
        marketInfoProvider: MarketInfoProvider,
        rateProvider: RateProvider
    ): Money {
        return instruments
            .map { priceForInstrument(instrument = it.key, inPossession = it.value, marketInfoProvider) }
            .sumToCurrency(inCurrency, rateProvider)
    }

    private fun priceForInstrument(
        instrument: Shares,
        inPossession: Int,
        marketInfoProvider: MarketInfoProvider
    ) = marketInfoProvider.getInstrumentPrice(instrument) * inPossession
}
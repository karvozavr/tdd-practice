package com.stringconcat.tdd.stocks

import com.stringconcat.tdd.Money
import com.stringconcat.tdd.sumToCurrency

class Portfolio(private val instruments: Map<Shares, Int> = emptyMap()) {

    fun totalPrice(
        inCurrency: Money.Currency,
        marketInfoProvider: MarketInfoProvider
    ): Money {
        return instruments.asSequence()
            .map { priceForInstrument(instrument = it.key, inPosession = it.value, marketInfoProvider) }
            .toList()
            .sumToCurrency(inCurrency)
    }

    private fun priceForInstrument(
        instrument: Shares,
        inPosession: Int,
        marketInfoProvider: MarketInfoProvider
    ) = marketInfoProvider.getInstrumentPrice(instrument) * inPosession
}
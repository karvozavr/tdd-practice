package com.stringconcat.tdd

import java.lang.RuntimeException

class InMemoryMarketInfoProvider(private val prices: Map<Shares, Money> = emptyMap()) : MarketInfoProvider {

    override fun getInstrumentPrice(instrument: Shares): Money {
        return prices[instrument] ?: throw RuntimeException("Unknown stock instrument $instrument")
    }
}
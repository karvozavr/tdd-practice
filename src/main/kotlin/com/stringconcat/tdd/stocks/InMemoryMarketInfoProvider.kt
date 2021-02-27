package com.stringconcat.tdd.stocks

import com.stringconcat.tdd.Money
import java.lang.RuntimeException

class InMemoryMarketInfoProvider(private val prices: Map<Shares, Money> = emptyMap()) : MarketInfoProvider {

    override fun getInstrumentPrice(instrument: Shares): Money {
        return prices[instrument] ?: throw RuntimeException("Unknown stock instrument $instrument")
    }
}
package com.stringconcat.tdd.stocks

import com.stringconcat.tdd.Money

interface MarketInfoProvider {

    fun getInstrumentPrice(instrument: Shares): Money
}

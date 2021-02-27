package com.stringconcat.tdd

interface MarketInfoProvider {

    fun getInstrumentPrice(instrument: Shares): Money
}

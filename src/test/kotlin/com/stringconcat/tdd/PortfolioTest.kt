package com.stringconcat.tdd

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PortfolioTest {

    private val TESLA_SHARES = Shares(name = "TSLA", market = TradingMarket.NASDAQ)


    @Test
    fun `empty portfolio should have price of 0 USD`() {
        val portfolio = Portfolio()
        portfolio.totalPrice(Money.Currency.USD, InMemoryMarketInfoProvider(emptyMap())) shouldBe Money.dollar(0)
    }

    @Test
    fun `portfolio with one Tesla share valued 42 USD should cost 42 USD`() {
        val portfolio = Portfolio(mapOf(TESLA_SHARES to 1))
        val marketInfoProvider = InMemoryMarketInfoProvider(mapOf(TESLA_SHARES to Money(42, Money.Currency.USD)))
        portfolio.totalPrice(Money.Currency.USD, marketInfoProvider) shouldBe Money.dollar(42)
    }
}

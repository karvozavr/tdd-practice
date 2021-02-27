package com.stringconcat.tdd.stocks

import com.stringconcat.tdd.Money
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class PortfolioTest {

    private val TESLA_SHARES = Shares(name = "TSLA", market = TradingMarket.NASDAQ)
    private val NOVARTIS_SHARES = Shares(name = "NOVN", market = TradingMarket.SWX)

    @Test
    fun `empty portfolio should have price of 0 USD`() {
        val portfolio = Portfolio()
        val totalPrice =
            portfolio.totalPrice(Money.Currency.USD, InMemoryMarketInfoProvider(emptyMap()), { BigDecimal.ZERO })
        totalPrice shouldBe Money.dollar(0)
    }

    @Test
    fun `portfolio with one Tesla share valued 42 USD should cost 42 USD`() {
        val portfolio = Portfolio(mapOf(TESLA_SHARES to 1))
        val marketInfoProvider = InMemoryMarketInfoProvider(mapOf(TESLA_SHARES to Money(42, Money.Currency.USD)))

        val totalPrice = portfolio.totalPrice(Money.Currency.USD, marketInfoProvider, { BigDecimal.ZERO })
        totalPrice shouldBe Money.dollar(42)
    }

    @Test
    fun `portfolio with 2 Tesla shares valued 10 USD and 3 Novartis shares valued 3 CHF costs 49 CHF`() {
        val portfolio = Portfolio(mapOf(TESLA_SHARES to 2, NOVARTIS_SHARES to 3))
        val marketInfoProvider = InMemoryMarketInfoProvider(
            mapOf(
                TESLA_SHARES to Money(10, Money.Currency.USD),
                NOVARTIS_SHARES to Money(3, Money.Currency.CHF)
            )
        )

        val totalPrice = portfolio.totalPrice(Money.Currency.CHF, marketInfoProvider, { BigDecimal(2) })
        totalPrice shouldBe Money.franc(49)
    }
}

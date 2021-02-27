package com.stringconcat.tdd

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PortfolioTest {

    @Test
    fun `empty portfolio should have price of 0 USD`() {
        val portfolio = Portfolio()
        portfolio.totalPrice(Money.Currency.USD) shouldBe Money.dollar(0)
    }
}

package com.stringconcat.tdd

import java.math.BigDecimal

typealias RateProvider = (Pair<Money.Currency, Money.Currency>) -> BigDecimal

class Pocket(
    val a: Money,
    val b: Money
) : Expression {

    fun reduce(
        targetCurrency: Money.Currency,
        rateProvider: RateProvider
    ): Money {
        val aTargetCurrencyAmount = targetCurrencyAmount(a, targetCurrency, rateProvider)
        val bTargetCurrencyAmount = targetCurrencyAmount(b, targetCurrency, rateProvider)
        return Money(aTargetCurrencyAmount.plus(bTargetCurrencyAmount), targetCurrency)
    }

    private inline fun targetCurrencyAmount(
        money: Money,
        targetCurrency: Money.Currency,
        rateProvider: RateProvider
    ): BigDecimal =
        if (money.currency == targetCurrency)
            BigDecimal(money.amount)
        else
            BigDecimal(money.amount) * rateProvider(money.currency to targetCurrency)
}

operator fun Money.plus(other: Money): Pocket {
    return Pocket(this, other)
}

fun Collection<Money>.sumToCurrency(targetCurrency: Money.Currency): Money =
    this.fold(Money(amount = 0, currency = targetCurrency)) { acc, money ->
        Pocket(acc, money).reduce(
            targetCurrency,
            rateProvider = { BigDecimal.ZERO }
        )
    }

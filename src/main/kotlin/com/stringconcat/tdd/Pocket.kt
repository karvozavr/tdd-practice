package com.stringconcat.tdd

typealias RateProvider = (Pair<Money.Currency, Money.Currency>) -> Int

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
        return Money(aTargetCurrencyAmount + bTargetCurrencyAmount, targetCurrency)
    }

    private inline fun targetCurrencyAmount(
        money: Money,
        targetCurrency: Money.Currency,
        rateProvider: (Pair<Money.Currency, Money.Currency>) -> Int
    ) =
        if (money.currency == targetCurrency)
            money.amount
        else
            money.amount * rateProvider(money.currency to targetCurrency)
}

operator fun Money.plus(other: Money): Pocket {
    return Pocket(this, other)
}
package com.stringconcat.tdd

class Pocket(
    val a: Money,
    val b: Money
) : Expression {
    fun reduce(
        targetCurrency: Money.Currency,
        rateProvider: (Pair<Money.Currency, Money.Currency>) -> Int
    ): Money {
        val aAmountInTargetCurrency =
            if (a.currency == targetCurrency) a.amount else a.amount * rateProvider(a.currency to targetCurrency)
        val bAmountInTargetCurrency =
            if (b.currency == targetCurrency) b.amount else b.amount * rateProvider(b.currency to targetCurrency)
        return Money(aAmountInTargetCurrency + bAmountInTargetCurrency, targetCurrency)
    }
}

operator fun Money.plus(other: Money): Pocket {
    return Pocket(this, other)
}
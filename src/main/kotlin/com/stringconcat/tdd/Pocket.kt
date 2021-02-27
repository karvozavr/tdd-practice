package com.stringconcat.tdd

class Pocket(
    val a: Money,
    val b: Money
) : Expression {
    fun reduce(
        targetCurrency: Money.Currency,
        rateProvider: (Pair<Money.Currency, Money.Currency>) -> Int
    ): Money {
        return if (a.currency == targetCurrency && b.currency == targetCurrency) {
            Money((a.amount + b.amount), targetCurrency)
        } else {
            Money((a.amount + b.amount) * rateProvider(a.currency to targetCurrency), targetCurrency)
        }
    }
}

operator fun Money.plus(other: Money): Pocket {
    return Pocket(this, other)
}
package com.stringconcat.tdd

import java.math.BigDecimal

open class Money internal constructor(
    val amount: Int,
    val currency: Currency
) : Expression {

    constructor(
        amount: BigDecimal,
        currency: Currency
    ) : this(amount.toInt(), currency)

    override fun equals(other: Any?): Boolean {
        if (other !is Money) return false
        if (amount == other.amount && currency == other.currency) return true
        return false
    }

    operator fun times(multiplier: Int): Money {
        return Money(amount * multiplier, currency = this.currency)
    }

    override fun toString(): String {
        return "Money(amount=$amount, currency=$currency)"
    }

    enum class Currency {
        USD, CHF
    }

    companion object {
        fun dollar(amount: Int) = Money(amount = amount, currency = Currency.USD)
        fun franc(amount: Int) = Money(amount = amount, currency = Currency.CHF)
    }
}
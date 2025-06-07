package dev.yoon.`object`.generic

import java.math.BigDecimal

data class Money(
    private val amount: BigDecimal,
) {
    fun plus(money: Money): Money {
        return Money(this.amount.add(money.amount))
    }

    fun minus(money: Money): Money {
        return Money(this.amount.subtract(money.amount))
    }

    fun times(percent: Double): Money {
        return Money(this.amount.multiply(BigDecimal.valueOf(percent)))
    }

    fun divide(divisor: Double): Money {
        return Money(this.amount.divide(BigDecimal.valueOf(divisor)))
    }

    fun isLessThan(other: Money): Boolean {
        return amount < other.amount
    }

    fun isGreaterThanOrEqual(other: Money): Boolean {
        return amount >= other.amount
    }

    companion object {
        val ZERO = wons(0)

        fun wons(amount: Long): Money {
            return Money(BigDecimal.valueOf(amount))
        }

        fun wons(amount: Double): Money {
            return Money(BigDecimal.valueOf(amount))
        }
    }
}

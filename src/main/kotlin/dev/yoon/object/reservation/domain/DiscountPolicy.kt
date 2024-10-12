package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

abstract class DiscountPolicy(
    private val conditions: List<DiscountCondition>
) {
    protected abstract fun getDiscountAmount(screening: Screening): Money

    fun calculateDiscount(screening: Screening): Money {
        for (condition in conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening)
            }
        }
        return Money.ZERO
    }
}

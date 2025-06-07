package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

abstract class DefaultDiscountPolicy(
    private val conditions: List<DiscountCondition>
): DiscountPolicy {
    protected abstract fun getDiscountAmount(screening: Screening): Money

    override fun calculateDiscount(screening: Screening): Money {
        for (condition in conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening)
            }
        }
        return Money.ZERO
    }
}

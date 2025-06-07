package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

class OverlappedDiscountPolicy(
    private val policies: List<DefaultDiscountPolicy>,
    conditions: List<DiscountCondition>,
) : DefaultDiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        var result = Money.ZERO

        for (policy in policies) {
            result = result.plus(policy.calculateDiscount(screening))
        }
        return result
    }
}

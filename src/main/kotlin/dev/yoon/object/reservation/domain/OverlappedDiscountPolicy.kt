package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

class OverlappedDiscountPolicy(
    val policies: List<DiscountPolicy>,
    conditions: List<DiscountCondition>,
) : DiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        var result = Money.ZERO

        for (policy in policies) {
            result = result.plus(policy.calculateDiscount(screening))
        }
        return result
    }
}

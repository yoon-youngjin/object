package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

class NoneDiscountPolicy(
    conditions: List<DiscountCondition>,
) : DiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        return Money.ZERO
    }
}
package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

class PercentDiscountPolicy(
    private val percent: Double,
    conditions: List<DiscountCondition>,
) : DiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        return screening.getFixedFee().times(percent)
    }
}

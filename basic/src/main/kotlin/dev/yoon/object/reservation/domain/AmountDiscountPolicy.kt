package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

class AmountDiscountPolicy(
    private val discountAmount: Money,
    conditions: List<DiscountCondition>,
) : DefaultDiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        return discountAmount
    }
}

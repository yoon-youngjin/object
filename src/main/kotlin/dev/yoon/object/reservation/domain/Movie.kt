package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

data class Movie(
    val fee: Money,
    val discountPolicy: DiscountPolicy,
) {
    fun calculateFee(screening: Screening): Money {
        return fee.minus(discountPolicy.calculateDiscount(screening))
    }
}

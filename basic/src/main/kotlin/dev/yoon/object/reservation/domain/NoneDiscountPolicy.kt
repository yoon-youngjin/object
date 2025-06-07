package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

class NoneDiscountPolicy : DiscountPolicy {
    override fun calculateDiscount(screening: Screening): Money {
        return Money.ZERO
    }
}
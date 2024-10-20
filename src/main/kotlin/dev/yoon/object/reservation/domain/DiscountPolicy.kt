package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

interface DiscountPolicy {
    fun calculateDiscount(screening: Screening): Money
}
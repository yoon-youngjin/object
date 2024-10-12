package dev.yoon.`object`.reservation.service

import dev.yoon.`object`.reservation.domain.DiscountPolicy

interface DiscountPolicyRepository {
    fun getDiscountPolicy(movieId: Long): DiscountPolicy

    fun insert(discountPolicy: DiscountPolicy)
}

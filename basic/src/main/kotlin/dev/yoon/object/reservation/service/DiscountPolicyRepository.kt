package dev.yoon.`object`.reservation.service

import dev.yoon.`object`.reservation.domain.DefaultDiscountPolicy

interface DiscountPolicyRepository {
    fun getDiscountPolicy(movieId: Long): DefaultDiscountPolicy

    fun insert(defaultDiscountPolicy: DefaultDiscountPolicy)
}

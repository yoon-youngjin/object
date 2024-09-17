package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.domain.DiscountPolicy
import dev.yoon.`object`.reservation.persistence.DiscountPolicyRepository

class InMemoryDiscountPolicyRepository: DiscountPolicyRepository, InMemoryRepository<DiscountPolicy>() {
    override fun getDiscountPolicy(movieId: Long): DiscountPolicy {
        return findOne { it.movieId == movieId }
    }
}

package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.domain.DefaultDiscountPolicy
import dev.yoon.`object`.reservation.service.DiscountPolicyRepository

class InMemoryDiscountPolicyRepository: DiscountPolicyRepository, InMemoryRepository<DefaultDiscountPolicy>() {
    override fun getDiscountPolicy(movieId: Long): DefaultDiscountPolicy {
        return findOne { it.movieId == movieId }
    }
}

package dev.yoon.`object`.reservation.persistence

import dev.yoon.`object`.reservation.domain.DiscountCondition

interface DiscountConditionRepository {
    fun getDiscountConditions(policyId: Long): List<DiscountCondition>

    fun insert(discountCondition: DiscountCondition)
}